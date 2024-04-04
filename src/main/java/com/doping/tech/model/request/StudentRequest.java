package com.doping.tech.model.request;

import com.doping.tech.enums.Gender;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.doping.tech.model.constants.ValidationMessages.GENDER_NOT_EMPTY;
import static com.doping.tech.model.constants.ValidationMessages.STUDENT_NAME_NOT_EMPTY;
import static com.doping.tech.model.constants.ValidationMessages.STUDENT_NUMBER_NOT_EMPTY;
import static com.doping.tech.model.constants.ValidationMessages.STUDENT_SURNAME_NOT_EMPTY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    @NotEmpty(message = STUDENT_NAME_NOT_EMPTY)
    private String name;

    @NotEmpty(message = STUDENT_SURNAME_NOT_EMPTY)
    private String surname;

    @NotEmpty(message = GENDER_NOT_EMPTY)
    private Gender gender;

    @NotEmpty(message = STUDENT_NUMBER_NOT_EMPTY)
    private String studentNumber;
}
