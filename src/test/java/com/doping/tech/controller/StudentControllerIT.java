package com.doping.tech.controller;

import com.doping.tech.config.BaseIT;
import com.doping.tech.enums.ResponseStatus;
import com.doping.tech.model.response.StudentResponse;
import com.doping.tech.model.response.StudentsResponse;
import com.doping.tech.repository.StudentRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.util.UriComponentsBuilder;

import static com.doping.tech.enums.Gender.MALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS, scripts = "classpath:sql/data.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS, scripts = "classpath:sql/data-revert.sql")
@Disabled //TODO
class StudentControllerIT extends BaseIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    StudentRepository studentRepository;

    @Test
    void should_get_all_students() {
        //when
        var builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + serverPort + "/student");

        var response = testRestTemplate.getForEntity(builder.build().encode().toUri(), StudentsResponse.class);

        //then

        var body = response.getBody();
        assertNotNull(body);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(StudentsResponse.class);
        assertEquals(body.getStatus(), ResponseStatus.SUCCESS.getValue());
        assertEquals(response.getBody().getCode(), HttpStatus.OK.value());
        assertThat(body.getStudents()).isNotEmpty()
                .hasSize(2)
                .extracting("id", "name", "surname", "gender", "studentNumber")
                .contains(
                        tuple(1L, "John", "Wick", MALE, "12345"),
                        tuple(2L, "John", "Doe", MALE, "12346")
                );
    }

    @Test
    void should_get_student_by_id() {
        //given
        var studentId = 1L;
        //when
        var builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + serverPort + "/student/" + studentId);

        var response = testRestTemplate.getForEntity(builder.build().encode().toUri(), StudentResponse.class);

        //then
        var body = response.getBody();
        assertNotNull(body);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(StudentResponse.class);
        assertEquals(body.getStatus(), ResponseStatus.SUCCESS.getValue());
        assertEquals(response.getBody().getCode(), HttpStatus.OK.value());
        assertNotNull(body.getStudent());
    }

    @Test
    void should_remove_student_by_id() {
        //given
        var studentId = 3L;
        //when
        var builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + serverPort + "/student/" + studentId);

        testRestTemplate.delete(builder.build().encode().toUri());

        //then
        var optionalStudent = studentRepository.findById(studentId);
        assertTrue(optionalStudent.isEmpty());
    }

}
