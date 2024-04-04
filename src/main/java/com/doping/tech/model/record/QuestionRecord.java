package com.doping.tech.model.record;

import com.doping.tech.enums.QuestionOption;

import java.io.Serializable;

public record QuestionRecord(String text,
                             String option1,
                             String option2,
                             String option3,
                             String option4,
                             QuestionOption answer) implements Serializable {
}
