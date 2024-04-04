package com.doping.tech.service.manager;

import com.doping.tech.model.request.StudentRequest;
import com.doping.tech.model.response.BaseResponse;
import com.doping.tech.model.response.StudentResponse;
import com.doping.tech.model.response.StudentsResponse;
import com.doping.tech.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class StudentManager {

    private final StudentService studentService;

    public StudentsResponse findAll() {
        log.info("Getting all students");
        var students = studentService.findAll();
        log.info("All students found");
        return new StudentsResponse(students);
    }

    public StudentResponse findById(Long id) {
        log.info("Searching student with id: {}", id);
        var student = studentService.findById(id);
        log.info("Student found with number: {} and name: {}", student.studentNumber(), student.name().concat(" ").concat(student.surname()));
        return new StudentResponse(student);
    }

    public StudentResponse addStudent(StudentRequest request) {
        log.info("Student addition process started with student number: {} , name: {}", request.getStudentNumber(), request.getName().concat(" ").concat(request.getSurname()));
        var student = studentService.addStudent(request);
        log.info("Student added");
        return new StudentResponse(student);
    }

    public BaseResponse removeStudent(Long id) {
        log.info("Student removing process started for student id: {}", id);
        studentService.removeStudent(id);
        log.info("Student removed");
        return new BaseResponse();
    }

}
