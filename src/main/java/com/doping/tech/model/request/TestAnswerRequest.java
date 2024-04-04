package com.doping.tech.model.request;

import com.doping.tech.enums.QuestionOption;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class TestAnswerRequest implements Serializable {
    @NotNull
    private Long studentId;
    @NotNull
    private Long testId;
    @NotEmpty
    private Map<Long, QuestionOption> answers;
}

