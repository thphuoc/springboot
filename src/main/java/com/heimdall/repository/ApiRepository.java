package com.heimdall.repository;

import com.heimdall.dao.ApiModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApiRepository extends MongoRepository<ApiModel, String> {
//    @DeleteQuery("{'$and': [{'user.id': ?0}, {'project.id': ?1}, {'id': ?2}]}")
//    void removeApi(String userId, String projectId, String apiId);
//
//    @Query("{'$and': [{'user.id': ?0}, {'project.id': ?1}, {'id': ?2}]}")
//    ApiModel findCustomByUserIdAndProjectIdAndApiId(String userId, String projectId, String apiId);
}
