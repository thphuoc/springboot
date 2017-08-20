package com.tony.mockapi.exception.base;

import com.tony.mockapi.model.Status;

public class GenericException extends Exception {
    private Status status;

    public GenericException(String errorCode, String errorMessage) {
        status = new Status(errorCode, errorMessage);
    }

    public GenericException(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
