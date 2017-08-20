package com.tony.mockapi.exception;

import com.tony.mockapi.exception.base.ApiException;
import com.tony.mockapi.exception.base.GenericException;

public class ProjectExistedException extends GenericException {
    public ProjectExistedException() {
        super(ApiException.PROJECT_NAME_HAS_EXISTED);
    }
}
