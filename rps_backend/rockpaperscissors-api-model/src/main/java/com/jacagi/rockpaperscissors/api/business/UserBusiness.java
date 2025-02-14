package com.jacagi.rockpaperscissors.api.business;

import com.jacagi.rockpaperscissors.api.dto.UserDTO;
import com.jacagi.rockpaperscissors.api.exception.BusinessException;
import com.jacagi.rockpaperscissors.api.exception.IncorrectAuthenticationException;

public interface UserBusiness {

	public UserDTO findUser(String username) throws BusinessException, IncorrectAuthenticationException;
	
	public UserDTO saveUser(String username, String password) throws BusinessException, IncorrectAuthenticationException;
}
