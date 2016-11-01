package com.user.registration.exception;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.user.registration.controller.exception.BaseApiException;
import com.user.registration.controller.exception.GlobalExceptionHandler;
import com.user.registration.model.ApiExceptionMessage;
import com.user.registration.model.User;


public class GlobalExceptionHandlerTest {
	
	@InjectMocks
	private GlobalExceptionHandler globalExceptionHandler;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testHandleApiException(){
		String msg = "Exception";
		BaseApiException bae = new BaseApiException(msg);
		ApiExceptionMessage aem = globalExceptionHandler.handleApiException(bae);
		assertThat(aem.getMessage(), is(msg));
	}
	
	@Test
	public void testHandleException(){
		Exception e = new Exception();
		ApiExceptionMessage aem = globalExceptionHandler.handleException(e);
		assertThat(aem.getMessage(), is("Internal Error Occured"));
	}
}
