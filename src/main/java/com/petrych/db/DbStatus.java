package com.petrych.db;

public enum DbStatus {

    INTERNAL_DB_ERROR(500, "Database can not be accessed"),
    NO_CONTENT(204, "Database has no items"),
    OK(200, "Database status is OK");

    private final int statusCode;
    private final String message;

    DbStatus(int statusCode, String message) {

        this.statusCode = statusCode;
        this.message = message;
    }


    public String getMessage() {

        return this.message;
    }


    public int getStatusCode() {

        return this.statusCode;
    }
}
