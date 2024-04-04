package com.doping.tech.service;

import com.doping.tech.config.BaseUnitTest;
import com.doping.tech.entity.Answer;
import com.doping.tech.entity.Question;
import com.doping.tech.entity.Student;
import com.doping.tech.enums.Gender;
import com.doping.tech.enums.QuestionOption;
import com.doping.tech.exception.BusinessException;
import com.doping.tech.mappers.AnswerMapper;
import com.doping.tech.mappers.StudentMapper;
import com.doping.tech.model.record.StudentRecord;
import com.doping.tech.model.record.StudentTestsRecord;
import com.doping.tech.model.request.StudentRequest;
import com.doping.tech.model.request.TestAnswerRequest;
import com.doping.tech.repository.AnswerRepository;
import com.doping.tech.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StudentTestServiceTest extends BaseUnitTest {

    @Mock
    AnswerRepository answerRepository;
    @Mock
    AnswerMapper answerMapper;
    @Mock
    StudentService studentService;
    @Mock
    TestService testService;

    @InjectMocks
    StudentTestService studentTestService;

    @Test
    void should_answer_question() {
        var request = new TestAnswerRequest();
        request.setStudentId(1L);
        request.setTestId(1L);
        var map = new HashMap<Long, QuestionOption>();
        map.put(1L, QuestionOption.OPTION1);
        request.setAnswers(map);
        var answer = new Answer();
        var answerList = List.of(answer);
        var student = new Student();
        var question = new Question();
        question.setId(1L);
        var questions = new ArrayList<Question>();
        questions.add(question);
        var students = new ArrayList<Student>();
        students.add(student);
        var test = new com.doping.tech.entity.Test();
        test.setQuestions(questions);
        test.setStudents(students);


        when(studentService.findStudentEntityWithId(anyLong())).thenReturn(student);
        when(testService.findTest(anyLong())).thenReturn(test);

        when(answerMapper.toEntity(any(), any(), any())).thenReturn(answer);

        studentTestService.answerQuestion(request);

        verify(answerRepository, times(1)).saveAll(answerList);
    }


}
