package com.tony.mockapi.service;

import com.tony.mockapi.dao.Api;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    public boolean publishApi(Api api) {
        //TODO: call rest temp
        return true;
    }

    public boolean unpublishApi(Api api) {
        return false;
    }

    public boolean createDatabase(String dbName) {
        return false;
    }

    public boolean removeDatabase(String dbName) {
       return false;
    }
}