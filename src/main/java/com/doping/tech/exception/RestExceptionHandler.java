package com.doping.tech.exception;

import com.doping.tech.model.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.doping.tech.enums.ErrorDescription.COMMON_SYSTEM_ERROR;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<BaseResponse> handleValidationException(BusinessException exception) {
        log.error("Business exception occurred with message: {} and code : {}", exception.getMessage(), exception.getCode());
        return buildResponse(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> log.error("{} shouldn't be empty", fieldError.getField()));
        return buildResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<BaseResponse> handleGeneralException(Exception exception) {
        log.error("Exception occurred with message: {} , exception : ", exception.getMessage(), exception);
        return buildResponse(COMMON_SYSTEM_ERROR.getCode(), COMMON_SYSTEM_ERROR.getMessage());
    }

    private ResponseEntity<BaseResponse> buildResponse(int code, String message) {
        return ResponseEntity.ok(BaseResponse.error(code, message));
    }
}
