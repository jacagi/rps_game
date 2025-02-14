package com.jacagi.rockpaperscissors.ws.mapper;

import org.mapstruct.Mapper;

import com.jacagi.rockpaperscissors.api.dto.LoginResponseDTO;
import com.jacagi.rockpaperscissors.api.dto.RegisterResponseDTO;
import com.jacagi.rockpaperscissors.api.filter.LoginRequestFilter;
import com.jacagi.rockpaperscissors.model.LoginRequest;
import com.jacagi.rockpaperscissors.model.LoginResponse;
import com.jacagi.rockpaperscissors.model.RegisterResponse;

@Mapper(componentModel = "spring")
public interface AuthRestMapper {

	public LoginRequestFilter toLoginRequestFilter(LoginRequest loginRequest);
	
	public LoginResponse toLoginResponse(LoginResponseDTO loginResponse);
	
	public RegisterResponse toRegisterResponse(RegisterResponseDTO registerResponseDTO);
}
