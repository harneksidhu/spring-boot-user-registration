package com.user.registration.controller.v1;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.user.registration.SpringBootUserRegistrationApplication;
import com.user.registration.model.ApiExceptionMessage;
import com.user.registration.model.User;
import com.user.registration.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=SpringBootUserRegistrationApplication.class, webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserControllerWebIntegrationTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void init(){
		userRepository.deleteAll();
	}
	
	@Test
	public void testRegistration(){
		TestRestTemplate restTemplate = new TestRestTemplate();
	    User user = new User();
	    user.setUserName("userTest");
	    user.setPassword("userPassword1");
	    ResponseEntity<User> response = restTemplate.postForEntity("http://localhost:9000/v1/register", user, User.class);
	    User returnedUser = response.getBody();
	    assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
	    assertThat(returnedUser.getUserName(), is(user.getUserName()));
	    assertThat(returnedUser.getPassword(), is(user.getPassword()));	    
	}
	
	@Test
	public void testRegistrationInvalid(){
		TestRestTemplate restTemplate = new TestRestTemplate();
	    User user = new User();
	    user.setUserName("userTest");
	    user.setPassword("userPassword");
	    ResponseEntity<ApiExceptionMessage> response = restTemplate.postForEntity("http://localhost:9000/v1/register", user, ApiExceptionMessage.class);
	    ApiExceptionMessage returnedMessage = response.getBody();
	    assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	    assertThat(returnedMessage.getMessage(), is("Invalid username and password"));
	}
	
	@Test
	public void testRegistrationIdempotency(){
		TestRestTemplate restTemplate = new TestRestTemplate();
	    User user = new User();
	    user.setUserName("userTest");
	    user.setPassword("userPassword1");
	    userRepository.save(user);
	    ResponseEntity<User> response = restTemplate.postForEntity("http://localhost:9000/v1/register", user, User.class);
	    User returnedUser = response.getBody();
	    assertThat(response.getStatusCode(), is(HttpStatus.OK));
	    assertThat(returnedUser.getUserName(), is(user.getUserName()));
	    assertThat(returnedUser.getPassword(), is(user.getPassword()));	    
	}
	
	@Test
	public void testLoginNotFound(){
		TestRestTemplate restTemplate = new TestRestTemplate();
	    User user = new User();
	    user.setUserName("userTest");
	    user.setPassword("userPassword1");
	    ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9000/v1/login", user, String.class);
	    assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
	    assertThat(response.getBody(), is(nullValue()));
	}
	
	@Test
	public void testLoginUserExists(){
		TestRestTemplate restTemplate = new TestRestTemplate();
	    User user = new User();
	    user.setUserName("userTest");
	    user.setPassword("userPassword1");
	    userRepository.save(user);
	    ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9000/v1/login", user, String.class);
	    assertThat(response.getStatusCode(), is(HttpStatus.OK));
	    assertThat(response.getBody(), is(nullValue()));
	}
}
