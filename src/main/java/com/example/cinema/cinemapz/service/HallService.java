package com.example.cinema.cinemapz.service;

import java.util.List;

import com.example.cinema.cinemapz.model.Seat;

public interface HallService {

	List<Seat> getSeats(int projectionId);

	boolean seatsExistsInHall(int projectionId, List<Integer> seatsIdsToCheck);

}
