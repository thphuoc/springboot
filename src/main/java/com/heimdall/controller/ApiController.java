package com.heimdall.controller;

import com.heimdall.dao.ApiModel;
import com.heimdall.defines.Constants;
import com.heimdall.repository.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api")
public class ApiController {

    private final ApiRepository repository;

    @Autowired
    public ApiController(ApiRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/create")
    public ResponseEntity createApi(@RequestBody ApiModel newApi,
                                    @RequestAttribute String userId) {
        newApi.setId(null);//Auto generate
        newApi.setUserId(userId);
        newApi = repository.insert(newApi);
        return ResponseEntity.ok(newApi);
    }

    @PutMapping("/update")
    public ResponseEntity updateApi(@RequestBody ApiModel apiModel,
                                    @RequestAttribute String userId) {
        String appId = apiModel.getAppId();
        String apiId = apiModel.getId();
        if (!repository.existsApiModelByIds(apiId, appId, userId)) {
            return ResponseEntity.notFound().build();
        }
        apiModel.setUserId(userId);
        apiModel = repository.save(apiModel);
        return ResponseEntity.ok(apiModel);
    }

    @GetMapping("/list")
    public ResponseEntity getListApis(@RequestParam(Constants.HeaderKey.APP_ID) String appId,
                                      @RequestParam("pageIndex") int pageIndex,
                                      @RequestParam("pageCapacity") int pageCapacity,
                                      @RequestAttribute String userId) {
        Page<ApiModel> page = repository.getAllByIds(userId, appId, new PageRequest(pageIndex, pageCapacity));
        return ResponseEntity.ok(page);
    }
}
