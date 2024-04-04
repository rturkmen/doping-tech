package com.doping.tech.model.response;

import com.doping.tech.model.record.StudentTestsRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class StudentTestResponse extends BaseResponse {

    private StudentTestsRecord studentTest;

    public StudentTestResponse(StudentTestsRecord studentTest) {
        super();
        this.studentTest = studentTest;
    }

}
