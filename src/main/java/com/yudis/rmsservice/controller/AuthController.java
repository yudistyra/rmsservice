package com.yudis.rmsservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.yudis.rmsservice.payloads.JwtAuthenticationResponse;
import com.yudis.rmsservice.payloads.LoginRequest;
import com.yudis.rmsservice.security.JwtTokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/*
 * This class is used for authentication controller
 */
@Api(description = "This is my Authentication Controller")
@RestController
@RequestMapping(AuthController.BASE_URL)
public class AuthController {
	
	public static final String BASE_URL = "/api/v1/auth";
	
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
		super();
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
	}

    /*
     * This method is used to authenticate the user
     * @param loginRequest contains user name and password
     * authenticate the user name and password with AuthenticationManager by UsernamePasswordAuthenticationToken
     * generate token from JwtTokenProvider
     * @return ApiResponse whether it's success or not
     */
	@ApiOperation(value = "This API will generate access token.")
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public JwtAuthenticationResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    	try {
    		Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.generateToken(authentication);
            
            return new JwtAuthenticationResponse(200,jwt);
		} catch (BadCredentialsException e) {
            return new JwtAuthenticationResponse(401,null);
		}
        
    }
}
