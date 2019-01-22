package com.yudis.rmsservice.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;
/*
 * This class is used for the authentication response
 * 
 */
@Data
@NoArgsConstructor
public class JwtAuthenticationResponse {
	private int status;
	private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(int status,String accessToken) {
    	this.status = status;
        this.accessToken = accessToken;
    }

}
