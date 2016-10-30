package com.user.registration.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.registration.model.User;
import com.user.registration.repository.UserRepository;

@RestController
@RequestMapping(value="/v1")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST )
	public ResponseEntity<?> login(@RequestBody User user) {
		User userQuery = userRepository.findOneByUserNameAndPassword(user.getUserName(), user.getPassword());
		if (userQuery == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else{
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(value= "/register", method = RequestMethod.POST)
	@ResponseBody
	public User getUserInfo(@RequestBody User user) {
		userRepository.save(user);
		return user;
	}
	
}	
