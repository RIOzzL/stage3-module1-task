package com.mjc.school.utils;

import lombok.Getter;

@Getter
public enum Operations {
    GET_ALL_NEWS(1, "Get all news."),
    GET_NEWS_BY_ID(2, "Get news by id."),
    CREATE_NEWS(3, "Create news."),
    UPDATE_NEWS(4, "Update news."),
    REMOVE_NEWS_BY_ID(5, "Remove news by id."),
    EXIT(6, "Exit");

    private int operationNumber;
    private String operationDescription;

    Operations(int operationNumber, String operationDescription) {
        this.operationNumber = operationNumber;
        this.operationDescription = operationDescription;
    }
}
