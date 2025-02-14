package com.jacagi.rockpaperscissors.model.mapper;

import java.util.Date;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.jacagi.rockpaperscissors.api.dto.GameDTO;
import com.jacagi.rockpaperscissors.api.dto.GameMovesDTO;
import com.jacagi.rockpaperscissors.api.entity.GameMovesEntity;
import com.jacagi.rockpaperscissors.api.entity.GameRegistryEntity;

@Mapper(componentModel = "spring")
public interface GameMovesMapper {

	public GameMovesDTO toGameMovesDTO(GameMovesEntity gameMovesEntity);
	
	public List<GameDTO> toGameDTO(List<GameRegistryEntity> gameRegistryEntity);
	
	@Mapping(target = "gameDate", ignore=true)
	public GameRegistryEntity toGameRegistryEntity(GameDTO gameDTO);
	
	@AfterMapping
	default void fillWinner(GameDTO gameDTO, @MappingTarget GameRegistryEntity result) {
		result.setGameDate(new Date());
	}
}
