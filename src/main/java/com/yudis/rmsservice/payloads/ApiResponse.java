package com.yudis.rmsservice.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * This class used to be the response of the application
 * status to return the response status (e.g. 200, 401, etc)
 * message to return the response message (e.g. success, Invalid Username or Password!, etc)
 * and use JWTAuthenticationResponse for authentication response
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
	private int status;
    private String message;
    private JwtAuthenticationResponse jwtResponse;
    
}
