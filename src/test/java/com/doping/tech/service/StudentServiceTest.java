package com.doping.tech.service;

import com.doping.tech.config.BaseUnitTest;
import com.doping.tech.entity.Student;
import com.doping.tech.enums.Gender;
import com.doping.tech.exception.BusinessException;
import com.doping.tech.mappers.StudentMapper;
import com.doping.tech.mappers.TestMapper;
import com.doping.tech.model.record.QuestionRecord;
import com.doping.tech.model.record.StudentRecord;
import com.doping.tech.model.record.StudentTestsRecord;
import com.doping.tech.model.record.TestRecord;
import com.doping.tech.model.request.StudentRequest;
import com.doping.tech.model.request.TestCreateRequest;
import com.doping.tech.repository.StudentRepository;
import com.doping.tech.repository.TestRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.doping.tech.enums.QuestionOption.OPTION1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StudentServiceTest extends BaseUnitTest {

    @Mock
    StudentRepository studentRepository;
    @Mock
    StudentMapper studentMapper;
    @InjectMocks
    StudentService studentService;

    @Test
    void should_retrieve_all_students() {
        var studentEntities = List.of(new Student());
        var studentRecord = new StudentRecord(1L, "john", "doe", Gender.MALE, "1234", Collections.emptyList());
        var expected = List.of(studentRecord);

        when(studentRepository.findAll()).thenReturn(studentEntities);
        when(studentMapper.mapEntitiesToRecords(anyList())).thenReturn(expected);

        var response = studentService.findAll();

        assertEquals(expected, response);
    }

    @Test
    void should_not_retrieve_student_record_with_id() {

        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> studentService.findById(1L));
    }

    @Test
    void should_not_retrieve_student_test_with_id() {

        when(studentRepository.findByIdAndStatus(anyLong(), anyBoolean())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> studentService.findStudentEntityWithId(1L));
    }

    @Test
    void should_retrieve_student_record() {
        var optionalStudent = Optional.of(new Student());
        var expected = new StudentRecord(1L, "john", "doe", Gender.MALE, "1234", Collections.emptyList());

        when(studentRepository.findById(anyLong())).thenReturn(optionalStudent);
        when(studentMapper.mapEntityToRecord(optionalStudent.get())).thenReturn(expected);

        var response = studentService.findById(1L);

        assertEquals(expected, response);
    }

    @Test
    void should_retrieve_single_student_test_record() {
        var entity = new Student();
        var expected = new StudentTestsRecord(1L, Collections.emptyList());

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(studentMapper.mapEntityToTestRecord(any())).thenReturn(expected);

        var response = studentService.findStudentTestRecord(1L);

        assertEquals(expected, response);
    }

    @Test
    void should_not_retrieve_single_student_test_record() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> studentService.findStudentTestRecord(1L));
    }

    @Test
    void should_remove_student() {

        studentService.removeStudent(1L);

        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    void should_add_student() {
        var student = new Student();
        var studentRecord = new StudentRecord(1L, "john", "doe", Gender.MALE, "1234", Collections.emptyList());

        when(studentMapper.mapRequestToEntity(any())).thenReturn(student);
        when(studentMapper.mapEntityToRecord(any())).thenReturn(studentRecord);

        var response = studentService.addStudent(new StudentRequest());

        verify(studentRepository,times(1)).save(student);
        assertEquals(studentRecord, response);
    }

}
