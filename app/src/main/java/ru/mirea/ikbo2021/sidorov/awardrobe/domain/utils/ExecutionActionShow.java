package ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ExecutionActionShow {
    CONNECT("connect"),
    WAITING("waiting"),
    TRANSFER("transfer"),
    ACCEPT_TRANSFER("accept_transfer"),
    SUCCESS_FINAL("success_final"),
    SUCCESS_MORE("success_more"),
    ERROR("error");



    private final String status;

    ExecutionActionShow(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}