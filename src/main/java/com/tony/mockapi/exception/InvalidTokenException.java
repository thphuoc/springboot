package com.tony.mockapi.exception;

import com.tony.mockapi.exception.base.ApiException;
import com.tony.mockapi.exception.base.GenericException;

public class InvalidTokenException extends GenericException {
    public InvalidTokenException() {
        super(ApiException.INVALID_TOKEN);
    }
}
