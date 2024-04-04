package com.doping.tech.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuestionOption {

    OPTION1("OPTION1"),
    OPTION2("OPTION2"),
    OPTION3("OPTION3"),
    OPTION4("OPTION4"),
    NONE("NONE");

    private final String value;
}
