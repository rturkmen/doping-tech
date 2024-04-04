package com.doping.tech.controller;

import com.doping.tech.model.request.StudentRequest;
import com.doping.tech.model.response.BaseResponse;
import com.doping.tech.model.response.StudentResponse;
import com.doping.tech.model.response.StudentsResponse;
import com.doping.tech.service.manager.StudentManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Student Api", description = "You can search,add and remove a student with this api")
@RestController
@RequiredArgsConstructor
@RequestMapping("student")
public class StudentController {

    private final StudentManager manager;

    @Operation(
            summary = "Fetch all students",
            description = "Fetch all students from system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StudentsResponse> findAll() {
        return ResponseEntity.ok(manager.findAll());
    }


    @Operation(
            summary = "Fetch a student",
            description = "Fetch student via id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StudentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(manager.findById(id));
    }

    @Operation(
            summary = "Add a student",
            description = "You can add a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StudentResponse> addStudent(@Valid @RequestBody StudentRequest studentRequest) {
        return ResponseEntity.ok(manager.addStudent(studentRequest));
    }

    @Operation(
            summary = "Remove a student",
            description = "You can remove a student via id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse> removeStudent(@PathVariable Long id) {
        return ResponseEntity.ok(manager.removeStudent(id));
    }
}
