package com.tony.mockapi.model;

import com.tony.mockapi.dao.QueryMethod;

public class CreateApiRequest {
    private String apiName;
    private String route;
    private QueryMethod queryMethod;
    private String tableName;
    private String bodySchema;
    private String queryString;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public QueryMethod getQueryMethod() {
        return queryMethod;
    }

    public void setQueryMethod(QueryMethod queryMethod) {
        this.queryMethod = queryMethod;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getBodySchema() {
        return bodySchema;
    }

    public void setBodySchema(String bodySchema) {
        this.bodySchema = bodySchema;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
}
