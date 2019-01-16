package com.yudis.rmsservice.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
	private int status;
    private String message;
    private JwtAuthenticationResponse jwtResponse;
    
}
