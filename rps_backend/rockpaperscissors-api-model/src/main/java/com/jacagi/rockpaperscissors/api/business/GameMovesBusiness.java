package com.jacagi.rockpaperscissors.api.business;

import java.util.List;

import com.jacagi.rockpaperscissors.api.dto.GameDTO;
import com.jacagi.rockpaperscissors.api.dto.GameMovesDTO;
import com.jacagi.rockpaperscissors.api.exception.BusinessException;

public interface GameMovesBusiness {

	public GameMovesDTO getComputerMove() throws BusinessException;

	void saveGameRegistry(GameDTO gameDTO) throws BusinessException;
	
	public List<GameDTO> getHistoryGames(String username) throws BusinessException;
	
}
