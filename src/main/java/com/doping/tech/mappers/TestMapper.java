package com.doping.tech.mappers;

import com.doping.tech.entity.Question;
import com.doping.tech.entity.Test;
import com.doping.tech.model.record.QuestionRecord;
import com.doping.tech.model.record.TestRecord;
import com.doping.tech.model.request.TestCreateRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface TestMapper {

    TestRecord mapEntityToRecord(Test test);

    List<QuestionRecord> mapQuestionEntityListToRecordList(List<Question> questions);

    Test mapRequestToTest(TestCreateRequest request);

    @AfterMapping
    default void setQuestionTest(@MappingTarget Test test) {
        test.getQuestions().forEach(e -> e.setTest(test));
    }
}
