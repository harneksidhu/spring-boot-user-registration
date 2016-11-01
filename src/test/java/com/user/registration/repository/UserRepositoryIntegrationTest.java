package com.user.registration.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.user.registration.SpringBootUserRegistrationApplication;
import com.user.registration.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=SpringBootUserRegistrationApplication.class)
public class UserRepositoryIntegrationTest {
	
	@Autowired
	private UserRepository userRepository;
	
	User user;
	
	@Before
	public void init(){
		user = new User();
		user.setUserName("testUser");
		user.setPassword("testPassword1");
		userRepository.save(user);
	}
	
	@After
	public void cleanup(){
		userRepository.deleteAll();
	}
	
	@Test
	public void testFindOneByUserNameAndPassword(){
		User userQuery = userRepository.findOneByUserNameAndPassword(user.getUserName(), user.getPassword());
		assertThat(userQuery.getUserName(), is(user.getUserName()));	
		assertThat(userQuery.getPassword(), is(user.getPassword()));
	}
	
	@Test
	public void testFindOneByUserNameAndPasswordEmpty(){
		User userQuery = userRepository.findOneByUserNameAndPassword("user", "pass");
		assertThat(userQuery, is(nullValue()));	
	}
	
	@Test
	public void testFindByUserName(){
		User userQuery = userRepository.findOneByUserName(user.getUserName());
		assertThat(userQuery.getUserName(), is(user.getUserName()));	
		assertThat(userQuery.getPassword(), is(user.getPassword()));
	}
	
	@Test
	public void testFindByUserNameEmpty(){
		User userQuery = userRepository.findOneByUserName("user");
		assertThat(userQuery, is(nullValue()));	
	}
}
