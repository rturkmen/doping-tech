package com.doping.tech.mappers;

import com.doping.tech.entity.Student;
import com.doping.tech.model.record.StudentRecord;
import com.doping.tech.model.record.StudentTestsRecord;
import com.doping.tech.model.request.StudentRequest;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface StudentMapper {

    StudentRecord mapEntityToRecord(Student student);

    List<StudentRecord> mapEntitiesToRecords(List<Student> student);

    Student mapRequestToEntity(StudentRequest request);

    StudentTestsRecord mapEntityToTestRecord(Student student);
}
