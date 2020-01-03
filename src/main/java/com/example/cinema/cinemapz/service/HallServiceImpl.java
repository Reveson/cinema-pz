package com.example.cinema.cinemapz.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cinema.cinemapz.model.Seat;
import com.example.cinema.cinemapz.resource.SeatResource;

@Service
public class HallServiceImpl implements HallService {

	@Autowired
	SeatResource seatResource;

	@Override
	public List<Seat> getSeats(int projectionId) {
		return seatResource.findAllByProjectionId(projectionId);
	}

	@Override
	public boolean seatsExistsInHall(int projectionId, List<Integer> seatsIdsToCheck) {
		Set<Integer> existingSeatsIds = seatResource.findIdsByProjectionId(projectionId);

		for(Integer seatId : seatsIdsToCheck) {
			if(!existingSeatsIds.contains(seatId))
				return false;
		}
		return true;
	}

}
