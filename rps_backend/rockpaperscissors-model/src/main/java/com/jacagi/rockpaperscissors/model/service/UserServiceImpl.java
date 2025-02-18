package com.jacagi.rockpaperscissors.model.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacagi.rockpaperscissors.api.business.UserBusiness;
import com.jacagi.rockpaperscissors.api.dto.LoginResponseDTO;
import com.jacagi.rockpaperscissors.api.dto.RegisterResponseDTO;
import com.jacagi.rockpaperscissors.api.dto.UserDTO;
import com.jacagi.rockpaperscissors.api.exception.BusinessException;
import com.jacagi.rockpaperscissors.api.exception.ServiceException;
import com.jacagi.rockpaperscissors.api.exception.IncorrectAuthenticationException;
import com.jacagi.rockpaperscissors.api.filter.LoginRequestFilter;
import com.jacagi.rockpaperscissors.api.service.UserService;
import com.jacagi.rockpaperscissors.model.util.JwtService;

@Service
public class UserServiceImpl implements UserService{

	org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
    private JwtService jwtUtils;
	
	@Autowired
	private UserBusiness userBusiness;
	
	@Override
	public LoginResponseDTO authenticate(LoginRequestFilter loginRequestFilter) throws IncorrectAuthenticationException, ServiceException {
		logger.info("Called UserService#authenticate({})", loginRequestFilter);
		try {
			UserDTO userDTO = this.userBusiness.findUser(loginRequestFilter.getUsername());
			if(loginRequestFilter.getPassword().equals(userDTO.getPassword())) {
				return new LoginResponseDTO(this.jwtUtils.generateToken(loginRequestFilter.getUsername()), "Success");
			}
			throw new IncorrectAuthenticationException("Incorrect Password");
		}catch(BusinessException e) {
			logger.info(" BusinessException catched at GameMovesService#authenticate. Message: {}", e.getMessage());
			throw new ServiceException(e.getMessage());
		}catch(IncorrectAuthenticationException e) {
			logger.info(" IncorrectAuthenticationException catched at GameMovesService#authenticate. Message: {}", e.getMessage());
			throw e;
		}
	}

	@Override
	public RegisterResponseDTO register(LoginRequestFilter loginRequestFilter) throws IncorrectAuthenticationException, ServiceException {
		logger.info("Called UserService#register({})", loginRequestFilter);
		try {
			this.userBusiness.saveUser(loginRequestFilter.getUsername(), loginRequestFilter.getPassword());
			return new RegisterResponseDTO("Success");
		}catch(BusinessException e) {
			logger.info(" BusinessException catched at GameMovesService#register. Message: {}", e.getMessage());
			throw new ServiceException(e.getMessage());
		}catch(IncorrectAuthenticationException e) {
			logger.info(" IncorrectAuthenticationException catched at GameMovesService#register. Message: {}", e.getMessage());
			throw e;
		}
	}


}
