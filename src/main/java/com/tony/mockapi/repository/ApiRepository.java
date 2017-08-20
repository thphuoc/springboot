package com.tony.mockapi.repository;

import com.tony.mockapi.dao.Api;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ApiRepository extends MongoRepository<Api, String> {
    @DeleteQuery("{'$and': [{'user.id': ?0}, {'project.id': ?1}, {'id': ?2}]}")
    void removeApi(String userId, String projectId, String apiId);

    @Query("{'$and': [{'user.id': ?0}, {'project.id': ?1}, {'id': ?2}]}")
    Api findCustomByUserIdAndProjectIdAndApiId(String userId, String projectId, String apiId);
}
