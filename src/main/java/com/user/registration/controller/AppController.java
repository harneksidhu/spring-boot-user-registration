package com.user.registration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class AppController {
	@RequestMapping("/admin")
	public @ResponseBody String getAdminInfo() {
		String msg ="Welcome to Admin.";
		return msg;
	}
	
	@RequestMapping("/user")
	public @ResponseBody String getUserInfo() {
		String msg ="Welcome to User.";
		return msg;
	}
	
	@RequestMapping("/test")
	public @ResponseBody String getTestInfo() {
		String msg ="Welcome to test.";
		return msg;
	}
}	
