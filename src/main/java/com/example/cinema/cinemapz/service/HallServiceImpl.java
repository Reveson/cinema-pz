package com.example.cinema.cinemapz.service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cinema.cinemapz.error.ErrorCode;
import com.example.cinema.cinemapz.NoEntityFoundException;
import com.example.cinema.cinemapz.model.Seat;
import com.example.cinema.cinemapz.resource.SeatResource;

@Service
public class HallServiceImpl implements HallService {

	@Autowired
	SeatResource seatResource;

	@Override
	public List<Seat> getSeats(int projectionId) {
		List<Seat> seats = seatResource.findAllByProjectionId(projectionId);
		if(seats.isEmpty())
			throw new NoEntityFoundException(ErrorCode.PROJECTION_NOT_EXISTS);
		return seats;
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

	@Override
	public List<Integer[]> getSeatPlacement(int projectionId) {
		String seatMap = seatResource.getSeatPlacementMap(projectionId);
		String[] rows = seatMap.replaceAll("\r", "").split("\n");
		return Arrays.stream(rows)
				.map(row -> row.split(" "))
				.map(stringArray -> Arrays.stream(stringArray)
						.map(Integer::valueOf)
						.toArray(Integer[]::new))
				.collect(Collectors.toList());
	}

}
