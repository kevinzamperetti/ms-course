package com.kevinzamperetti.hroauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevinzamperetti.hroauth.entities.User;
import com.kevinzamperetti.hroauth.feignclients.UserFeignClient;

@Service
public class UserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	public User findByEmail(String email) {
		User user = userFeignClient.findByEmail(email).getBody();
		if (user == null) {
			logger.error("There are no User with this e-mail: " + email);
			throw new IllegalArgumentException("There are no User with this e-mail:" + email);
		}
		logger.error("E-mail found: " + email);
		return user;
	}
	
}
