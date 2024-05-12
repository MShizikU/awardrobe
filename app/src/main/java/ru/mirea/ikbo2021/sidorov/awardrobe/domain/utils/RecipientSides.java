package ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RecipientSides {
    USER("user"),
    EXECUTOR("executor");

    private final String side;

    RecipientSides(String status) {
        this.side = status;
    }

    @JsonValue
    public String getStatus() {
        return side;
    }
}
