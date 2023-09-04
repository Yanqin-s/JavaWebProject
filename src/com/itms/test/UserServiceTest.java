package com.itms.test;

import org.junit.Test;

import com.itms.Bean.User;
import com.itms.service.UserService;
import com.itms.service.impl.UserServiceImpl;

public class UserServiceTest {
	UserService userService = new UserServiceImpl();
	
	 		
	@Test
	public void login() {
		User user = new User("202107050101310", "admin123456");
		System.out.println(userService.login(user));
	}
}
