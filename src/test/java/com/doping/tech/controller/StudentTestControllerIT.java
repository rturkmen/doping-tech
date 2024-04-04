package com.doping.tech.controller;

import com.doping.tech.config.BaseIT;
import com.doping.tech.enums.QuestionOption;
import com.doping.tech.enums.ResponseStatus;
import com.doping.tech.model.record.QuestionRecord;
import com.doping.tech.model.request.TestAnswerRequest;
import com.doping.tech.model.response.AnswerResponse;
import com.doping.tech.model.response.BaseResponse;
import com.doping.tech.model.response.StudentResponse;
import com.doping.tech.model.response.StudentTestResponse;
import com.doping.tech.repository.StudentRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

import static com.doping.tech.enums.QuestionOption.OPTION1;
import static com.doping.tech.enums.QuestionOption.OPTION2;
import static com.doping.tech.enums.QuestionOption.OPTION3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS, scripts = "classpath:sql/data.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS, scripts = "classpath:sql/data-revert.sql")
@Disabled //TODO
class StudentTestControllerIT extends BaseIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    StudentRepository studentRepository;

    @Test
    void should_get_student_and_test_with_id() {
        //when
        var testId = 3L;
        var studentId = 4L;
        var builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + serverPort + "/student-test/" + studentId + "/" + testId);

        var response = testRestTemplate.getForEntity(builder.build().encode().toUri(), AnswerResponse.class);

        //then

        var body = response.getBody();
        assertNotNull(body);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(AnswerResponse.class);
        assertEquals(body.getStatus(), ResponseStatus.SUCCESS.getValue());
        assertEquals(response.getBody().getCode(), HttpStatus.OK.value());
        assertThat(body.getAnswers()).isNotEmpty()
                .hasSize(3)
                .extracting("question", "questionAnswer")
                .contains(
                        tuple(new QuestionRecord("question-text", "OPTION1-text", "OPTION2-text", "OPTION3-text", "OPTION4-text", OPTION1), OPTION1),
                        tuple(new QuestionRecord("question-text2", "OPTION1-text", "OPTION2-text", "OPTION3-text", "OPTION4-text", OPTION2), OPTION2),
                        tuple(new QuestionRecord("question-text3", "OPTION1-text", "OPTION2-text", "OPTION3-text", "OPTION4-text", OPTION3), OPTION3));
    }

    @Test
    void should_not_get_test_by_student_id() {
        //given
        var studentId = 4L;
        //when
        var builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + serverPort + "/student-test/" + studentId);

        var response = testRestTemplate.getForEntity(builder.build().encode().toUri(), StudentTestResponse.class);

        //then
        var body = response.getBody();
        assertNotNull(body);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(StudentTestResponse.class);
        assertEquals(body.getStatus(), ResponseStatus.FAILURE.getValue());
    }

    @Test
    void should_answer_question() {
        //given
        var request = new TestAnswerRequest();
        var questionMap = new HashMap<Long, QuestionOption>();
        request.setStudentId(4L);
        request.setTestId(4L);
        questionMap.put(10L, OPTION1);
        questionMap.put(11L, OPTION2);
        questionMap.put(12L, OPTION3);
        request.setAnswers(questionMap);

        //when
        var builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + serverPort + "/student-test/question/answer");

        var response = testRestTemplate.postForEntity(builder.build().encode().toUri(), request, BaseResponse.class);

        //then
        var body = response.getBody();
        assertNotNull(body);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(BaseResponse.class);
        assertEquals(body.getStatus(), ResponseStatus.SUCCESS.getValue());
        assertEquals(response.getBody().getCode(), HttpStatus.OK.value());
    }

}
