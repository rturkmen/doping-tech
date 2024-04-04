package com.doping.tech.service;

import com.doping.tech.entity.Answer;
import com.doping.tech.enums.QuestionOption;
import com.doping.tech.exception.BusinessException;
import com.doping.tech.mappers.AnswerMapper;
import com.doping.tech.model.record.AnswerRecord;
import com.doping.tech.model.record.StudentTestsRecord;
import com.doping.tech.model.request.TestAnswerRequest;
import com.doping.tech.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.doping.tech.enums.ErrorDescription.STUDENT_ANSWERS_NOT_FOUND;
import static com.doping.tech.enums.ErrorDescription.STUDENT_TEST_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class StudentTestService {

    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final StudentService studentService;
    private final TestService testService;

    @CacheEvict(value = "studentAnswers", key = "#request.studentId")
    public void answerQuestion(TestAnswerRequest request) {
        var student = studentService.findStudentEntityWithId(request.getStudentId());
        var test = testService.findTest(request.getTestId());
        var answers = new ArrayList<Answer>();
        test.getStudents().add(student);
        test.getQuestions().forEach(question -> {
            var questionAnswer = request.getAnswers().getOrDefault(question.getId(), QuestionOption.NONE);
            var answer = answerMapper.toEntity(question, student, questionAnswer);
            answers.add(answer);
        });
        answerRepository.saveAll(answers);
    }

    @Cacheable(value = "studentAnswers", key = "#studentId + ':' + #testId")
    public List<AnswerRecord> getStudentAnswers(Long studentId, Long testId) {
        var answers = answerRepository.findByStudentIdAndQuestionTestId(studentId, testId);
        if (CollectionUtils.isEmpty(answers))
            throw new BusinessException(STUDENT_ANSWERS_NOT_FOUND);
        return answerMapper.toRecord(answers);
    }

    @Cacheable(value = "studentTests", keyGenerator = "customKeyGenerator")
    public StudentTestsRecord getStudentTests(Long studentId) {
        var studentTest = studentService.findStudentTestRecord(studentId);
        if (CollectionUtils.isEmpty(studentTest.tests()))
            throw new BusinessException(STUDENT_TEST_NOT_FOUND);
        return studentTest;
    }

}

