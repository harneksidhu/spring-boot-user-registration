package com.user.registration.utility;

public final class UserRegistrationUtil {

	public static boolean isUserNameValid(String userName){
		return userName.length()>=5 && userName.matches("^[a-zA-Z0-9]*$");
	}

	public static boolean isPasswordValid(String password){
		return password.length()>=8 && password.matches(".*[0-9].*") && password.matches(".*[A-Z].*") 
				&& password.matches(".*[a-z].*");
	}
}
