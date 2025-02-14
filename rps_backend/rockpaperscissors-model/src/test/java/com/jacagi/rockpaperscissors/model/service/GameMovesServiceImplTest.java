package com.jacagi.rockpaperscissors.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jacagi.rockpaperscissors.api.business.GameMovesBusiness;
import com.jacagi.rockpaperscissors.api.dto.GameDTO;
import com.jacagi.rockpaperscissors.api.dto.GameMovesDTO;
import com.jacagi.rockpaperscissors.api.exception.BusinessException;
import com.jacagi.rockpaperscissors.api.exception.ServiceException;
import com.jacagi.rockpaperscissors.api.filter.GameRequestFilter;
import com.jacagi.rockpaperscissors.api.filter.HistoryRequestFilter;

@ExtendWith(SpringExtension.class)
public class GameMovesServiceImplTest {

	@InjectMocks
	private GameMovesServiceImpl gameMovesService;
	
	@Mock
	private GameMovesBusiness gameMovesBusiness;
	
    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;
	
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(this.securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }
	
	@Test
	void playGameWhenItsSuccessful() throws BusinessException, ServiceException {
		
		GameRequestFilter filter = new GameRequestFilter();
		filter.setUserMove("ROCK");
		GameMovesDTO dto = new GameMovesDTO();
		dto.setMove("SCISSORS");
		when(this.authentication.getName()).thenReturn("test");
		when(this.gameMovesBusiness.getComputerMove()).thenReturn(dto);
		doNothing().when(this.gameMovesBusiness).saveGameRegistry(ArgumentMatchers.any());
		
		GameDTO response = this.gameMovesService.playGame(filter);
		
		assertEquals(response.getResult(), "WIN");
		
		verify(this.authentication).getName();
		verify(this.gameMovesBusiness).getComputerMove();
		verify(this.gameMovesBusiness).saveGameRegistry(ArgumentMatchers.any());
	}
	
	@Test
	void playGameWhenItThrowsServiceException() throws BusinessException, ServiceException {
		
		GameRequestFilter filter = new GameRequestFilter();
		filter.setUserMove("ROCK");
		
		when(this.gameMovesBusiness.getComputerMove()).thenThrow(new BusinessException());
		
		assertThrows(ServiceException.class, () -> this.gameMovesService.playGame(filter));
		
		verify(this.gameMovesBusiness).getComputerMove();
	}
	
	@Test
	void getHistoryWhenItsSuccessful() throws BusinessException, ServiceException {
		
		HistoryRequestFilter filter = new HistoryRequestFilter();
		filter.setGamesNum(1);
		filter.setUsername("test");
		GameDTO dto = new GameDTO("test", "test", "test", "test");
		List<GameDTO> dtoList = new ArrayList<>();
		dtoList.add(dto);
		dtoList.add(dto);
		when(this.authentication.getName()).thenReturn("test");
		when(this.gameMovesBusiness.getHistoryGames("test")).thenReturn(dtoList);
		
		List<GameDTO> response = this.gameMovesService.getHistory(filter);
		
		assertEquals(response.size(), 1);
		
		verify(this.authentication).getName();
		verify(this.gameMovesBusiness).getHistoryGames("test");
	}
	
	@Test
	void getHistoryWhenItThrowsServiceException() throws BusinessException, ServiceException {
		
		HistoryRequestFilter filter = new HistoryRequestFilter();
		
		when(this.authentication.getName()).thenReturn("test");
		when(this.gameMovesBusiness.getHistoryGames("test")).thenThrow(new BusinessException());
		
		assertThrows(ServiceException.class, () -> this.gameMovesService.getHistory(filter));
		
		verify(this.authentication).getName();
		verify(this.gameMovesBusiness).getHistoryGames("test");
	}
}
