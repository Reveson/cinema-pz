package com.example.cinema.cinemapz.service;

import java.util.Date;
import java.util.List;

import com.example.cinema.cinemapz.dto.SimpleMovie;
import com.example.cinema.cinemapz.model.Movie;
import com.example.cinema.cinemapz.model.MovieCategory;

public interface MovieService {

	List<SimpleMovie> getMovies();

	List<SimpleMovie> getMovies(int categoryId);

	List<MovieCategory> getMovieCategories();

	Movie findMovie(int id);

	List<Long> getMovieProjectionDates(int movieId);

}
