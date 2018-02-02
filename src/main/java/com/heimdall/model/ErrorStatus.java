package com.heimdall.model;

import lombok.Data;

@Data
public class ErrorStatus {
    private String reason;

    public ErrorStatus(String reason) {
        this.reason = reason;
    }
}
