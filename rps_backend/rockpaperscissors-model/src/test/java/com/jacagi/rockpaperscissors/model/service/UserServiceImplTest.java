package com.jacagi.rockpaperscissors.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jacagi.rockpaperscissors.api.business.UserBusiness;
import com.jacagi.rockpaperscissors.api.dto.LoginResponseDTO;
import com.jacagi.rockpaperscissors.api.dto.RegisterResponseDTO;
import com.jacagi.rockpaperscissors.api.dto.UserDTO;
import com.jacagi.rockpaperscissors.api.exception.BusinessException;
import com.jacagi.rockpaperscissors.api.exception.IncorrectAuthenticationException;
import com.jacagi.rockpaperscissors.api.exception.ServiceException;
import com.jacagi.rockpaperscissors.api.filter.LoginRequestFilter;
import com.jacagi.rockpaperscissors.model.util.JwtService;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private UserBusiness userBusiness;
	
	@Mock
    private JwtService jwtUtils;
	
	@Test
	void authenticateWhenItsSuccessful() throws BusinessException, ServiceException, IncorrectAuthenticationException {
		
		LoginRequestFilter filter = new LoginRequestFilter();
		filter.setUsername("test");
		filter.setPassword("test");
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername("test");
		userDTO.setPassword("test");
		
		when(this.userBusiness.findUser("test")).thenReturn(userDTO);
		when(this.jwtUtils.generateToken("test")).thenReturn("test");
		
		LoginResponseDTO response = this.userService.authenticate(filter);
		
		assertEquals(response.getToken(), "test");
		
		verify(this.userBusiness).findUser("test");
		verify(this.jwtUtils).generateToken("test");
	}
	
	@Test
	void authenticateWhenPasswordWrong() throws BusinessException, ServiceException, IncorrectAuthenticationException {
		
		LoginRequestFilter filter = new LoginRequestFilter();
		filter.setUsername("test");
		filter.setPassword("test");
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername("test");
		userDTO.setPassword("wrong");
		
		when(this.userBusiness.findUser("test")).thenReturn(userDTO);
		
		assertThrows(IncorrectAuthenticationException.class, () -> this.userService.authenticate(filter));
		
		verify(this.userBusiness).findUser("test");
	}
	
	@Test
	void authenticateWhenBusinessException() throws BusinessException, ServiceException, IncorrectAuthenticationException {
		
		LoginRequestFilter filter = new LoginRequestFilter();
		filter.setUsername("test");
		filter.setPassword("test");
		
		when(this.userBusiness.findUser("test")).thenThrow(new BusinessException());
		
		assertThrows(ServiceException.class, () -> this.userService.authenticate(filter));
		
		verify(this.userBusiness).findUser("test");
	}
	
	@Test
	void authenticateWhenIncorrectAuthenticationException() throws BusinessException, ServiceException, IncorrectAuthenticationException {
		
		LoginRequestFilter filter = new LoginRequestFilter();
		filter.setUsername("test");
		filter.setPassword("test");
		
		when(this.userBusiness.findUser("test")).thenThrow(new IncorrectAuthenticationException());
		
		assertThrows(IncorrectAuthenticationException.class, () -> this.userService.authenticate(filter));
		
		verify(this.userBusiness).findUser("test");
	}
	@Test
	void registerWhenItsSuccessful() throws BusinessException, ServiceException, IncorrectAuthenticationException {
		
		LoginRequestFilter filter = new LoginRequestFilter();
		filter.setUsername("test");
		filter.setPassword("test");
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername("test");
		userDTO.setPassword("test");
		
		when(this.userBusiness.saveUser("test", "test")).thenReturn(userDTO);
		
		RegisterResponseDTO response = this.userService.register(filter);
		
		assertEquals(response.getStatus(), "Success");
		
		verify(this.userBusiness).saveUser("test", "test");
	}
	
	@Test
	void registerWhenBusinessException() throws BusinessException, ServiceException, IncorrectAuthenticationException {
		
		LoginRequestFilter filter = new LoginRequestFilter();
		filter.setUsername("test");
		filter.setPassword("test");
		
		when(this.userBusiness.saveUser("test", "test")).thenThrow(new BusinessException());
		
		assertThrows(ServiceException.class, () -> this.userService.register(filter));
		
		verify(this.userBusiness).saveUser("test", "test");
	}
	
	@Test
	void registerWhenIncorrectAuthenticationException() throws BusinessException, ServiceException, IncorrectAuthenticationException {
		
		LoginRequestFilter filter = new LoginRequestFilter();
		filter.setUsername("test");
		filter.setPassword("test");
		
		when(this.userBusiness.saveUser("test", "test")).thenThrow(new IncorrectAuthenticationException());
		
		assertThrows(IncorrectAuthenticationException.class, () -> this.userService.register(filter));
		
		verify(this.userBusiness).saveUser("test", "test");
	}
}
