package com.doping.tech.entity;

import com.doping.tech.entity.converter.QuestionOptionConverter;
import com.doping.tech.enums.QuestionOption;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Question extends BaseEntity {

    private String text;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    @Convert(converter = QuestionOptionConverter.class)
    private QuestionOption answer;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;

}
