package com.example.cinema.cinemapz.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cinema.cinemapz.error.ErrorCode;
import com.example.cinema.cinemapz.IllegalRequestException;
import com.example.cinema.cinemapz.dto.SeatDto;
import com.example.cinema.cinemapz.model.Projection;
import com.example.cinema.cinemapz.model.Seat;
import com.example.cinema.cinemapz.model.Ticket;
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

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<SeatDto> getSeats(int projectionId) {
		List<Seat> seats = hallService.getSeats(projectionId);
		Set<Integer> seatIdsFromTickets = getSeatIdsFromTickets(projectionId);

		return seats.stream().map(seat -> {
			boolean isOccupied = seatIdsFromTickets.contains(seat.getId());
			return SeatSerializer.serialize(seat, isOccupied);
		}).collect(Collectors.toList());
	}

	@Override
	public void bookTickets(int projectionId, List<Integer> seatsIds, String clientName) {
		if (hallService.seatsExistsInHall(projectionId, seatsIds))
			seatsIds.forEach(seat -> ticketResource.save(getNewTicket(projectionId, seat, clientName)));
		else
			throw new IllegalRequestException(ErrorCode.SEATS_OCCUPIED);
	}

	private Set<Integer> getSeatIdsFromTickets(int projectionId) {
		return new HashSet<>(ticketResource.findSeatIdsByProjectionId(projectionId)); //TODO set from database
	}

	private Ticket getNewTicket(int projectionId, int seatId, String clientName) {
		Projection projectionDummy = entityManager.getReference(Projection.class, projectionId);
		Seat seatDummy = entityManager.getReference(Seat.class, seatId);

		Ticket ticket = new Ticket();
		ticket.setClientName(clientName);
		ticket.setProjection(projectionDummy);
		ticket.setSeat(seatDummy);

		return ticket;
	}

}
