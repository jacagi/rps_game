package com.jacagi.rockpaperscissors.api.dto;

public class GameDTO {

	private String userMove;
	
	private String computerMove;

	private String result;
	
	private String username;
	
	public GameDTO(String userMove, String computerMove, String result, String username) {
		super();
		this.userMove = userMove;
		this.computerMove = computerMove;
		this.result = result;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
