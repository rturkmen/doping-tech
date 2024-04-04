package com.doping.tech.model.record;

import java.io.Serializable;
import java.util.List;

public record StudentTestsRecord(Long studentId,
                                 List<TestRecord> tests) implements Serializable {
}
