package com.tony.mockapi.controller;

import com.tony.mockapi.repository.ApiRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/{projectAlias}/{apiAlias}/{version}/api")
public class RoutingController {
    @Autowired
    ApiRepository apiRepository;
    
    private Logger logger = LogManager.getLogger(RoutingController.class);

    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public Object doGet(@PathVariable String projectAlias,
                        @PathVariable String apiAlias,
                        @PathVariable String version,
                        @RequestHeader String token,
                        HttpServletRequest request) {
        String remainingPaths = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        logger.info(remainingPaths);
        return null;
    }

    @RequestMapping(value = "/**", method = RequestMethod.POST)
    public Object doPost(@RequestBody Object body,
                         @PathVariable String projectAlias,
                         @PathVariable String apiAlias,
                         @PathVariable String version,
                         @RequestHeader String token,
                         HttpServletRequest request) {
        return null;
    }
}
