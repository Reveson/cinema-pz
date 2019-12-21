package com.example.cinema.cinemapz.resource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.cinema.cinemapz.dto.SimpleMovie;
import com.example.cinema.cinemapz.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

	@Query("select new com.example.cinema.cinemapz.dto.SimpleMovie(id, name) from Movie")
	List<SimpleMovie> findAllSimple();
}
