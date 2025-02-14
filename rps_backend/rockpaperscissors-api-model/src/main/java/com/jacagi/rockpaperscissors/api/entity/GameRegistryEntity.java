package com.jacagi.rockpaperscissors.api.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gameregistry")
public class GameRegistryEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	private String userMove;
	
	@Column(nullable = false)
	private String computerMove;
	
	@Column(nullable = false)
	private String result;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private Date gameDate;

	public GameRegistryEntity() {
		super();
	}
	
	public GameRegistryEntity(String userMove, String computerMove, String result, String username, Date gameDate) {
		super();
		this.userMove = userMove;
		this.computerMove = computerMove;
		this.result = result;
		this.username = username;
		this.gameDate = gameDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserMove() {
		return userMove;
	}

	public void setUserMove(String userMove) {
		this.userMove = userMove;
	}

	public String getComputerMove() {
		return computerMove;
	}

	public void setComputerMove(String computerMove) {
		this.computerMove = computerMove;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getGameDate() {
		return gameDate;
	}

	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
