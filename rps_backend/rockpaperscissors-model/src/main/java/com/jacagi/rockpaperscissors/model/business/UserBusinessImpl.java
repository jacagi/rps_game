package com.jacagi.rockpaperscissors.model.business;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jacagi.rockpaperscissors.api.business.UserBusiness;
import com.jacagi.rockpaperscissors.api.dto.UserDTO;
import com.jacagi.rockpaperscissors.api.entity.UserEntity;
import com.jacagi.rockpaperscissors.api.exception.BusinessException;
import com.jacagi.rockpaperscissors.api.exception.IncorrectAuthenticationException;
import com.jacagi.rockpaperscissors.api.repository.UserRepository;
import com.jacagi.rockpaperscissors.model.mapper.AuthMapper;

@Component
public class UserBusinessImpl implements UserBusiness{

	org.slf4j.Logger logger = LoggerFactory.getLogger(UserBusinessImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthMapper authMapper;
	
	@Override
	public UserDTO findUser(String username) throws BusinessException, IncorrectAuthenticationException{
		logger.info("Called UserBusiness#findUser({})", username);
		try {
			Optional<UserEntity> user = this.userRepository.findByUsername(username);
			if(user.isEmpty()) {
				throw new IncorrectAuthenticationException("User not found");
			}
			return this.authMapper.toUserDTO(user.get());
		}catch(IncorrectAuthenticationException e) {
			throw e;
		}catch(Exception e) {
			logger.info("Exception catched at UserBusiness#saveUser. Message: {}", e.getMessage());
			throw new BusinessException("User not found");
		}
	}

	@Override
	public UserDTO saveUser(String username, String password)
			throws BusinessException, IncorrectAuthenticationException {
		logger.info("Called UserBusiness#saveUser({}, {})", username, password);
		try {
			Optional<UserEntity> user = this.userRepository.findByUsername(username);
			if(user.isPresent()) {
				throw new IncorrectAuthenticationException("User already exist");
			}
			UserEntity newUser = this.userRepository.save(new UserEntity(username,password));
			return this.authMapper.toUserDTO(newUser);
		}catch(IncorrectAuthenticationException e) {
			throw e;
		}catch(Exception e) {
			logger.info("Exception catched at UserBusiness#saveUser. Message: {}", e.getMessage());
			throw new BusinessException("User not found");
		}
	}

}
