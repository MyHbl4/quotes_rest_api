package com.moon.quotes_rest_api.utils;

public enum StringConstants {

    NF("NOT FOUND!!!"),
    COULD_NOT_DELETE("Invalid request, failed to delete"),
    COULD_NOT_UPDATED("Invalid request, could not be updated"),
    COULD_NOT_SAVED("Invalid request, could not be saved"),
    ACCESS_DENIED("Invalid request, access is denied");

    public final String value;

    StringConstants(String value) {
        this.value = value;
    }
}
