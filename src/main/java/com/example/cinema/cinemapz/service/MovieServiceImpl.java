package com.example.cinema.cinemapz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cinema.cinemapz.dto.SimpleMovie;
import com.example.cinema.cinemapz.model.Movie;
import com.example.cinema.cinemapz.model.MovieCategory;
import com.example.cinema.cinemapz.resource.MovieCategoryRepository;
import com.example.cinema.cinemapz.resource.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private MovieCategoryRepository movieCategoryRepository;

	@Override
	public List<SimpleMovie> getMovies() {
		return movieRepository.findAllSimple();
	}

	@Override
	public List<MovieCategory> getMovieCategories() {
		return movieCategoryRepository.findAll();
	}

	@Override
	public Movie findMovie(int id) {
		return movieRepository.getOne(id);
	}

}
