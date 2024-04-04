package com.doping.tech.service.manager;

import com.doping.tech.model.request.TestCreateRequest;
import com.doping.tech.model.response.BaseResponse;
import com.doping.tech.model.response.TestResponse;
import com.doping.tech.model.response.TestsResponse;
import com.doping.tech.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TestManager {

    private final TestService testService;

    public TestsResponse findAll() {
        log.info("Getting all tests");
        var testRecords = testService.findAll();
        log.info("All tests found");
        return new TestsResponse(testRecords);
    }

    public TestResponse findById(Long id) {
        log.info("Searching test with id: {}", id);
        var testRecord = testService.findById(id);
        log.info("Test found with id: {} and name: {}", id, testRecord.name());
        return new TestResponse(testRecord);
    }

    public BaseResponse createTest(TestCreateRequest request) {
        log.info("Test creation process started");
        testService.createTest(request);
        log.info("Test creation process ended");
        return new BaseResponse();
    }

}
