package com.doping.tech.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorDescription {

    TEST_NOT_FOUND(1, "Test couldn't found on system"),
    STUDENT_NOT_FOUND(2, "Student couldn't found on system"),
    STUDENT_ANSWERS_NOT_FOUND(996, "Student answers didn't find any test on  system"),
    STUDENT_TEST_NOT_FOUND(997, "Student doesn't have any test on  system"),
    STUDENT_NOT_FOUND_WITH_NUMBER(998, "Student couldn't found with student number"),
    QUESTION_NOT_FOUND(999, "Question didn't found"),
    COMMON_SYSTEM_ERROR(1000, "An unexpected error occurred");

    private final int code;
    private final String message;
}
