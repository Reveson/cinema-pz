package com.example.cinema.cinemapz.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cinema.cinemapz.dto.SeatDto;
import com.example.cinema.cinemapz.model.Seat;
import com.example.cinema.cinemapz.resource.TicketResource;
import com.example.cinema.cinemapz.serializer.SeatSerializer;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketResource ticketResource;

	@Autowired
	HallService hallService;

	@Autowired
	MovieService movieService;

	@Override
	public List<SeatDto> getSeats(int projectionId) {
		List<Seat> seats = hallService.getSeats(projectionId);
		Set<Integer> seatIdsFromTickets = getSeatIdsFromTickets(projectionId);

		return seats.stream().map(seat -> {
			boolean isOccupied = seatIdsFromTickets.contains(seat.getId());
			return SeatSerializer.serialize(seat, isOccupied);
		}).collect(Collectors.toList());
	}

	private Set<Integer> getSeatIdsFromTickets(int projectionId) {
		return new HashSet<>(ticketResource.findSeatIdsByProjectionId(projectionId)); //TODO set from database
	}

	public void bookTickets(int projectionId, List<Integer> seats) {
		if(hallService.seatsExistsInHall(projectionId, seats)) {
			//TODO seat and projection unique in ticket in database
		}
	}

}
