package com.doping.tech.service;

import com.doping.tech.entity.Student;
import com.doping.tech.exception.BusinessException;
import com.doping.tech.mappers.StudentMapper;
import com.doping.tech.model.record.StudentRecord;
import com.doping.tech.model.record.StudentTestsRecord;
import com.doping.tech.model.request.StudentRequest;
import com.doping.tech.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.doping.tech.enums.ErrorDescription.STUDENT_NOT_FOUND;
import static com.doping.tech.enums.ErrorDescription.STUDENT_NOT_FOUND_WITH_NUMBER;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Cacheable(value = "students")
    public List<StudentRecord> findAll() {
        var students = studentRepository.findAll();
        return studentMapper.mapEntitiesToRecords(students);
    }

    @Cacheable(value = "student", key = "#id")
    public StudentRecord findById(Long id) {
        var student = studentRepository.findById(id)
                .orElseThrow(() -> new BusinessException(STUDENT_NOT_FOUND));
        return studentMapper.mapEntityToRecord(student);
    }

    @CacheEvict(value = "students", key = "#request.studentNumber")
    public StudentRecord addStudent(StudentRequest request) {
        var studentValue = studentMapper.mapRequestToEntity(request);
        var student = studentRepository.save(studentValue);
        return studentMapper.mapEntityToRecord(student);
    }

    public Student findStudentEntityWithId(Long id) {
        return studentRepository.findByIdAndStatus(id, true)
                .orElseThrow(() -> new BusinessException(STUDENT_NOT_FOUND_WITH_NUMBER));
    }

    public StudentTestsRecord findStudentTestRecord(Long studentId) {
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new BusinessException(STUDENT_NOT_FOUND));
        return studentMapper.mapEntityToTestRecord(student);
    }

    @CacheEvict(value = "student", key = "#id")
    @Transactional
    public void removeStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
