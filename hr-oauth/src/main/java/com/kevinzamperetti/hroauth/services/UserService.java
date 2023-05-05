package com.kevinzamperetti.hroauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kevinzamperetti.hroauth.entities.User;
import com.kevinzamperetti.hroauth.feignclients.UserFeignClient;

@Service
public class UserService implements UserDetailsService {

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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userFeignClient.findByEmail(username).getBody();
		if (user == null) {
			logger.error("There are no User with this e-mail: " + username);
			throw new UsernameNotFoundException("There are no User with this e-mail:" + username);
		}
		logger.error("E-mail found: " + username);
		return user;
	}
	
}
