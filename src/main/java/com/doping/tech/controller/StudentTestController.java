package com.doping.tech.controller;

import com.doping.tech.model.request.TestAnswerRequest;
import com.doping.tech.model.response.AnswerResponse;
import com.doping.tech.model.response.BaseResponse;
import com.doping.tech.model.response.StudentTestResponse;
import com.doping.tech.service.manager.StudentTestManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Student Test Api", description = "You can answer a question and get your answers on applied tests")
@RestController
@RequiredArgsConstructor
@RequestMapping("student-test")
public class StudentTestController {

    private final StudentTestManager studentTestManager;

    @Operation(
            summary = "Answer Question",
            description = "Answer a question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @PostMapping("question/answer")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse> answerQuestion(@Valid @RequestBody TestAnswerRequest request) {
        return ResponseEntity.ok(studentTestManager.answerQuestion(request));
    }


    @Operation(
            summary = "Student Answers",
            description = "Get your answers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @GetMapping("{studentId}/{testId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AnswerResponse> studentAnswers(@PathVariable Long studentId, @PathVariable Long testId) {
        return ResponseEntity.ok(studentTestManager.answersByStudentAndTestId(studentId, testId));
    }


    @Operation(
            summary = "Student Tests",
            description = "Get your tests")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @GetMapping("{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StudentTestResponse> studentTests(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentTestManager.getStudentTests(studentId));
    }
}
