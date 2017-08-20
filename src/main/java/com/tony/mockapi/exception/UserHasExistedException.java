package com.tony.mockapi.exception;

import com.tony.mockapi.exception.base.ApiException;
import com.tony.mockapi.exception.base.GenericException;

public class UserHasExistedException extends GenericException {
    public UserHasExistedException() {
        super(ApiException.USER_HAS_EXISTED);
    }
}
