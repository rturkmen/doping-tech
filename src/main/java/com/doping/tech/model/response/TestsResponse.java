package com.doping.tech.model.response;

import com.doping.tech.model.record.TestRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TestsResponse extends BaseResponse {

    private List<TestRecord> tests;

    public TestsResponse(List<TestRecord> tests) {
        super();
        this.tests = tests;
    }

}
