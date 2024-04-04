package com.doping.tech.model.response;

import com.doping.tech.model.record.TestRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TestResponse extends BaseResponse {

    private TestRecord test;

    public TestResponse(TestRecord test) {
        super();
        this.test = test;
    }

}
