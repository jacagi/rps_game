package com.jacagi.rockpaperscissors.api.dto;

public class RegisterResponseDTO {

	private String status;

	public RegisterResponseDTO() {
	}
	
	public RegisterResponseDTO(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
