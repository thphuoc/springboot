package com.tony.mockapi.repository;

import com.tony.mockapi.dao.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{$and: [{'password': ?1}, {'$or': [{'email': ?0}, {'userName': ?0}]}]}")
    User findCustomByLoginNameAndPassword(String loginName, String password);

    @Query("{$or: [{'email': ?0}, {'userName': ?1}]}")
    User findCustomByEmailOrUserName(String email, String userName);
    User findByToken(String token);
}
