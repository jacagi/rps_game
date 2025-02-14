package com.jacagi.rockpaperscissors.ws.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacagi.rockpaperscissors.api.AuthApi;
import com.jacagi.rockpaperscissors.api.dto.LoginResponseDTO;
import com.jacagi.rockpaperscissors.api.dto.RegisterResponseDTO;
import com.jacagi.rockpaperscissors.api.exception.IncorrectAuthenticationException;
import com.jacagi.rockpaperscissors.api.exception.ServiceException;
import com.jacagi.rockpaperscissors.api.service.UserService;
import com.jacagi.rockpaperscissors.model.LoginRequest;
import com.jacagi.rockpaperscissors.model.LoginResponse;
import com.jacagi.rockpaperscissors.model.RegisterResponse;
import com.jacagi.rockpaperscissors.ws.mapper.AuthRestMapper;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthApi{

	org.slf4j.Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthRestMapper authRestMapper;
	
	  @Override
	  @PostMapping("/login")
	  public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
		logger.info("Called RockPaperScissorsController#login({})", loginRequest);
		if(loginRequest == null || loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
			logger.info("Bad request at RockPaperScissorsController#login({})", loginRequest);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			LoginResponseDTO response = this.userService.authenticate(this.authRestMapper.toLoginRequestFilter(loginRequest));
		    if (response.getToken() != null) {
		    	return ResponseEntity.ok(this.authRestMapper.toLoginResponse(response));
		    }else {
		    	logger.info("Internal error at RockPaperScissorsController#login({})", loginRequest);
		    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		}catch(ServiceException e) {
			logger.info("ServiceException catched at RockPaperScissorsController#login. Message: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(IncorrectAuthenticationException e) {
			logger.info("IncorrectAuthenticationException catched at RockPaperScissorsController#login. Message: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	  }

	  @Override
	  @PostMapping("/register")
	  public ResponseEntity<RegisterResponse> register( LoginRequest loginRequest) {
		  	logger.info("Called RockPaperScissorsController#login({})", loginRequest);
			if(loginRequest == null || loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
				logger.info("Bad request at RockPaperScissorsController#login({})", loginRequest);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			try {
				RegisterResponseDTO response = this.userService.register(this.authRestMapper.toLoginRequestFilter(loginRequest));
			    return ResponseEntity.ok(this.authRestMapper.toRegisterResponse(response));
			}catch(ServiceException e) {
				logger.info("ServiceException catched at RockPaperScissorsController#login. Message: {}", e.getMessage());
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}catch(IncorrectAuthenticationException e) {
				logger.info("IncorrectAuthenticationException catched at RockPaperScissorsController#login. Message: {}", e.getMessage());
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
	  }
	
}
