package com.doping.tech.model.response;

import com.doping.tech.model.record.StudentRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class StudentResponse extends BaseResponse {

    private StudentRecord student;

    public StudentResponse(StudentRecord student) {
        super();
        this.student = student;
    }

}
