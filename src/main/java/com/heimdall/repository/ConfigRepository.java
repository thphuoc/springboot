package com.heimdall.repository;

import com.heimdall.dao.Configs;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigRepository extends MongoRepository<Configs, String> {
}
