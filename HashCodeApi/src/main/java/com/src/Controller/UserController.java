package com.src.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.src.Dto.UserDto;
import com.src.Entity.User;
import com.src.Service.UserService;

@RestController	
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/api/User")
	public User saveData(@RequestBody UserDto userDto) {
		return userService.saveData(userDto);
	}
}
