package com.user.registration.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.registration.controller.exception.BaseApiException;
import com.user.registration.model.User;
import com.user.registration.repository.UserRepository;
import com.user.registration.utility.UserRegistrationUtil;

@RestController
@RequestMapping(value="/v1")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody User user) {
		User userQuery = userRepository.findOneByUserNameAndPassword(user.getUserName(), user.getPassword());
		if (userQuery == null){
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} else{
			return new ResponseEntity<String>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(value= "/register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<User> register(@RequestBody User user) {
		User userFound = userRepository.findOneByUserName(user.getUserName());
		if (userFound == null){
			if (UserRegistrationUtil.isUserNameValid(user.getUserName()) && 
					UserRegistrationUtil.isPasswordValid(user.getPassword())){
				User savedUser = userRepository.save(user);
				return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);	
			}else{
				throw new BaseApiException("Invalid username and password");
			}
		} else{
			return new ResponseEntity<User>(userFound, HttpStatus.OK);
		}
	}
	
}	
