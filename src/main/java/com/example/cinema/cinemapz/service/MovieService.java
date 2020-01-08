package com.example.cinema.cinemapz.service;

import com.example.cinema.cinemapz.dto.MovieCategoryDto;
import com.example.cinema.cinemapz.dto.MovieDto;
import java.util.Date;
import java.util.List;

import com.example.cinema.cinemapz.dto.SimpleMovie;
import com.example.cinema.cinemapz.model.Movie;
import com.example.cinema.cinemapz.model.MovieCategory;

public interface MovieService {

	List<SimpleMovie> getMovies();

	List<SimpleMovie> getMovies(int categoryId);

	List<MovieCategoryDto> getMovieCategories();

	MovieDto findMovie(int id);

	List<Long> getMovieProjectionDates(int movieId);

}
