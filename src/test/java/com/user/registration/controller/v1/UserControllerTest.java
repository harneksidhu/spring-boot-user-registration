package com.user.registration.controller.v1;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.user.registration.controller.exception.BaseApiException;
import com.user.registration.model.User;
import com.user.registration.repository.UserRepository;

public class UserControllerTest {

	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserRepository userRepository;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testUserLogin(){
		User user = new User();
		user.setUserName("testUser");
		user.setPassword("testPassword1");
		when(userRepository.findOneByUserNameAndPassword(user.getUserName(), user.getPassword())).
			thenReturn(null);
		ResponseEntity<String> response = userController.login(user);
		assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
		assertThat(response.getBody(), is(nullValue()));
	}
	
	@Test
	public void testUserRegistration(){
		User user = new User();
		user.setUserName("testUser");
		user.setPassword("testPassword1");
		try {
			userController.register(user);
		} catch (BaseApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
