package com.jacagi.rockpaperscissors.api.filter;

public class HistoryRequestFilter {

	private String username;

	private Integer gamesNum;

	public HistoryRequestFilter() {
	}
	
	public HistoryRequestFilter(String username, Integer gamesNum) {
		super();
		this.username = username;
		this.gamesNum = gamesNum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getGamesNum() {
		return gamesNum;
	}

	public void setGamesNum(Integer gamesNum) {
		this.gamesNum = gamesNum;
	}
	

}
