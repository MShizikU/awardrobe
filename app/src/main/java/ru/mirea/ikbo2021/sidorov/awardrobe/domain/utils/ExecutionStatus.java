package ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ExecutionStatus {
    CONNECT("connect"),
    ACCEPT("accept"),
    SEND("send"),
    ERROR("error"),
    SUCCESS("success");

    private final String status;

    ExecutionStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
