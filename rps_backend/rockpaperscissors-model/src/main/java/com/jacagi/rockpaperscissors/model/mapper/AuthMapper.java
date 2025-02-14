package com.jacagi.rockpaperscissors.model.mapper;

import org.mapstruct.Mapper;

import com.jacagi.rockpaperscissors.api.dto.UserDTO;
import com.jacagi.rockpaperscissors.api.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface AuthMapper {

	public UserDTO toUserDTO(UserEntity userEntity);
	
}
