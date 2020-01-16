package com.example.cinema.cinemapz.resource;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.cinema.cinemapz.dto.ProjectionIdWithEpoch;
import com.example.cinema.cinemapz.dto.SimpleMovie;
import com.example.cinema.cinemapz.model.Movie;
import com.example.cinema.cinemapz.model.MovieCategory;

public interface MovieResource extends JpaRepository<Movie, Integer> { //TODO movies only having projections

	@Query("select new com.example.cinema.cinemapz.dto.SimpleMovie(id, name) from Movie")
	List<SimpleMovie> findAllSimple();

	@Query("select new com.example.cinema.cinemapz.dto.SimpleMovie(m.id, m.name) "
			+ "from Movie m where m.movieCategory.id = ?1")
	List<SimpleMovie> findByCategorySimple(int categoryId);

	@Query("select new com.example.cinema.cinemapz.dto.ProjectionIdWithEpoch(p.id, p.movieStartTimestamp) "
			+ "from Projection p where p.movieEvent.movie.id = ?1")
	List<ProjectionIdWithEpoch> getProjectionDates(int movieId);

}
