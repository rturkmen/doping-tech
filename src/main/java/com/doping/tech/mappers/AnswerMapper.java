package com.doping.tech.mappers;

import com.doping.tech.entity.Answer;
import com.doping.tech.entity.Question;
import com.doping.tech.entity.Student;
import com.doping.tech.enums.QuestionOption;
import com.doping.tech.model.record.AnswerRecord;
import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface AnswerMapper {

    List<AnswerRecord> toRecord(List<Answer> answers);

    @Mapping(target = "status", expression = "java(Boolean.TRUE)")
    @Mapping(target = "createdDate", expression = "java(localDateTimeNow())")
    @Mapping(target = "updatedDate", expression = "java(localDateTimeNow())")
    @Mapping(target = "id", ignore = true)
    Answer toEntity(Question question, Student student, QuestionOption questionAnswer);

    @AfterMapping
    default void mapCorrectAnswer(@MappingTarget Answer answer, Question question, QuestionOption questionAnswer) {
        answer.setAnswerIsCorrect(questionAnswer.equals(question.getAnswer()));
    }

    default LocalDateTime localDateTimeNow() {
        return LocalDateTime.now();
    }
}
