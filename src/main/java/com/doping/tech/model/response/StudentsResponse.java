package com.doping.tech.model.response;

import com.doping.tech.model.record.StudentRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class StudentsResponse extends BaseResponse {

    private List<StudentRecord> students;

    public StudentsResponse(List<StudentRecord> students) {
        super();
        this.students = students;
    }
}
