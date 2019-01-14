Feature: rms generate token
	Scenario: client makes call to GET /generate-token
	    When client calls /generate-token
	    Then the client get response code of 200
		And the client receives generated token