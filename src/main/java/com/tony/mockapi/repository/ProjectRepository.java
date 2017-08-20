package com.tony.mockapi.repository;

import com.tony.mockapi.dao.Project;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProjectRepository extends MongoRepository<Project, String> {
    @Query("{'user.id': ?0}")
    List<Project> findCustomByUserId(String userId);

    @Query("{'user.id': ?0, " +
            "'$and': [" +
            "{'$or': [{'projectName': ?1}]}," +
            "{'$or': [{'alias': ?2}]}" +
            "]}")
    Project findCustomByProjectAndUserId(String userId, String projectName, String alias);

    Project findById(String id);

    @DeleteQuery("{'$and': [{'user.id': ?0}, {'id': ?1}]}")
    void removeByUserIdAndProjectId(String userId, String projectId);

    @Query("{'$and': [{'user.id': ?0},{'id': ?1}]}")
    Project findCustomByUserIdAndProjectId(String userId, String projectId);
}
