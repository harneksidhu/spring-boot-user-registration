package com.user.registration.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@ControllerAdvice  
@RestController
public class GlobalExceptionHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)  
    @ExceptionHandler(value = BaseApiException.class)  
    public String handleBaseException(BaseApiException e){  
        return e.getMessage();  
    }  
}
