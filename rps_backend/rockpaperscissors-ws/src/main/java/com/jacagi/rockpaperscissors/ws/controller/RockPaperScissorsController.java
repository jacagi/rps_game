package com.jacagi.rockpaperscissors.ws.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacagi.rockpaperscissors.api.RpsApi;
import com.jacagi.rockpaperscissors.api.dto.GameDTO;
import com.jacagi.rockpaperscissors.api.enums.RockPaperScissorsEnum;
import com.jacagi.rockpaperscissors.api.exception.ServiceException;
import com.jacagi.rockpaperscissors.api.service.GameMovesService;
import com.jacagi.rockpaperscissors.model.GameRequest;
import com.jacagi.rockpaperscissors.model.GameResponse;
import com.jacagi.rockpaperscissors.model.HistoryRequest;
import com.jacagi.rockpaperscissors.ws.mapper.GameRestMapper;

@RestController
@RequestMapping("/rps")
public class RockPaperScissorsController implements RpsApi{

	org.slf4j.Logger logger = LoggerFactory.getLogger(RockPaperScissorsController.class);

	@Autowired
	private GameRestMapper gameRestMapper;
	
	@Autowired
	private GameMovesService gameMovesService;
	
	@Override
	@PostMapping("/play")
	public ResponseEntity<GameResponse> playGame(GameRequest request){
		logger.info("Called RockPaperScissorsController#playGame({})", request);
		if(request.getUserMove() == null || !RockPaperScissorsEnum.isValidMove(request.getUserMove())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			GameDTO result = this.gameMovesService.playGame(this.gameRestMapper.toGameRequestFilter(request));
			return new ResponseEntity<>(this.gameRestMapper.toGameResponse(result), HttpStatus.OK);
		}catch(ServiceException e) {
			logger.info("ServiceException catched at RockPaperScissorsController#playGame. Message: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	@PostMapping("/history")
	public ResponseEntity<List<GameResponse>> getHistory(HistoryRequest historyRequest){
		logger.info("Called RockPaperScissorsController#getHistory({})", historyRequest);
		if(historyRequest.getUsername() == null || historyRequest.getGamesNum() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			List<GameDTO> result = this.gameMovesService.getHistory(this.gameRestMapper.toHistoryRequestFilter(historyRequest));
			return new ResponseEntity<>(this.gameRestMapper.toGameResponse(result), HttpStatus.OK);
		}catch(ServiceException e) {
			logger.info("ServiceException catched at RockPaperScissorsController#getHistory. Message: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
