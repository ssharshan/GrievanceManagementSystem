package com.scaler.greivance.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionDTO {
    private HttpStatus errorCode;
    private String message;

    public ExceptionDTO(HttpStatus httpStatus, String message) {
        this.errorCode = httpStatus;
        this.message = message;
    }
}
