package com.example.cinema.cinemapz.service;

import com.example.cinema.cinemapz.dto.MovieCategoryDto;
import com.example.cinema.cinemapz.dto.MovieDto;

import java.util.List;

import com.example.cinema.cinemapz.dto.ProjectionIdWithEpoch;
import com.example.cinema.cinemapz.dto.SimpleMovie;

public interface MovieService {

	List<SimpleMovie> getMovies(String lang);

	List<SimpleMovie> getMovies(int categoryId, String lang);

	List<MovieCategoryDto> getMovieCategories(String lang);

	MovieDto findMovie(int id, String lang);

	List<ProjectionIdWithEpoch> getMovieProjectionDates(int movieId);

}
