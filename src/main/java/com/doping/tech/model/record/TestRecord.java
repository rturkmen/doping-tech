package com.doping.tech.model.record;

import java.io.Serializable;
import java.util.List;

public record TestRecord(Long id,
                         String name,
                         List<QuestionRecord> questions) implements Serializable {
}
