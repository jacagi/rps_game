package com.jacagi.rockpaperscissors.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jacagi.rockpaperscissors.api.business.GameMovesBusiness;
import com.jacagi.rockpaperscissors.api.dto.GameDTO;
import com.jacagi.rockpaperscissors.api.dto.GameMovesDTO;
import com.jacagi.rockpaperscissors.api.exception.BusinessException;
import com.jacagi.rockpaperscissors.api.exception.ServiceException;
import com.jacagi.rockpaperscissors.api.filter.GameRequestFilter;
import com.jacagi.rockpaperscissors.api.filter.HistoryRequestFilter;
import com.jacagi.rockpaperscissors.api.service.GameMovesService;
import com.jacagi.rockpaperscissors.model.util.GameMovesUtil;

@Service
public class GameMovesServiceImpl implements GameMovesService{

	org.slf4j.Logger logger = LoggerFactory.getLogger(GameMovesServiceImpl.class);
	
	@Autowired
	private GameMovesBusiness gameMovesBusiness;
	
	@Override
	public GameDTO playGame(GameRequestFilter gameRequestFilter) throws ServiceException {
		logger.info("Called GameMovesService#playGame({})", gameRequestFilter);
		try {
			GameMovesDTO computerMove = this.gameMovesBusiness.getComputerMove();
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			GameDTO result = new GameDTO(gameRequestFilter.getUserMove(), computerMove.getMove()
					,GameMovesUtil.checkIfWin(gameRequestFilter.getUserMove(), computerMove.getMove()), 
					authentication.getName());
			
			this.gameMovesBusiness.saveGameRegistry(result);
			
			return result;
		}catch(BusinessException e) {
			logger.info(" BusinessException catched at GameMovesService#playGame. Message: {}", e.getMessage());
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public List<GameDTO> getHistory(HistoryRequestFilter historyRequestFilter) throws ServiceException {
		logger.info("Called GameMovesService#getHistory({})", historyRequestFilter);
		try {

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			return this.gameMovesBusiness.getHistoryGames(authentication.getName())
				.stream()
				.limit(historyRequestFilter.getGamesNum())
				.collect(Collectors.toList());
			
		}catch(BusinessException e) {
			logger.info(" BusinessException catched at GameMovesService#getHistory. Message: {}", e.getMessage());
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
