package com.heimdall.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;

@Document(collection = "applications")
public class ApplicationModel {
    @Id
    private String id;
    private String userId;
    private String appName;
    private String privateKey;
    @Min(value = 30, message = "Minimum expired token in 30 seconds")
    private int tokenExpiredInSeconds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String hs256SecretKey) {
        this.privateKey = hs256SecretKey;
    }

    public int getTokenExpiredInSeconds() {
        return tokenExpiredInSeconds;
    }

    public void setTokenExpiredInSeconds(int tokenExpiredInSeconds) {
        this.tokenExpiredInSeconds = tokenExpiredInSeconds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
