package com.jacagi.rockpaperscissors.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jacagi.rockpaperscissors.api.entity.GameMovesEntity;

@Repository
public interface GameMovesRepository extends JpaRepository<GameMovesEntity, Long> {

	Optional<GameMovesEntity> findFirstByProcessedDateIsNullOrderByCreateDateAsc();
	
}
