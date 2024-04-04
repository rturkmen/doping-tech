package com.doping.tech.service;

import com.doping.tech.config.BaseUnitTest;
import com.doping.tech.exception.BusinessException;
import com.doping.tech.mappers.TestMapper;
import com.doping.tech.model.record.QuestionRecord;
import com.doping.tech.model.record.TestRecord;
import com.doping.tech.model.request.TestCreateRequest;
import com.doping.tech.repository.TestRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static com.doping.tech.enums.QuestionOption.OPTION1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TestServiceTest extends BaseUnitTest {

    @Mock
    TestRepository testRepository;
    @Mock
    TestMapper testMapper;
    @InjectMocks
    TestService testService;

    @Test
    void should_retrieve_all_tests() {
        var testEntity = new com.doping.tech.entity.Test();
        testEntity.setName("test");
        var testEntities = List.of(testEntity);
        var testRecord = new TestRecord(1L, "test", List.of(new QuestionRecord("question-text", "OPTION1-text", "OPTION2-text", "OPTION3-text", "OPTION4-text", OPTION1)));
        var expected = List.of(testRecord);

        when(testRepository.findAll()).thenReturn(testEntities);
        when(testMapper.mapEntityToRecord(testEntity)).thenReturn(testRecord);

        var response = testService.findAll();

        assertEquals(expected, response);
    }

    @Test
    void should_retrieve_test_record_with_id() {
        var testEntity = new com.doping.tech.entity.Test();
        testEntity.setName("test");
        var testRecord = new TestRecord(1L, "test", List.of(new QuestionRecord("question-text", "OPTION1-text", "OPTION2-text", "OPTION3-text", "OPTION4-text", OPTION1)));

        when(testRepository.findById(anyLong())).thenReturn(Optional.of(testEntity));
        when(testMapper.mapEntityToRecord(testEntity)).thenReturn(testRecord);

        var response = testService.findById(1L);

        assertEquals(testRecord, response);
    }

    @Test
    void should_retrieve_test_with_id() {
        var testEntity = new com.doping.tech.entity.Test();
        testEntity.setName("test");
        testEntity.setId(1L);

        when(testRepository.findById(anyLong())).thenReturn(Optional.of(testEntity));

        var response = testService.findTest(1L);

        assertEquals(1L, response.getId());
        assertEquals("test", response.getName());
    }

    @Test
    void should_not_retrieve_test_record_with_id() {

        when(testRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> testService.findById(1L));
    }

    @Test
    void should_not_retrieve_test_with_id() {

        when(testRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> testService.findTest(1L));
    }

    @Test
    void should_create_test() {
        var request = new TestCreateRequest();
        var testEntity = new com.doping.tech.entity.Test();

        when(testMapper.mapRequestToTest(request)).thenReturn(testEntity);

        testService.createTest(request);

        verify(testRepository, times(1)).save(testEntity);
    }
}
