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
	private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

}
