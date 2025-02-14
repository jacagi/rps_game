package com.jacagi.rockpaperscissors.api.dto;

import java.util.Date;

public class GameMovesDTO {

    private Long id;

    private String move;

    private Date createDate;

    private Date processedDate;

    public GameMovesDTO() {
	}
    
	public GameMovesDTO(Long id, String move, Date createDate, Date processedDate) {
		super();
		this.id = id;
		this.move = move;
		this.createDate = createDate;
		this.processedDate = processedDate;
	}

	public GameMovesDTO(String move) {
		super();
		this.move = move;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getProcessedDate() {
		return processedDate;
	}

	public void setProcessedDate(Date processedDate) {
		this.processedDate = processedDate;
	}
	
    
}
