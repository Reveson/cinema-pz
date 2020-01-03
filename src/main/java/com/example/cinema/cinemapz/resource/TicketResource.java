package com.example.cinema.cinemapz.resource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.cinema.cinemapz.model.Ticket;

public interface TicketResource extends JpaRepository<Ticket, Long> {

	@Query("select t.seat.id from Ticket t where t.projection.id = ?1")
	List<Integer> findSeatIdsByProjectionId(int projectionId);
}
