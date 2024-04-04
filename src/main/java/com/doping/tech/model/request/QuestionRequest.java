package com.doping.tech.model.request;

import com.doping.tech.enums.QuestionOption;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequest {

    @NotBlank
    private String text;
    @NotEmpty
    private QuestionOption questionAnswer;
}
