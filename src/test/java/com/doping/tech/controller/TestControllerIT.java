package com.doping.tech.controller;

import com.doping.tech.config.BaseIT;
import com.doping.tech.enums.ResponseStatus;
import com.doping.tech.model.dto.QuestionDto;
import com.doping.tech.model.request.TestCreateRequest;
import com.doping.tech.model.response.BaseResponse;
import com.doping.tech.model.response.TestResponse;
import com.doping.tech.model.response.TestsResponse;
import com.doping.tech.repository.TestRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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
class TestControllerIT extends BaseIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    TestRepository testRepository;

    @Test
    void should_get_all_test() {

        //when
        var builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + serverPort + "/test");

        var response = testRestTemplate.getForEntity(builder.build().encode().toUri(), TestsResponse.class);

        //then

        var body = response.getBody();
        assertNotNull(body);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(TestsResponse.class);
        assertEquals(body.getStatus(), ResponseStatus.SUCCESS.getValue());
        assertEquals(response.getBody().getCode(), HttpStatus.OK.value());
        assertThat(body.getTests()).isNotEmpty()
                .hasSize(2)
                .extracting("id", "name")
                .contains(
                        tuple(1L, "test1"),
                        tuple(2L, "test2")
                );
    }

    @Test
    void should_get_test_by_id() {
        //given
        var testId = 1L;
        //when
        var builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + serverPort + "/test/" + testId);

        var response = testRestTemplate.getForEntity(builder.build().encode().toUri(), TestResponse.class);

        //then

        var body = response.getBody();
        assertNotNull(body);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(TestResponse.class);
        assertEquals(body.getStatus(), ResponseStatus.SUCCESS.getValue());
        assertEquals(response.getBody().getCode(), HttpStatus.OK.value());
        assertNotNull(body.getTest());
        assertThat(body.getTest().questions()).isNotEmpty()
                .hasSize(3)
                .extracting("text", "option1", "option2", "option3", "option4", "answer")
                .contains(
                        tuple("question-text", "OPTION1-text", "OPTION2-text", "OPTION3-text", "OPTION4-text", OPTION1),
                        tuple("question-text2", "OPTION1-text", "OPTION2-text", "OPTION3-text", "OPTION4-text", OPTION2),
                        tuple("question-text3", "OPTION1-text", "OPTION2-text", "OPTION3-text", "OPTION4-text", OPTION3)

                );
    }

    @Test
    void should_create_test() {
        var testCreateRequest = new TestCreateRequest();
        var questionDto = new QuestionDto();
        questionDto.setAnswer("OPTION2");
        questionDto.setOption1("option1-question");
        questionDto.setOption2("option2-question");
        questionDto.setOption3("option3-question");
        questionDto.setOption4("option4-question");
        questionDto.setText("question-text");
        testCreateRequest.setName("test101");
        testCreateRequest.setQuestions(List.of(questionDto));

        //when
        var builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + serverPort + "/test");

        var response = testRestTemplate.postForEntity(builder.build().encode().toUri(), testCreateRequest, BaseResponse.class);

        //then
        var optionalTest = testRepository.findByName("test101");

        var body = response.getBody();
        assertNotNull(body);
        assertThat(optionalTest).isPresent();
        assertEquals("test101", optionalTest.get().getName());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(BaseResponse.class);
        assertEquals(body.getStatus(), ResponseStatus.SUCCESS.getValue());
        assertEquals(response.getBody().getCode(), HttpStatus.OK.value());
    }
}
