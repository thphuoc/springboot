package com.tony.mockapi.exception;

import com.tony.mockapi.exception.base.ApiException;
import com.tony.mockapi.exception.base.GenericException;

public class BadRequestException extends GenericException {
    public BadRequestException() {
        super(ApiException.NOT_FOUND_PROJECT_TO_UPDATE);
    }
}
