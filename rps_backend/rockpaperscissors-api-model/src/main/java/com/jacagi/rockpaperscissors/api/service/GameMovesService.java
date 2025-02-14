package com.jacagi.rockpaperscissors.api.service;

import java.util.List;

import com.jacagi.rockpaperscissors.api.dto.GameDTO;
import com.jacagi.rockpaperscissors.api.exception.ServiceException;
import com.jacagi.rockpaperscissors.api.filter.GameRequestFilter;
import com.jacagi.rockpaperscissors.api.filter.HistoryRequestFilter;

public interface GameMovesService {

	public GameDTO playGame(GameRequestFilter gameRequestFilter) throws ServiceException ;
	
	public List<GameDTO> getHistory(HistoryRequestFilter historyRequestFilter) throws ServiceException ;
}
