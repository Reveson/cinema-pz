package com.example.cinema.cinemapz.model;

import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Projection {

	@Id
	@GeneratedValue
	private int id;
	private Date movieStart;
	private int price;

	public Date getMovieStart() {
		return movieStart;
	}

	public void setMovieStart(Date movieStart) {
		this.movieStart = movieStart;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
