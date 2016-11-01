package com.user.registration.controller.v1;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
		
	User user;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setUserName("testUser");
		user.setPassword("testPassword1");
	}
	
	@Test
	public void testUserLoginNotFound(){
		when(userRepository.findOneByUserNameAndPassword(user.getUserName(), user.getPassword())).
			thenReturn(null);
		ResponseEntity<String> response = userController.login(user);
		assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
		assertThat(response.getBody(), is(nullValue()));
	}
	
	@Test
	public void testUserLoginFound(){
		when(userRepository.findOneByUserNameAndPassword(user.getUserName(), user.getPassword())).
			thenReturn(user);
		ResponseEntity<String> response = userController.login(user);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getBody(), is(nullValue()));
	}
	
	@Test
	public void testUserRegistrationAlreadyExists(){
		when(userRepository.findOneByUserName(user.getUserName())).
			thenReturn(user);
		ResponseEntity<User> response = userController.register(user);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getBody(), is(user));
	}
	
	@Test
	public void testUserRegistrationNotExist(){
		when(userRepository.findOneByUserName(user.getUserName())).
			thenReturn(null);
		when(userRepository.save(user)).
			thenReturn(user);
		ResponseEntity<User> response = userController.register(user);
		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
		assertThat(response.getBody(), is(user));
	}
	
	@Test(expected=BaseApiException.class)
	public void testUserRegistrationInvalidUserName(){
		User invalidUser = new User();
		invalidUser.setUserName("u");
		invalidUser.setPassword("p");
		when(userRepository.findOneByUserName(invalidUser.getUserName())).
			thenReturn(null);
		userController.register(invalidUser);
	}
	
	@Test
	public void testUserRegistrationExists(){
		when(userRepository.findOneByUserName(user.getUserName())).
			thenReturn(user);
		ResponseEntity<User> response = userController.register(user);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getBody(), is(user));
	}
}
