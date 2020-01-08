package com.example.cinema.cinemapz.controller;

import com.example.cinema.cinemapz.dto.MovieCategoryDto;
import com.example.cinema.cinemapz.dto.MovieDto;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.cinema.cinemapz.dto.SimpleMovie;
import com.example.cinema.cinemapz.model.Movie;
import com.example.cinema.cinemapz.model.MovieCategory;
import com.example.cinema.cinemapz.service.MovieService;

@Controller
@RequestMapping("movies")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping
	@ResponseBody
	public List<SimpleMovie> getMovies() {
		return movieService.getMovies();
	}

	@GetMapping("/categories")
	@ResponseBody
	public List<MovieCategoryDto> getCategories() {
		return movieService.getMovieCategories();
	}

	@GetMapping("/categories/{categoryId:\\d+}")
	@ResponseBody
	public List<SimpleMovie> getMovies(@PathVariable("categoryId") int categoryId) {
		return movieService.getMovies(categoryId);
	}

	@GetMapping("/{movieId:\\d+}")
	@ResponseBody
	public MovieDto findMovie(@PathVariable("movieId") int id) {
		return movieService.findMovie(id);
	}

	@GetMapping("/{movieId:\\d+}/projections")
	@ResponseBody
	public List<Instant> getMovieProjectionTimes(@PathVariable("movieId") int movieId) {
		List<Long> projectionsAsTimestamp = movieService.getMovieProjectionDates(movieId);
		return projectionsAsTimestamp.stream().map(Instant::ofEpochMilli).collect(Collectors.toList()); //TODO leave as long
	}



}
