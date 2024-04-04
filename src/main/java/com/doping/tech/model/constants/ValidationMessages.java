package com.doping.tech.model.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationMessages {
    public static final String STUDENT_NUMBER_NOT_EMPTY = "Student number shouldn't be empty";
    public static final String STUDENT_NAME_NOT_EMPTY = "Student name shouldn't be empty";
    public static final String STUDENT_SURNAME_NOT_EMPTY = "Student surname shouldn't be empty";
    public static final String GENDER_NOT_EMPTY = "Gender shouldn't be empty";
    public static final String TEST_ID_NOT_EMPTY = "Test id shouldn't be empty";
}
