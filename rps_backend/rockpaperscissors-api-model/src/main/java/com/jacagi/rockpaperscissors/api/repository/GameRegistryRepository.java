package com.jacagi.rockpaperscissors.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jacagi.rockpaperscissors.api.entity.GameRegistryEntity;

@Repository
public interface GameRegistryRepository extends JpaRepository<GameRegistryEntity, Long> {

	List<GameRegistryEntity> findByUsernameOrderByGameDateDesc(String username);
	
}
