package com.mjc.school.service.exceptiion;

public enum ServiceError {
    NEWS_ID_DOES_NOT_EXIST("000001", "News with id %d does not exist."),
    AUTHOR_ID_DOES_NOT_EXIST("000002", "Author Id does not exist. Author Id is: %s"),
    VALIDATE_NEGATIVE_OR_NULL_NUMBER("000010", "%s can not be null or less than 1. %s is: %s"),
    VALIDATE_NULL_STRING("000011", "%s can not be null. %s is null"),
    VALIDATE_STRING_LENGTH("000012", "%s can not be less than %d and more than %d symbols. %s is %s"),
    VALIDATE_INT_VALUE("000013", "%s should be number");

    private final String errorMessage;

    private ServiceError(String errorCode, String message) {
        this.errorMessage = "ERROR_CODE: " + errorCode + " ERROR_MESSAGE: " + message;
    }

    public String getMessage() {
        return this.errorMessage;
    }

    public String getErrorCode() {
        return this.errorMessage;
    }
}
