package com.tony.mockapi.exception;

import com.tony.mockapi.exception.base.ApiException;
import com.tony.mockapi.exception.base.GenericException;

public class LoginException extends GenericException {

    public LoginException() {
        super(ApiException.LOGIN_FAILED);
    }
}
