package com.jacagi.rockpaperscissors.model.business;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jacagi.rockpaperscissors.api.business.GameMovesBusiness;
import com.jacagi.rockpaperscissors.api.dto.GameDTO;
import com.jacagi.rockpaperscissors.api.dto.GameMovesDTO;
import com.jacagi.rockpaperscissors.api.entity.GameMovesEntity;
import com.jacagi.rockpaperscissors.api.exception.BusinessException;
import com.jacagi.rockpaperscissors.api.repository.GameMovesRepository;
import com.jacagi.rockpaperscissors.api.repository.GameRegistryRepository;
import com.jacagi.rockpaperscissors.model.mapper.GameMovesMapper;
import com.jacagi.rockpaperscissors.model.util.GameMovesUtil;

@Component
public class GameMovesBusinessImpl implements GameMovesBusiness{

	org.slf4j.Logger logger = LoggerFactory.getLogger(GameMovesBusinessImpl.class);
	
	@Autowired
	private GameMovesRepository gameMovesRepository;
	
	@Autowired
	private GameRegistryRepository gameRegistryRepository;
	
	@Autowired
	private GameMovesMapper gameMovesMapper;
	
	@Override
	public GameMovesDTO getComputerMove() throws BusinessException {
		logger.info("Called GameMovesBusiness#getComputerMove()");
		try {
			Optional<GameMovesEntity> res = this.gameMovesRepository.findFirstByProcessedDateIsNullOrderByCreateDateAsc();
			if(res.isEmpty()) {
				return GameMovesUtil.getRandomMove();
			}
			GameMovesEntity gme = res.get();
			gme.setProcessedDate(new Date());
			this.gameMovesRepository.save(gme);
			
			return this.gameMovesMapper.toGameMovesDTO(gme);
		}catch(Exception e) {
			logger.info("Exception catched at GameMovesService#getComputerMove. Message: {}", e.getMessage());
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@Override
	public void saveGameRegistry(GameDTO gameDTO) throws BusinessException {
		logger.info("Called GameMovesBusiness#saveGameRegistry({})", gameDTO);
		try {
			
			this.gameRegistryRepository.save(this.gameMovesMapper.toGameRegistryEntity(gameDTO));
			
		}catch(Exception e) {
			logger.info("Exception catched at GameMovesService#saveGameRegistry. Message: {}", e.getMessage());
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@Override
	public List<GameDTO> getHistoryGames(String username) throws BusinessException {
		logger.info("Called GameMovesBusiness#getHistoryGames({})", username);
		try {
			return this.gameMovesMapper.toGameDTO(this.gameRegistryRepository.findByUsernameOrderByGameDateDesc(username));
		}catch(Exception e) {
			logger.info("Exception catched at GameMovesService#saveGameRegistry. Message: {}", e.getMessage());
			throw new BusinessException(e.getMessage(), e);
		}
	}
}
