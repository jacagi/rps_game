package com.jacagi.rockpaperscissors.ws.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.jacagi.rockpaperscissors.api.dto.GameDTO;
import com.jacagi.rockpaperscissors.api.filter.GameRequestFilter;
import com.jacagi.rockpaperscissors.api.filter.HistoryRequestFilter;
import com.jacagi.rockpaperscissors.model.GameRequest;
import com.jacagi.rockpaperscissors.model.GameResponse;
import com.jacagi.rockpaperscissors.model.HistoryRequest;

@Mapper(componentModel = "spring")
public interface GameRestMapper {

	public GameRequestFilter toGameRequestFilter(GameRequest gameRequest);
	
	public GameResponse toGameResponse(GameDTO gameDTO);
	
	public List<GameResponse> toGameResponse(List<GameDTO> gameDTO);
	
	public HistoryRequestFilter toHistoryRequestFilter(HistoryRequest HistoryRequest);
}
