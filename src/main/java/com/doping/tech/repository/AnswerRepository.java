package com.doping.tech.repository;

import com.doping.tech.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByStudentIdAndQuestionTestId(Long studentId, Long testId);
}
