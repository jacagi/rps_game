package com.jacagi.rockpaperscissors.api.service;

import com.jacagi.rockpaperscissors.api.exception.ServiceException;
import com.jacagi.rockpaperscissors.api.dto.LoginResponseDTO;
import com.jacagi.rockpaperscissors.api.dto.RegisterResponseDTO;
import com.jacagi.rockpaperscissors.api.exception.IncorrectAuthenticationException;
import com.jacagi.rockpaperscissors.api.filter.LoginRequestFilter;


public interface UserService {

	public LoginResponseDTO authenticate(LoginRequestFilter loginRequestFilter) throws IncorrectAuthenticationException, ServiceException ;
	
	public RegisterResponseDTO register(LoginRequestFilter loginRequestFilter) throws IncorrectAuthenticationException, ServiceException ;
	
}
