package com.heimdall.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Tran Huu Phuoc on 12/8/2016.
 */
@Document(collection = "services")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiModel {

    @Id
    private String id;
    private String userId;
    private String appId;

    @NotNull
    @NotBlank
    private String exposedEndpoint;

    @NotNull
    @NotBlank
    private String resourceUri;
    @NotNull
    @NotBlank
    private String version;

    private boolean published;

    private int readTimeout;

    private int connectionTimeout;

    private int throttlingRequestLimit;

    private int throttlingTimeRangeInSecond;

    private boolean enableThrottling;

    private List<String> allowHeadersInbound;

    private List<String> allowHeadersOutbound;

    private List<String> allowMethods;

    private boolean issueAccessToken;

    private boolean verifyAccessToken;

    private List<Header> additionResponseHeaders;

    public List<String> getAllowMethods() {
        return allowMethods;
    }

    public void setAllowMethods(List<String> allowMethods) {
        this.allowMethods = allowMethods;
    }

    public int getThrottlingRequestLimit() {
        return throttlingRequestLimit;
    }

    public void setThrottlingRequestLimit(int throttlingRequestLimit) {
        this.throttlingRequestLimit = throttlingRequestLimit;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean isPublished) {
        this.published = isPublished;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String ipAddress) {
        this.resourceUri = ipAddress;
    }

    public String getExposedEndpoint() {
        return exposedEndpoint;
    }

    public void setExposedEndpoint(String exposedEndpoint) {
        this.exposedEndpoint = exposedEndpoint;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getThrottlingTimeRangeInSecond() {
        return throttlingTimeRangeInSecond;
    }

    public void setThrottlingTimeRangeInSecond(int throttlingTimeRangeInSecond) {
        this.throttlingTimeRangeInSecond = throttlingTimeRangeInSecond;
    }

    public boolean isEnableThrottling() {
        return enableThrottling;
    }

    public void setEnableThrottling(boolean enableThrottling) {
        this.enableThrottling = enableThrottling;
    }

    public List<String> getAllowHeadersInbound() {
        return allowHeadersInbound;
    }

    public void setAllowHeadersInbound(List<String> allowHeadersInbound) {
        this.allowHeadersInbound = allowHeadersInbound;
    }

    public List<String> getAllowHeadersOutbound() {
        return allowHeadersOutbound;
    }

    public void setAllowHeadersOutbound(List<String> allowHeadersOutbound) {
        this.allowHeadersOutbound = allowHeadersOutbound;
    }

    public boolean isIssueAccessToken() {
        return issueAccessToken;
    }

    public void setIssueAccessToken(boolean issueAccessToken) {
        this.issueAccessToken = issueAccessToken;
    }

    public boolean isVerifyAccessToken() {
        return verifyAccessToken;
    }

    public void setVerifyAccessToken(boolean verifyAccessToken) {
        this.verifyAccessToken = verifyAccessToken;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Header> getAdditionResponseHeaders() {
        return additionResponseHeaders;
    }

    public void setAdditionResponseHeaders(List<Header> additionResponseHeaders) {
        this.additionResponseHeaders = additionResponseHeaders;
    }
}
