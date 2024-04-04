package com.doping.tech.service.manager;

import com.doping.tech.config.BaseUnitTest;
import com.doping.tech.enums.Gender;
import com.doping.tech.model.record.StudentRecord;
import com.doping.tech.model.request.StudentRequest;
import com.doping.tech.model.response.StudentResponse;
import com.doping.tech.model.response.StudentsResponse;
import com.doping.tech.service.StudentService;
import com.doping.tech.service.manager.StudentManager;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class StudentManagerTest extends BaseUnitTest {

    @Mock
    StudentService studentService;
    @InjectMocks
    StudentManager studentManager;

    @Test
    void should_find_all_students() {
        var expected = new StudentsResponse();
        var studentList = List.of(new StudentRecord(1L, "john", "doe", Gender.MALE, "123", Collections.emptyList()));
        expected.setStudents(studentList);

        when(studentService.findAll()).thenReturn(studentList);

        var response = studentManager.findAll();
        assertEquals(expected.getStudents().size(), response.getStudents().size());
        assertEquals(expected.getStudents(), response.getStudents());
    }

    @Test
    void should_find_student_by_id() {
        var expected = new StudentResponse();
        var record = new StudentRecord(1L, "john", "doe", Gender.MALE, "123", Collections.emptyList());
        expected.setStudent(record);

        when(studentService.findById(anyLong())).thenReturn(record);

        var response = studentManager.findById(1L);
        assertEquals(expected.getStudent(), response.getStudent());
    }

    @Test
    void should_successfully_add_student() {
        var expected = new StudentResponse();
        var request = new StudentRequest("john", "doe", Gender.MALE, "1234");
        var record = new StudentRecord(1L, "john", "doe", Gender.MALE, "123", Collections.emptyList());
        expected.setStudent(record);

        when(studentService.addStudent(request)).thenReturn(record);

        var response = studentService.addStudent(request);
        assertEquals(record, response);
    }


    @Test
    void should_remove_student() {
        var studentId = 1L;

        studentManager.removeStudent(studentId);

        verify(studentService, times(1)).removeStudent(studentId);
    }
}
