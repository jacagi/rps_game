package com.jacagi.rockpaperscissors.ws.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jacagi.rockpaperscissors.api.dto.LoginResponseDTO;
import com.jacagi.rockpaperscissors.api.dto.RegisterResponseDTO;
import com.jacagi.rockpaperscissors.api.exception.IncorrectAuthenticationException;
import com.jacagi.rockpaperscissors.api.exception.ServiceException;
import com.jacagi.rockpaperscissors.api.filter.LoginRequestFilter;
import com.jacagi.rockpaperscissors.api.service.UserService;
import com.jacagi.rockpaperscissors.model.LoginRequest;
import com.jacagi.rockpaperscissors.model.LoginResponse;
import com.jacagi.rockpaperscissors.model.RegisterResponse;
import com.jacagi.rockpaperscissors.ws.mapper.AuthRestMapper;

@ExtendWith(SpringExtension.class)
public class AuthControllerTest {

	@InjectMocks
	private AuthController authController;
	
	@Mock
	private AuthRestMapper authRestMapper;
	
	@Mock
	private UserService userService;
	
	@Test
	void loginWhenItsSuccessful() throws ServiceException, IncorrectAuthenticationException {
		
		LoginRequest request = new LoginRequest();
		request.setUsername("test");
		request.setPassword("test");
		LoginRequestFilter filter = new LoginRequestFilter();
		LoginResponseDTO dto = new LoginResponseDTO("test", "test");
		LoginResponse expected = new LoginResponse();
		expected.setToken("test");
		expected.setStatus("test");
		
		when(this.authRestMapper.toLoginRequestFilter(request)).thenReturn(filter);
		when(this.userService.authenticate(filter)).thenReturn(dto);
		when(this.authRestMapper.toLoginResponse(dto)).thenReturn(expected);
		
		ResponseEntity<LoginResponse> response = this.authController.login(request);
		
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), expected);
		
		verify(this.authRestMapper).toLoginRequestFilter(request);
		verify(this.userService).authenticate(filter);
		verify(this.authRestMapper).toLoginResponse(dto);
	}
	
	@Test
	void loginWhenRequestIsIncorrect() throws ServiceException, IncorrectAuthenticationException {
		
		LoginRequest request = new LoginRequest();
		request.setUsername("test");
		
		
		ResponseEntity<LoginResponse> response = this.authController.login(request);
		
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	void loginWhenIncorrectAuthenticationExceptionIsThrown() throws ServiceException, IncorrectAuthenticationException {
		
		LoginRequest request = new LoginRequest();
		request.setUsername("test");
		request.setPassword("test");
		LoginRequestFilter filter = new LoginRequestFilter();
		
		when(this.authRestMapper.toLoginRequestFilter(request)).thenReturn(filter);
		when(this.userService.authenticate(filter)).thenThrow(new IncorrectAuthenticationException());
		
		ResponseEntity<LoginResponse> response = this.authController.login(request);
		
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		
		verify(this.authRestMapper).toLoginRequestFilter(request);
		verify(this.userService).authenticate(filter);
	}
	
	@Test
	void loginWhenServiceExceptionIsThrown() throws ServiceException, IncorrectAuthenticationException {
		
		LoginRequest request = new LoginRequest();
		request.setUsername("test");
		request.setPassword("test");
		LoginRequestFilter filter = new LoginRequestFilter();
		
		when(this.authRestMapper.toLoginRequestFilter(request)).thenReturn(filter);
		when(this.userService.authenticate(filter)).thenThrow(new ServiceException());
		
		ResponseEntity<LoginResponse> response = this.authController.login(request);
		
		assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		verify(this.authRestMapper).toLoginRequestFilter(request);
		verify(this.userService).authenticate(filter);
	}
	
	@Test
	void registerWhenItsSuccessful() throws ServiceException, IncorrectAuthenticationException {
		
		LoginRequest request = new LoginRequest();
		request.setUsername("test");
		request.setPassword("test");
		LoginRequestFilter filter = new LoginRequestFilter();
		RegisterResponseDTO dto = new RegisterResponseDTO("test");
		RegisterResponse expected = new RegisterResponse();
		expected.setStatus("test");
		
		when(this.authRestMapper.toLoginRequestFilter(request)).thenReturn(filter);
		when(this.userService.register(filter)).thenReturn(dto);
		when(this.authRestMapper.toRegisterResponse(dto)).thenReturn(expected);
		
		ResponseEntity<RegisterResponse> response = this.authController.register(request);
		
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), expected);
		
		verify(this.authRestMapper).toLoginRequestFilter(request);
		verify(this.userService).register(filter);
		verify(this.authRestMapper).toRegisterResponse(dto);
	}
	
	@Test
	void registerWhenRequestIsIncorrect() throws ServiceException, IncorrectAuthenticationException {
		
		LoginRequest request = new LoginRequest();
		request.setUsername("test");
		
		ResponseEntity<RegisterResponse> response = this.authController.register(request);
		
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	void registerWhenServiceExceptionIsThrown() throws ServiceException, IncorrectAuthenticationException {
		
		LoginRequest request = new LoginRequest();
		request.setUsername("test");
		request.setPassword("test");
		LoginRequestFilter filter = new LoginRequestFilter();
		
		when(this.authRestMapper.toLoginRequestFilter(request)).thenReturn(filter);
		when(this.userService.register(filter)).thenThrow(new ServiceException());
		
		ResponseEntity<RegisterResponse> response = this.authController.register(request);
		
		assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		verify(this.authRestMapper).toLoginRequestFilter(request);
		verify(this.userService).register(filter);
	}
	
	@Test
	void registerWhenIncorrectAuthenticationExceptionIsThrown() throws ServiceException, IncorrectAuthenticationException {
		
		LoginRequest request = new LoginRequest();
		request.setUsername("test");
		request.setPassword("test");
		LoginRequestFilter filter = new LoginRequestFilter();
		
		when(this.authRestMapper.toLoginRequestFilter(request)).thenReturn(filter);
		when(this.userService.register(filter)).thenThrow(new IncorrectAuthenticationException());
		
		ResponseEntity<RegisterResponse> response = this.authController.register(request);
		
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		
		verify(this.authRestMapper).toLoginRequestFilter(request);
		verify(this.userService).register(filter);
	}
}
