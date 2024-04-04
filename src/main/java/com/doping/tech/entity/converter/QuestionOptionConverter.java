package com.doping.tech.entity.converter;


import com.doping.tech.enums.QuestionOption;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.apache.commons.lang3.EnumUtils;

@Converter
public class QuestionOptionConverter implements AttributeConverter<QuestionOption, String> {

    @Override
    public String convertToDatabaseColumn(QuestionOption value) {
        return value.name();
    }

    @Override
    public QuestionOption convertToEntityAttribute(String value) {
        return EnumUtils.getEnum(QuestionOption.class, value);
    }
}
