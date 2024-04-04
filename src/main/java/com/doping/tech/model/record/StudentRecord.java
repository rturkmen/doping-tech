package com.doping.tech.model.record;

import com.doping.tech.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StudentRecord(Long id,
                            String name,
                            String surname,
                            Gender gender,
                            String studentNumber,
                            List<TestRecord> tests) implements Serializable {
}
