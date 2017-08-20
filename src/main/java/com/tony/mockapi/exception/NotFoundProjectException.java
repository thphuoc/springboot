package com.tony.mockapi.exception;

import com.tony.mockapi.exception.base.ApiException;
import com.tony.mockapi.exception.base.GenericException;

public class NotFoundProjectException extends GenericException {
    public NotFoundProjectException() {
        super(ApiException.NOT_FOUND_PROJECT_TO_UPDATE);
    }
}
