package com.jacagi.rockpaperscissors.api.filter;

public class LoginRequestFilter {

	private String username;

	private String password;

	public LoginRequestFilter() {
	}
	
	public LoginRequestFilter(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
