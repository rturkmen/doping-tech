package com.doping.tech.service;

import com.doping.tech.entity.Test;
import com.doping.tech.exception.BusinessException;
import com.doping.tech.mappers.TestMapper;
import com.doping.tech.model.record.TestRecord;
import com.doping.tech.model.request.TestCreateRequest;
import com.doping.tech.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.doping.tech.enums.ErrorDescription.TEST_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {

    private final TestRepository testRepository;
    private final TestMapper testMapper;

    @Cacheable(value = "tests")
    public List<TestRecord> findAll() {
        var testEntities = testRepository.findAll();
        return testEntities.stream().map(testMapper::mapEntityToRecord).toList();
    }

    @Cacheable(value = "testRecord", key = "#id")
    public TestRecord findById(Long id) {
        var test = testRepository.findById(id)
                .orElseThrow(() -> new BusinessException(TEST_NOT_FOUND));
        return testMapper.mapEntityToRecord(test);
    }

    public Test findTest(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new BusinessException(TEST_NOT_FOUND));
    }

    @CacheEvict(value = "tests")
    public void createTest(TestCreateRequest test) {
        var testEntity = testMapper.mapRequestToTest(test);
        testRepository.save(testEntity);
    }
}
