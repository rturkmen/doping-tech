package com.doping.tech.controller;

import com.doping.tech.model.request.TestCreateRequest;
import com.doping.tech.model.response.BaseResponse;
import com.doping.tech.model.response.TestResponse;
import com.doping.tech.model.response.TestsResponse;
import com.doping.tech.service.manager.TestManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Test Api", description = "You can add,search test with this api")
@RestController
@RequiredArgsConstructor
@RequestMapping("test")
public class TestController {

    private final TestManager manager;

    @Operation(
            summary = "Fetch all tests",
            description = "Fetch all tests from system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TestsResponse> findAll() {
        return ResponseEntity.ok(manager.findAll());
    }

    @Operation(
            summary = "Fetch test",
            description = "Fetch test via id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TestResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(manager.findById(id));
    }


    @Operation(
            summary = "Create test",
            description = "You could start test creation process")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse> createTest(@Valid @RequestBody TestCreateRequest request) {
        return ResponseEntity.ok(manager.createTest(request));
    }
}
