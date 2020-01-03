package com.example.cinema.cinemapz.dto;

public class SeatDto {

	private int id;
	private String codeLetter;
	private String codeNumber;
	private boolean occupied;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeLetter() {
		return codeLetter;
	}

	public void setCodeLetter(String codeLetter) {
		this.codeLetter = codeLetter;
	}

	public String getCodeNumber() {
		return codeNumber;
	}

	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

}
