package com.tony.mockapi.controller;

import com.tony.mockapi.dao.Api;
import com.tony.mockapi.dao.ApiStatus;
import com.tony.mockapi.dao.Project;
import com.tony.mockapi.dao.User;
import com.tony.mockapi.exception.BadRequestException;
import com.tony.mockapi.exception.InvalidTokenException;
import com.tony.mockapi.exception.NotFoundApiException;
import com.tony.mockapi.exception.NotFoundProjectException;
import com.tony.mockapi.exception.base.GenericException;
import com.tony.mockapi.model.CreateApiRequest;
import com.tony.mockapi.repository.ApiRepository;
import com.tony.mockapi.repository.ProjectRepository;
import com.tony.mockapi.repository.UserRepository;
import com.tony.mockapi.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.tony.mockapi.dao.ApiStatus.PUBLISH;

@RestController(value = "/api")
public class MockApiController {

    @Autowired
    ApiRepository apiRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ApiService apiService;

    @RequestMapping(value = "/{projectId}/create", method = RequestMethod.POST)
    public Api createApi(@RequestHeader String userToken, @PathVariable String projectId, @RequestBody CreateApiRequest request) throws GenericException {
        User user = getUserByToken(userToken);
        Project project = projectRepository.findCustomByUserIdAndProjectId(user.getId(), projectId);
        if (project == null) {
            throw new NotFoundProjectException();
        }
        Api api = new Api();
        api.setProject(project);
        api.setUser(user);
        api.setApiName(request.getApiName());
        api.setBodySchema(request.getBodySchema());
        api.setQueryMethod(request.getQueryMethod());
        api.setQueryString(request.getQueryString());
        api.setRoute(request.getRoute());
        api.setTableName(request.getTableName());
        api.setStatus(ApiStatus.UNPUBLISH);
        return apiRepository.save(api);
    }

    @RequestMapping(value = "/{projectId}/{apiId}/remove", method = RequestMethod.POST)
    public void removeApi(@RequestHeader String userToken, @PathVariable String projectId, @PathVariable String apiId) throws GenericException {
        User user = getUserByToken(userToken);
        apiRepository.removeApi(user.getId(), projectId, apiId);
    }

    @RequestMapping(value = "/{projectId}/{apiId}/update", method = RequestMethod.POST)
    public Api updateApi(@RequestHeader String userToken, @PathVariable String projectId, @PathVariable String apiId, @RequestBody CreateApiRequest request) throws GenericException {
        User user = getUserByToken(userToken);
        Project project = projectRepository.findCustomByUserIdAndProjectId(user.getId(), projectId);
        if (project == null) {
            throw new NotFoundProjectException();
        }
        Api api = apiRepository.findCustomByUserIdAndProjectIdAndApiId(user.getId(), projectId, apiId);
        api.setProject(project);
        api.setUser(user);
        api.setApiName(request.getApiName());
        api.setBodySchema(request.getBodySchema());
        api.setQueryMethod(request.getQueryMethod());
        api.setQueryString(request.getQueryString());
        api.setRoute(request.getRoute());
        api.setTableName(request.getTableName());
        return apiRepository.save(api);
    }

    @RequestMapping(value = "/{projectId}/{apiId}/{status}", method = RequestMethod.POST)
    public Api publishApi(@RequestHeader String userToken, @PathVariable String projectId, @PathVariable String apiId, @PathVariable ApiStatus status) throws GenericException {
        User user = getUserByToken(userToken);
        Api api = apiRepository.findCustomByUserIdAndProjectIdAndApiId(user.getId(), projectId, apiId);
        if (api == null) {
            throw new NotFoundApiException();
        }
        boolean updateStatus = status == PUBLISH ? apiService.unpublishApi(api) : apiService.publishApi(api);
        if (updateStatus) {
            ApiStatus nextApiStatus = status == PUBLISH ? ApiStatus.UNPUBLISH : PUBLISH;
            api.setStatus(nextApiStatus);
            apiRepository.save(api);
        }
        throw new BadRequestException();
    }

    @RequestMapping(value = "/{projectId}/{apiId}/unpublish", method = RequestMethod.POST)
    public Api unpublishApi(@RequestHeader String userToken, @PathVariable String projectId, @PathVariable String apiId) throws GenericException {
        User user = getUserByToken(userToken);
        Api api = apiRepository.findCustomByUserIdAndProjectIdAndApiId(user.getId(), projectId, apiId);
        if (api == null) {
            throw new NotFoundApiException();
        }
        api.setStatus(apiService.unpublishApi(api) ? ApiStatus.UNPUBLISH : PUBLISH);
        return apiRepository.save(api);
    }

    private User getUserByToken(String userToken) throws InvalidTokenException {
        User user = userRepository.findByToken(userToken);
        if (user == null) {
            throw new InvalidTokenException();
        }
        return user;
    }
}
