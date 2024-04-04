package com.doping.tech.exception;

import com.doping.tech.enums.ErrorDescription;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private int code;
    private String message;

    public BusinessException(ErrorDescription errorDescription) {
        super(errorDescription.getMessage());
        this.message = errorDescription.getMessage();
        this.code = errorDescription.getCode();

    }
}
