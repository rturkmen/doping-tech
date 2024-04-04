package com.doping.tech.model.request;

import com.doping.tech.model.dto.QuestionDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class TestCreateRequest {

    @NotBlank
    private String name;
    @NotEmpty
    private List<QuestionDto> questions;

}

