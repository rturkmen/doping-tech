package com.doping.tech.service.manager;

import com.doping.tech.config.BaseUnitTest;
import com.doping.tech.enums.ResponseStatus;
import com.doping.tech.model.record.QuestionRecord;
import com.doping.tech.model.record.TestRecord;
import com.doping.tech.model.request.TestCreateRequest;
import com.doping.tech.model.response.TestResponse;
import com.doping.tech.model.response.TestsResponse;
import com.doping.tech.service.TestService;
import com.doping.tech.service.manager.TestManager;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static com.doping.tech.enums.QuestionOption.OPTION1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class TestManagerTest extends BaseUnitTest {

    @Mock
    TestService testService;
    @InjectMocks
    TestManager testManager;

    @Test
    void should_find_all_tests() {
        var expected = new TestsResponse();
        var testRecords = List.of(new TestRecord(1L, "test", List.of(new QuestionRecord("question-text", "OPTION1-text", "OPTION2-text", "OPTION3-text", "OPTION4-text", OPTION1))));
        expected.setTests(testRecords);

        when(testService.findAll()).thenReturn(testRecords);

        var response = testManager.findAll();
        assertEquals(expected.getTests().size(), response.getTests().size());
        assertEquals(expected.getTests(), response.getTests());
    }

    @Test
    void should_find_test_by_id() {
        var expected = new TestResponse();
        var record = new TestRecord(1L, "test", List.of(new QuestionRecord("question-text", "OPTION1-text", "OPTION2-text", "OPTION3-text", "OPTION4-text", OPTION1)));
        expected.setTest(record);

        when(testService.findById(anyLong())).thenReturn(record);

        var response = testManager.findById(1L);
        assertEquals(expected.getTest(), response.getTest());
    }

    @Test
    void should_successfully_create_student() {
        var request = new TestCreateRequest();

        var response = testManager.createTest(request);
        verify(testService, times(1)).createTest(request);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS.getValue());
    }

}
