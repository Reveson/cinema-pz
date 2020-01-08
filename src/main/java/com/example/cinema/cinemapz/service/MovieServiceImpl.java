package com.example.cinema.cinemapz.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cinema.cinemapz.ErrorCode;
import com.example.cinema.cinemapz.NoEntityFoundException;
import com.example.cinema.cinemapz.dto.SimpleMovie;
import com.example.cinema.cinemapz.model.Movie;
import com.example.cinema.cinemapz.model.MovieCategory;
import com.example.cinema.cinemapz.resource.MovieCategoryResource;
import com.example.cinema.cinemapz.resource.MovieResource;

@Service
public class
MovieServiceImpl implements MovieService {

	@Autowired
	private MovieResource movieResource;

	@Autowired
	private MovieCategoryResource movieCategoryRepository;

	@Override
	public List<SimpleMovie> getMovies() {
		return movieResource.findAllSimple();
	}

	@Override
	public List<SimpleMovie> getMovies(int categoryId) {
		return movieResource.findByCategorySimple(categoryId);
	}

	@Override
	public List<MovieCategory> getMovieCategories() {
		return movieCategoryRepository.findAll();
	}

	@Override
	public Movie findMovie(int id) {
		return movieResource.findById(id).orElseThrow(() -> new NoEntityFoundException(ErrorCode.MOVIE_NOT_EXISTS));
	}

	@Override
	public List<Long> getMovieProjectionDates(int movieId) {
		return movieResource.getProjectionDates(movieId);
	}



}
