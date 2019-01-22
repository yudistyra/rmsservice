Feature: Get User List
	Scenario: client makes call to GET /users
	    Given jwt token provided
	    When client calls /users
	    Then client get json type of "users"