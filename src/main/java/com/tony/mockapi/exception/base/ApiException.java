package com.tony.mockapi.exception.base;

import com.tony.mockapi.model.Status;

public interface ApiException {
    Status LOGIN_FAILED = new Status("E100", "Login failed!");
    Status USER_HAS_EXISTED = new Status("E101", "User has existed already!");
    Status INVALID_TOKEN = new Status("E102", "Invalid token!");
    Status PROJECT_NAME_HAS_EXISTED = new Status("E103", "Project name has taken already!");
    Status NOT_FOUND_PROJECT_TO_UPDATE = new Status("E104", "Not found project to update!");
    Status NOT_FOUND_API = new Status("E105", "Not found api!");
}
