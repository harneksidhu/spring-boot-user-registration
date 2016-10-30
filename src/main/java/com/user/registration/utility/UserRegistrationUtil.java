package com.user.registration.utility;

public final class UserRegistrationUtil {

	public static boolean isUserNameValid(String userName){
		if (userName.length()<5 || userName.matches("^[a-zA-Z0-9]*$")==false){
			return false;
		}else{
			return true;
		}
	}
	
	public static boolean isPasswordValid(String password){
		if (password.length()<8 || password.matches(".*[0-9].*")==false ||
				password.matches(".*[A-Z].*")==false || password.matches(".*[a-z].*")==false) {
			return false;
		} else{
			return true;
		}
	}
}
