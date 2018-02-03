package com.heimdall.repository;

import com.heimdall.dao.ApiModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ApiRepository extends MongoRepository<ApiModel, String> {

    @Query("{'$and': [{'userId': ?0}, {'appId': ?1}]}")
    Page<ApiModel> getAllByIds(String userId, String appId, Pageable pageable);

    @ExistsQuery("{'$and': [{'id': ?0}, {'appId': ?1}, {'userId': ?2}]}")
    boolean existsApiModelByIds(String apiId, String appId, String userId);
}
