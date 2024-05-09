package ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    ACTIVE("active"),
    INACTIVE("inactive"),
    INUSE("inuse"),
    BLOCKED("blocked"),
    DELETED("deleted");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}