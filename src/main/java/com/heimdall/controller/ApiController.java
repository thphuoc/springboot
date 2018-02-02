package com.heimdall.controller;

import com.heimdall.dao.ApiModel;
import com.heimdall.repository.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class ApiController {

    @Autowired
    ApiRepository repository;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ApiModel createApi(@RequestBody ApiModel newApi) {
        return null;
    }
}
