package com.tony.mockapi.exception;

import com.tony.mockapi.exception.base.ApiException;
import com.tony.mockapi.exception.base.GenericException;

public class NotFoundApiException extends GenericException {
    public NotFoundApiException() {
        super(ApiException.NOT_FOUND_API);
    }
}
