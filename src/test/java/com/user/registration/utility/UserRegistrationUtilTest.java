package com.user.registration.utility;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserRegistrationUtilTest {

	@Test
	public void testIsUserNameValid(){
		boolean isUserValid = UserRegistrationUtil.isUserNameValid("userTest123");
		assertThat(isUserValid, is(true));
	}
	
	@Test
	public void testIsUserNameValidIncorrectLength(){
		boolean isUserValid = UserRegistrationUtil.isUserNameValid("user");
		assertThat(isUserValid, is(false));
	}
	
	@Test
	public void testIsUserNameValidIncorrectComplexity(){
		boolean isUserValid = UserRegistrationUtil.isUserNameValid("user@Home");
		assertThat(isUserValid, is(false));
	}
	
	@Test
	public void testIsPasswordValid(){
		boolean isUserValid = UserRegistrationUtil.isPasswordValid("userPassword1");
		assertThat(isUserValid, is(true));
	}
	
	@Test
	public void testIsPasswordValidIncorrectLength(){
		boolean isUserValid = UserRegistrationUtil.isPasswordValid("uA1");
		assertThat(isUserValid, is(false));
	}
	
	@Test
	public void testIsPasswordValidIncorrectComplexity(){
		boolean isUserValid = UserRegistrationUtil.isPasswordValid("userpassword1");
		assertThat(isUserValid, is(false));
	}

}
