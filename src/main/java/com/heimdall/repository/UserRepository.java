package com.heimdall.repository;

import com.heimdall.dao.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<UserModel, String> {

    @Query("{$and: [{'password': ?1}, {'$or': [{'email': ?0}, {'userName': ?0}]}]}")
    UserModel findCustomByLoginNameAndPassword(String loginName, String password);

    @Query("{$or: [{'email': ?0}, {'userName': ?0}]}")
    UserModel findCustomByEmailOrUserName(String emailOrUsername);

    @Query("{$or: [{'email': ?1}, {'userName': ?0}]}")
    UserModel findCustomByEmailOrUserName(String username, String email);
}
