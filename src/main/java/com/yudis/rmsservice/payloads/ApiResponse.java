package com.yudis.rmsservice.payloads;

public class ApiResponse {
	private int status;
    private String message;
    private JwtAuthenticationResponse jwtResponse;

    public ApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiResponse(int status, String message, JwtAuthenticationResponse jwt) {
        this.status = status;
        this.message = message;
        this.jwtResponse = jwt;
    }
    
    public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public JwtAuthenticationResponse getJwtResponse() {
		return jwtResponse;
	}

	public void setJwtResponse(JwtAuthenticationResponse jwtResponse) {
		this.jwtResponse = jwtResponse;
	}
    
}
