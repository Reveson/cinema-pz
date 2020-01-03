package com.example.cinema.cinemapz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Seat {

	@Id
	@GeneratedValue
	private int id;

	@Column(name="seat_letter")
	private String letter;

	@Column(name="seat_number")
	private String number;

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
