package com.petrych.service;

public enum TestingEndpoints {
    STORAGE_DIR("storage-test"),
    LOCALHOST_ENDPOINT("http://localhost:8080/"),
    SCREENSHOTS_ENDPOINT("screenshots")
    ;


    TestingEndpoints(String name) {
        this.name = name;
    }

    private String name;


    @Override
    public final String toString() {
        return this.name;
    }
}
