package com.heimdall.repository;

import com.heimdall.dao.ApplicationModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<ApplicationModel, String> {
//    @Query("{'user.id': ?0}")
//    List<ApplicationModel> findCustomByUserId(String userId);
//
//    @Query("{'user.id': ?0, " +
//            "'$and': [" +
//            "{'$or': [{'projectName': ?1}]}," +
//            "{'$or': [{'alias': ?2}]}" +
//            "]}")
//    ApplicationModel findCustomByProjectAndUserId(String userId, String projectName, String alias);
//
//    ApplicationModel findById(String id);
//
//    @DeleteQuery("{'$and': [{'user.id': ?0}, {'id': ?1}]}")
//    void removeByUserIdAndProjectId(String userId, String projectId);
//
//    @Query("{'$and': [{'user.id': ?0},{'id': ?1}]}")
//    ApplicationModel findCustomByUserIdAndProjectId(String userId, String projectId);
}
