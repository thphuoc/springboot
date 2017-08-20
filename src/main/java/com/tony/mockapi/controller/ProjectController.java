package com.tony.mockapi.controller;

import com.tony.mockapi.dao.Project;
import com.tony.mockapi.dao.User;
import com.tony.mockapi.exception.InvalidTokenException;
import com.tony.mockapi.exception.NotFoundProjectException;
import com.tony.mockapi.exception.ProjectExistedException;
import com.tony.mockapi.exception.base.GenericException;
import com.tony.mockapi.model.CreateProjectRequest;
import com.tony.mockapi.repository.ProjectRepository;
import com.tony.mockapi.repository.UserRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Api(tags = "Project Api")
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/list", method = POST)
    public List<Project> getAllProjects(@RequestHeader String userToken) throws GenericException {
        User user = getUserByToken(userToken);
        List<Project> projects = projectRepository.findCustomByUserId(user.getId());
        if (projects != null) {
            return projects;
        }
        return Collections.emptyList();
    }

    @RequestMapping(value = "/create", method = POST)
    public Project createProject(@RequestHeader String userToken, @RequestBody CreateProjectRequest request) throws Exception {
        User user = getUserByToken(userToken);
        Project project = getUniqueProject(request, user);
        projectRepository.save(project);
        return project;
    }

    @RequestMapping(value = "/{projectId}/remove", method = POST)
    public void removeProject(@RequestHeader String userToken, @PathVariable String projectId) throws Exception {
        User user = getUserByToken(userToken);
        projectRepository.removeByUserIdAndProjectId(user.getId(), projectId);
    }

    @RequestMapping(value = "/{projectId}/update", method = POST)
    public void updateProject(@RequestHeader String userToken,
                              @PathVariable String projectId,
                              @RequestBody CreateProjectRequest request) throws Exception {
        Project project = projectRepository.findById(projectId);
        if (project == null) {
            throw new NotFoundProjectException();
        }

        User user = getUserByToken(userToken);
        project = getUniqueProject(request, user);
        project.setId(projectId);
        projectRepository.save(project);
    }

    private Project getUniqueProject(CreateProjectRequest request, User user)
            throws ProjectExistedException {
        Project project = projectRepository.findCustomByProjectAndUserId(user.getId(), request.getProjectName(), request.getAlias());
        if (project != null) {
            throw new ProjectExistedException();
        }
        project = new Project();
        project.setProjectName(request.getProjectName());
        project.setAlias(request.getAlias());
        project.setUser(user);
        return project;
    }

    private User getUserByToken(String userToken) throws InvalidTokenException {
        User user = userRepository.findByToken(userToken);
        if (user == null) {
            throw new InvalidTokenException();
        }
        return user;
    }
}
