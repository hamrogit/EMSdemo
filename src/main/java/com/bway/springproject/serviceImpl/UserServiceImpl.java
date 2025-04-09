package com.bway.springproject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springproject.model.User;
import com.bway.springproject.repository.UserRepository;
import com.bway.springproject.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void userSignup(User user) {

		userRepository.save(user);

	}

	@Override
	public User userLogin(String userName, String password) {

		return userRepository.findByUserNameAndPassword(userName, password);
	}

}
