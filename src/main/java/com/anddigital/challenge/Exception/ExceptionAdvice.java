package com.anddigital.challenge.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionAdvice {

    @ControllerAdvice
    class CustomerNotFoundExceptionAdvice {

        @ResponseBody
        @ExceptionHandler(ResourceNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        private String resourceNotFoundHandler(ResourceNotFoundException ex) {
            return ex.getMessage();
        }
    }
}
