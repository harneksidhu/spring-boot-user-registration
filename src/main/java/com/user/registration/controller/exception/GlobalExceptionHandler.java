package com.user.registration.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.user.registration.model.ApiExceptionMessage;

import org.springframework.http.HttpStatus;

@ControllerAdvice  
@RestController
public class GlobalExceptionHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)  
    @ExceptionHandler(value = BaseApiException.class)  
	@ResponseBody
    public ApiExceptionMessage handleBaseException(BaseApiException e){  
		return new ApiExceptionMessage(e.getMessage());
    }

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  
    @ExceptionHandler(value = Exception.class)  
	@ResponseBody
    public ApiExceptionMessage handleException(Exception e){  
		return new ApiExceptionMessage("Internal Error Occured");
    }  
}
