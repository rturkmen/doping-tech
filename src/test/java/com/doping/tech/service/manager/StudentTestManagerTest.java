package com.doping.tech.service.manager;

import com.doping.tech.config.BaseUnitTest;
import com.doping.tech.enums.QuestionOption;
import com.doping.tech.enums.ResponseStatus;
import com.doping.tech.model.record.AnswerRecord;
import com.doping.tech.model.record.QuestionRecord;
import com.doping.tech.model.record.StudentTestsRecord;
import com.doping.tech.model.request.TestAnswerRequest;
import com.doping.tech.model.response.AnswerResponse;
import com.doping.tech.model.response.StudentTestResponse;
import com.doping.tech.service.StudentTestService;
import com.doping.tech.service.manager.StudentTestManager;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static com.doping.tech.enums.QuestionOption.OPTION1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class StudentTestManagerTest extends BaseUnitTest {

    @Mock
    StudentTestService studentTestService;
    @InjectMocks
    StudentTestManager studentTestManager;

    @Test
    void should_student_get_all_answers() {
        var expected = new AnswerResponse();
        var record = new AnswerRecord(new QuestionRecord("question-text", "OPTION1-text", "OPTION2-text", "OPTION3-text", "OPTION4-text", OPTION1), QuestionOption.OPTION1);
        expected.setAnswers(List.of(record));

        when(studentTestService.getStudentAnswers(anyLong(), anyLong())).thenReturn(expected.getAnswers());

        var response = studentTestManager.answersByStudentAndTestId(1L, 1L);

        assertEquals(expected.getAnswers().size(), response.getAnswers().size());
        assertEquals(expected.getAnswers(), response.getAnswers());
    }

    @Test
    void should_student_get_all_test() {
        var expected = new StudentTestResponse();
        var record = new StudentTestsRecord(1L, Collections.emptyList());
        expected.setStudentTest(record);

        when(studentTestService.getStudentTests(anyLong())).thenReturn(record);

        var response = studentTestManager.getStudentTests(1L);

        assertEquals(expected.getStudentTest(), response.getStudentTest());
    }

    @Test
    void should_answer_question() {
        var request = new TestAnswerRequest();

        var response = studentTestManager.answerQuestion(request);

        verify(studentTestService, times(1)).answerQuestion(request);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS.getValue());
    }


}
