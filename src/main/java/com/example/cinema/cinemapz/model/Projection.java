package com.example.cinema.cinemapz.model;

import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Projection {

	@Id
	@GeneratedValue
	private int id;

	@Column(name="movie_start")
	private long movieStartTimestamp;
	private int price;

	@ManyToOne
	@JoinColumn(name="movie_event_id", referencedColumnName = "id")
	private MovieEvent movieEvent;

	public long getMovieStartTimestamp() {
		return movieStartTimestamp;
	}

	public void setMovieStartTimestamp(long movieStartTimestamp) {
		this.movieStartTimestamp = movieStartTimestamp;
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

	public MovieEvent getMovieEvent() {
		return movieEvent;
	}

	public void setMovieEvent(MovieEvent movieEvent) {
		this.movieEvent = movieEvent;
	}

}
