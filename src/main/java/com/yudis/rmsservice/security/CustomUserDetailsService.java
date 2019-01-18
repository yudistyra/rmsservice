package com.yudis.rmsservice.security;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yudis.rmsservice.model.User;
import com.yudis.rmsservice.repository.UserRepository;
/*
 * This class is used for authentication by checking the user details
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private UserRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/*
	 * This method load UserDetails by user name
	 * find user by user name with UserRepository
	 * then create UserPrincipal by the user's found by user name
	 * @return UserPrincipal created by user's information
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("Username not found"));
		return UserPrincipal.create(user);
	}
	
	/*
	 * This method load UserDetails by user id
	 * find user by user id with UserRepository
	 * then create UserPrincipal by the user's found by user id
	 * @return UserPrincipal created by user's information
	 */
	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("User not found with id : " + id));
		
		return UserPrincipal.create(user);
	}
}
