package com.jacagi.rockpaperscissors.model.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jacagi.rockpaperscissors.api.dto.UserDTO;
import com.jacagi.rockpaperscissors.api.entity.UserEntity;
import com.jacagi.rockpaperscissors.api.exception.BusinessException;
import com.jacagi.rockpaperscissors.api.exception.IncorrectAuthenticationException;
import com.jacagi.rockpaperscissors.api.repository.UserRepository;
import com.jacagi.rockpaperscissors.model.mapper.AuthMapper;

@ExtendWith(SpringExtension.class)
public class UserBusinessImplTest {

	@InjectMocks
	private UserBusinessImpl userBusiness;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private AuthMapper authMapper;
	
	@Test
	void findUserWhenItsSuccessful() throws BusinessException, IncorrectAuthenticationException{
		
		UserEntity userEntity = new UserEntity();
		Optional<UserEntity> optional = Optional.of(userEntity);
		UserDTO dto = new UserDTO();
		
		when(this.userRepository.findByUsername("test")).thenReturn(optional);
		when(this.authMapper.toUserDTO(userEntity)).thenReturn(dto);
		
		UserDTO response = this.userBusiness.findUser("test");
		
		assertEquals(response, dto);
		
		verify(this.userRepository).findByUsername("test");
		verify(this.authMapper).toUserDTO(userEntity);
	}
	
	@Test
	void findUserWhenOptionalItsEmpty() throws BusinessException, IncorrectAuthenticationException{
		
		Optional<UserEntity> optional = Optional.empty();
		
		when(this.userRepository.findByUsername("test")).thenReturn(optional);
		
		assertThrows(IncorrectAuthenticationException.class, () -> this.userBusiness.findUser("test"));
		
		verify(this.userRepository).findByUsername("test");
	}
	
	@Test
	void findUserWhenExceptionItsThrown() throws BusinessException, IncorrectAuthenticationException{
		
		
		when(this.userRepository.findByUsername("test")).thenThrow(new NullPointerException());
		
		assertThrows(BusinessException.class, () -> this.userBusiness.findUser("test"));
		
		verify(this.userRepository).findByUsername("test");
	}
	
	@Test
	void saveUserWhenItsSuccessful() throws BusinessException, IncorrectAuthenticationException{
		
		UserEntity userEntity = new UserEntity();
		Optional<UserEntity> optional = Optional.empty();
		UserDTO dto = new UserDTO();
		
		when(this.userRepository.findByUsername("test")).thenReturn(optional);
		when(this.userRepository.save(ArgumentMatchers.any())).thenReturn(userEntity);
		when(this.authMapper.toUserDTO(userEntity)).thenReturn(dto);
		
		UserDTO response = this.userBusiness.saveUser("test","test");
		
		assertEquals(response, dto);
		
		verify(this.userRepository).findByUsername("test");
		verify(this.userRepository).save(ArgumentMatchers.any());
		verify(this.authMapper).toUserDTO(userEntity);
	}
	
	@Test
	void saveUserWhenOptionalItsNotEmpty() throws BusinessException, IncorrectAuthenticationException{
		
		UserEntity userEntity = new UserEntity();
		Optional<UserEntity> optional = Optional.of(userEntity);
		
		when(this.userRepository.findByUsername("test")).thenReturn(optional);
		
		assertThrows(IncorrectAuthenticationException.class, () -> this.userBusiness.saveUser("test","test"));
		
		verify(this.userRepository).findByUsername("test");
	}
	
	@Test
	void saveUserWhenExceptionItsThrown() throws BusinessException, IncorrectAuthenticationException{
		
		
		when(this.userRepository.findByUsername("test")).thenThrow(new NullPointerException());
		
		assertThrows(BusinessException.class, () -> this.userBusiness.saveUser("test","test"));
		
		verify(this.userRepository).findByUsername("test");
	}
}
