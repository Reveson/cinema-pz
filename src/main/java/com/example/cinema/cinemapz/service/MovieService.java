package com.example.cinema.cinemapz.service;

import com.example.cinema.cinemapz.dto.MovieCategoryDto;
import com.example.cinema.cinemapz.dto.MovieDto;

import java.util.List;

import com.example.cinema.cinemapz.dto.ProjectionIdWithEpoch;
import com.example.cinema.cinemapz.dto.SimpleMovie;

public interface MovieService {

	List<SimpleMovie> getMovies();

	List<SimpleMovie> getMovies(int categoryId);

	List<MovieCategoryDto> getMovieCategories();

	MovieDto findMovie(int id);

	List<ProjectionIdWithEpoch> getMovieProjectionDates(int movieId);

}
