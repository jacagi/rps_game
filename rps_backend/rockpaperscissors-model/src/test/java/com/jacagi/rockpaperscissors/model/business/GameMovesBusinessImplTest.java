package com.jacagi.rockpaperscissors.model.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jacagi.rockpaperscissors.api.dto.GameDTO;
import com.jacagi.rockpaperscissors.api.dto.GameMovesDTO;
import com.jacagi.rockpaperscissors.api.entity.GameMovesEntity;
import com.jacagi.rockpaperscissors.api.entity.GameRegistryEntity;
import com.jacagi.rockpaperscissors.api.exception.BusinessException;
import com.jacagi.rockpaperscissors.api.repository.GameMovesRepository;
import com.jacagi.rockpaperscissors.api.repository.GameRegistryRepository;
import com.jacagi.rockpaperscissors.model.mapper.GameMovesMapper;

@ExtendWith(SpringExtension.class)
public class GameMovesBusinessImplTest {

	@InjectMocks
	private GameMovesBusinessImpl gameMovesBusiness;
	
	@Mock
	private GameMovesRepository gameMovesRepository;
	
	@Mock
	private GameRegistryRepository gameRegistryRepository;
	
	@Mock
	private GameMovesMapper gameMovesMapper;
	
	@Test
	void getComputerMoveWhenItsSuccessful() throws BusinessException{
		
		GameMovesEntity gameMovesEntity = new GameMovesEntity();
		Optional<GameMovesEntity> optional = Optional.of(gameMovesEntity);
		GameMovesDTO dto = new GameMovesDTO();
		
		when(this.gameMovesRepository.findFirstByProcessedDateIsNullOrderByCreateDateAsc()).thenReturn(optional);
		when(this.gameMovesRepository.save(ArgumentMatchers.any())).thenReturn(gameMovesEntity);
		when(this.gameMovesMapper.toGameMovesDTO(ArgumentMatchers.any())).thenReturn(dto);
		
		GameMovesDTO response = this.gameMovesBusiness.getComputerMove();
		
		assertEquals(response, dto);
		
		verify(this.gameMovesRepository).findFirstByProcessedDateIsNullOrderByCreateDateAsc();
		verify(this.gameMovesRepository).save(ArgumentMatchers.any());
		verify(this.gameMovesMapper).toGameMovesDTO(ArgumentMatchers.any());
	}
	
	@Test
	void getComputerMoveWhenMoveIsEmpty() throws BusinessException{
		
		Optional<GameMovesEntity> optional = Optional.empty();
		
		when(this.gameMovesRepository.findFirstByProcessedDateIsNullOrderByCreateDateAsc()).thenReturn(optional);
		
		GameMovesDTO response = this.gameMovesBusiness.getComputerMove();
		
		assertNotNull(response.getMove());
		
		verify(this.gameMovesRepository).findFirstByProcessedDateIsNullOrderByCreateDateAsc();
	}
	
	@Test
	void getComputerMoveWhenThrowsBusinessException() throws BusinessException{
		
		when(this.gameMovesRepository.findFirstByProcessedDateIsNullOrderByCreateDateAsc()).thenThrow(new NullPointerException());
		
		assertThrows(BusinessException.class, () -> this.gameMovesBusiness.getComputerMove());
		
		verify(this.gameMovesRepository).findFirstByProcessedDateIsNullOrderByCreateDateAsc();
	}
	
	@Test
	void saveGameRegistryWhenItsSuccessful() throws BusinessException{
		
		GameRegistryEntity entity = new GameRegistryEntity("test", "test", "test", "test", new Date());
		GameDTO gameDTO = new GameDTO("test", "test", "test", "test");
		
		when(this.gameMovesMapper.toGameRegistryEntity(gameDTO)).thenReturn(entity);
		when(this.gameRegistryRepository.save(entity)).thenReturn(entity);
		
		this.gameMovesBusiness.saveGameRegistry(gameDTO);
		
		verify(this.gameMovesMapper).toGameRegistryEntity(gameDTO);
		verify(this.gameRegistryRepository).save(entity);
	}
	
	@Test
	void saveGameRegistryWhenThrowsBusinessException() throws BusinessException{
		
		GameDTO gameDTO = new GameDTO("test", "test", "test", "test");
		
		when(this.gameMovesMapper.toGameRegistryEntity(gameDTO)).thenThrow(new NullPointerException());
		
		assertThrows(BusinessException.class, () -> this.gameMovesBusiness.saveGameRegistry(gameDTO));
		
		verify(this.gameMovesMapper).toGameRegistryEntity(gameDTO);
	}
	
	@Test
	void getHistoryGamesWhenItsSuccessful() throws BusinessException{
		
		List<GameRegistryEntity> registryList = new ArrayList<>();
		GameRegistryEntity entity = new GameRegistryEntity("test", "test", "test", "test", new Date());
		registryList.add(entity);
		List<GameDTO> dtoList = new ArrayList<>();
		GameDTO gameDTO = new GameDTO("test", "test", "test", "test");
		dtoList.add(gameDTO);
		
		when(this.gameRegistryRepository.findByUsernameOrderByGameDateDesc("test")).thenReturn(registryList);
		when(this.gameMovesMapper.toGameDTO(registryList)).thenReturn(dtoList);
		
		List<GameDTO> res = this.gameMovesBusiness.getHistoryGames("test");
		
		assertEquals(res, dtoList);
		
		verify(this.gameRegistryRepository).findByUsernameOrderByGameDateDesc("test");
		verify(this.gameMovesMapper).toGameDTO(registryList);
	}
	
	@Test
	void getHistoryGamesWhenThrowsBusinessException() throws BusinessException{
		
		
		when(this.gameRegistryRepository.findByUsernameOrderByGameDateDesc("test")).thenThrow(new NullPointerException());

		assertThrows(BusinessException.class, () -> this.gameMovesBusiness.getHistoryGames("test"));
		
		verify(this.gameRegistryRepository).findByUsernameOrderByGameDateDesc("test");
	}
}
