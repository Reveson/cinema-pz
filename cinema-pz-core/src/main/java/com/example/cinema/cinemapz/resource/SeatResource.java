package com.example.cinema.cinemapz.resource;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.cinema.cinemapz.model.Hall;
import com.example.cinema.cinemapz.model.Projection;
import com.example.cinema.cinemapz.model.Seat;

public interface SeatResource extends JpaRepository<Seat, Integer> {

	@Query("select p.movieEvent.hall.seats from Projection p where p.id = ?1")
	List<Seat> findAllByProjectionId(int projectionId);

	@Query(value = "select s.id from projection p "
			+ "join movie_event e on p.movie_event_id = e.id "
			+ "join seat s ON e.hall_id = s.hall_id "
			+ "where p.id = ?1", nativeQuery = true)
	Set<Integer> findIdsByProjectionId(int projectionId);

	@Query(value = "select seats_placement from hall_arrangement h "
			+ "join movie_event e on h.hall_id = e.hall_id "
			+ "join projection p on p.movie_event_id = e.id "
			+ "where p.id = ?", nativeQuery = true)
	String getSeatPlacementMap(int projectionId);
}
