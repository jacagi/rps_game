package com.jacagi.rockpaperscissors.ws.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jacagi.rockpaperscissors.api.dto.GameDTO;
import com.jacagi.rockpaperscissors.api.exception.ServiceException;
import com.jacagi.rockpaperscissors.api.filter.GameRequestFilter;
import com.jacagi.rockpaperscissors.api.filter.HistoryRequestFilter;
import com.jacagi.rockpaperscissors.api.service.GameMovesService;
import com.jacagi.rockpaperscissors.model.GameRequest;
import com.jacagi.rockpaperscissors.model.GameResponse;
import com.jacagi.rockpaperscissors.model.HistoryRequest;
import com.jacagi.rockpaperscissors.ws.mapper.GameRestMapper;

@ExtendWith(SpringExtension.class)
public class RockPaperScissorsControllerTest {

	@InjectMocks
	private RockPaperScissorsController rockPaperScissorsController;
	
	@Mock
	private GameRestMapper gameRestMapper;
	
	@Mock
	private GameMovesService gameMovesService;
	
	@Test
	void playGameWhenItsSuccessful() throws ServiceException {
		
		GameRequest request = new GameRequest();
		request.setUserMove("ROCK");
		GameRequestFilter filter = new GameRequestFilter();
		GameDTO dto = new GameDTO("test", "test", "test", "test");
		GameResponse expected = new GameResponse();
		expected.setComputerMove("test");
		expected.setUserMove("test");
		expected.setResult("test");
		
		when(this.gameRestMapper.toGameRequestFilter(request)).thenReturn(filter);
		when(this.gameMovesService.playGame(filter)).thenReturn(dto);
		when(this.gameRestMapper.toGameResponse(dto)).thenReturn(expected);
		
		ResponseEntity<GameResponse> response = this.rockPaperScissorsController.playGame(request);
		
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), expected);
		
		verify(this.gameRestMapper).toGameRequestFilter(request);
		verify(this.gameMovesService).playGame(filter);
		verify(this.gameRestMapper).toGameResponse(dto);
	}
	
	@Test
	void playGameWhenThrowsInternalException() throws ServiceException {
		
		GameRequest request = new GameRequest();
		request.setUserMove("ROCK");
		GameRequestFilter filter = new GameRequestFilter();
		
		when(this.gameRestMapper.toGameRequestFilter(request)).thenReturn(filter);
		when(this.gameMovesService.playGame(filter)).thenThrow(new ServiceException());
		
		ResponseEntity<GameResponse> response = this.rockPaperScissorsController.playGame(request);
		
		assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		verify(this.gameRestMapper).toGameRequestFilter(request);
		verify(this.gameMovesService).playGame(filter);
	}
	
	@Test
	void playGameWhenInputIsInvalid() throws ServiceException {
		
		GameRequest request = new GameRequest();
		request.setUserMove("Test");
		
		ResponseEntity<GameResponse> response = this.rockPaperScissorsController.playGame(request);
		
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		
	}
	
	@Test
	void getHistoryWhenItsSuccessful() throws ServiceException {
		
		HistoryRequest request = new HistoryRequest();
		request.setUsername("test");
		request.setGamesNum(20);
		HistoryRequestFilter filter = new HistoryRequestFilter();
		GameDTO dto = new GameDTO("test", "test", "test", "test");
		List<GameDTO> dtoList = new ArrayList<>();
		dtoList.add(dto);
		List<GameResponse> resList = new ArrayList<>();
		GameResponse expected = new GameResponse();
		expected.setComputerMove("test");
		expected.setUserMove("test");
		expected.setResult("test");
		resList.add(expected);
		
		when(this.gameRestMapper.toHistoryRequestFilter(request)).thenReturn(filter);
		when(this.gameMovesService.getHistory(filter)).thenReturn(dtoList);
		when(this.gameRestMapper.toGameResponse(dtoList)).thenReturn(resList);
		
		ResponseEntity<List<GameResponse>> response = this.rockPaperScissorsController.getHistory(request);
		
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), resList);
		
		verify(this.gameRestMapper).toHistoryRequestFilter(request);
		verify(this.gameMovesService).getHistory(filter);
		verify(this.gameRestMapper).toGameResponse(dtoList);
	}
	
	@Test
	void getHistoryWhenBadRequest() throws ServiceException {
		
		HistoryRequest request = new HistoryRequest();
		request.setGamesNum(20);

		ResponseEntity<List<GameResponse>> response = this.rockPaperScissorsController.getHistory(request);
		
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	void getHistoryWhenInternalServerError() throws ServiceException {
		
		HistoryRequest request = new HistoryRequest();
		request.setUsername("test");
		request.setGamesNum(20);
		HistoryRequestFilter filter = new HistoryRequestFilter();
		
		when(this.gameRestMapper.toHistoryRequestFilter(request)).thenReturn(filter);
		when(this.gameMovesService.getHistory(filter)).thenThrow(new ServiceException());
		
		ResponseEntity<List<GameResponse>> response = this.rockPaperScissorsController.getHistory(request);
		
		assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		verify(this.gameRestMapper).toHistoryRequestFilter(request);
		verify(this.gameMovesService).getHistory(filter);
	}
}
