package com.yudis.rmsservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.yudis.rmsservice.model.User;
import com.yudis.rmsservice.payloads.ApiResponse;
import com.yudis.rmsservice.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * This class is used for User API Controller
 */
@Api(description="User API Controller")
@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {
	public static final String BASE_URL = "/api/v1/users";
	
	private UserRepository userRepository;
	
	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	/**
	 * Get User List
	 */
	@ApiOperation(value="This API will genereate list of user")
	@Secured("ROLE_ADMIN")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<User> getUser() {
		List<User> users = userRepository.findAll();
		
		return new ApiResponse<User>(200, users);
	}
}
