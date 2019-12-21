package com.example.cinema.cinemapz.service;

import java.util.List;

import com.example.cinema.cinemapz.dto.SimpleMovie;
import com.example.cinema.cinemapz.model.Movie;
import com.example.cinema.cinemapz.model.MovieCategory;

public interface MovieService {

	List<SimpleMovie> getMovies();

	List<MovieCategory> getMovieCategories();

	Movie findMovie(int id);

}
