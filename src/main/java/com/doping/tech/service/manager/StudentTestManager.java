package com.doping.tech.service.manager;

import com.doping.tech.model.request.TestAnswerRequest;
import com.doping.tech.model.response.AnswerResponse;
import com.doping.tech.model.response.BaseResponse;
import com.doping.tech.model.response.StudentTestResponse;
import com.doping.tech.service.StudentTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentTestManager {

    private final StudentTestService studentTestService;

    public BaseResponse answerQuestion(TestAnswerRequest request) {
        log.info("Student which id: {} answering to tests", request.getStudentId());
        studentTestService.answerQuestion(request);
        log.info("Student answers submitted");
        return new BaseResponse();
    }

    public AnswerResponse answersByStudentAndTestId(Long studentId, Long testId) {
        log.info("Student which id: {} getting answers for test: {}", studentId, testId);
        var answers = studentTestService.getStudentAnswers(studentId, testId);
        log.info("Student got test answers");
        return new AnswerResponse(answers);
    }

    public StudentTestResponse getStudentTests(Long studentId) {
        log.info("Student which id: {} getting tests ", studentId);
        var studentTest = studentTestService.getStudentTests(studentId);
        log.info("Getting tests process finished");
        return new StudentTestResponse(studentTest);
    }

}
