package com.src.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.src.Dto.UserDto;
import com.src.Entity.User;
import com.src.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public User saveData(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		return userRepository.save(user);
	}
}
