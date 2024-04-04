package com.doping.tech.model.record;

import com.doping.tech.enums.QuestionOption;

import java.io.Serializable;

public record AnswerRecord(QuestionRecord question,
                           QuestionOption questionAnswer) implements Serializable {
}
