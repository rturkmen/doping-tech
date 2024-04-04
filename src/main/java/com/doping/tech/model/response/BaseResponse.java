package com.doping.tech.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static com.doping.tech.enums.ResponseStatus.FAILURE;
import static com.doping.tech.enums.ResponseStatus.SUCCESS;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {

    private String status;
    private int code;
    private String message;
    private LocalDateTime time;

    public BaseResponse() {
        this.status = SUCCESS.getValue();
        this.time = LocalDateTime.now();
        this.code = HttpStatus.OK.value();
    }

    public static BaseResponse error(int code, String message) {
        return BaseResponse.builder()
                .code(code)
                .time(LocalDateTime.now())
                .message(message)
                .status(FAILURE.getValue())
                .build();
    }

}
