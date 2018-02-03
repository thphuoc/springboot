package com.heimdall.controller;

import com.heimdall.dao.ApplicationModel;
import com.heimdall.defines.Constants;
import com.heimdall.model.ErrorStatus;
import com.heimdall.repository.ApplicationRepository;
import com.heimdall.utils.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class ApplicationController {
    private final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationController(ApplicationRepository repository) {
        this.applicationRepository = repository;
    }

    @PostMapping("/create")
    public ResponseEntity createNewApplication(@RequestParam(Constants.HeaderKey.APP_NAME) String appName,
                                               @RequestAttribute String userId) {
        if (StringUtils.isEmpty(appName)) {
            return ResponseEntity.badRequest().body(new ErrorStatus("Application name cannot empty."));
        }
        ApplicationModel app = new ApplicationModel();
        app.setAppName(appName);
        app.setUserId(userId);
        app.setPrivateKey(StringUtils.getRandomPassword());
        app = applicationRepository.insert(app);
        return ResponseEntity.ok(app);
    }

    @DeleteMapping("/remove")
    public ResponseEntity removeApp(@RequestParam(Constants.HeaderKey.APP_ID) String appId,
                                    @RequestAttribute String userId) {
        long preCount = applicationRepository.count();
        applicationRepository.removeByUserIdAndProjectId(userId, appId);
        long afterCount = applicationRepository.count();
        return ResponseEntity.ok(new JSONObject().append("effects", afterCount - preCount));
    }

    @PutMapping("/update")
    public ResponseEntity updateApp(@RequestParam(Constants.HeaderKey.APP_ID) String appId,
                                    @RequestParam(Constants.HeaderKey.APP_NAME) String appName,
                                    @RequestAttribute String userId) {
        ApplicationModel app = applicationRepository.findByUserIdAndAppId(userId, appId);
        if (app == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorStatus("Resource not found"));
        }

        app.setAppName(appName);
        app = applicationRepository.save(app);
        return ResponseEntity.ok(app);
    }
}
