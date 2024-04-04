package com.doping.tech.model.response;

import com.doping.tech.model.record.AnswerRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AnswerResponse extends BaseResponse {

    private List<AnswerRecord> answers;

    public AnswerResponse(List<AnswerRecord> answers) {
        super();
        this.answers = answers;
    }

}
