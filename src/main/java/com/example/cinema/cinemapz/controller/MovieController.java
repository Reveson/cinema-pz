package com.example.cinema.cinemapz.controller;

import java.util.List;

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
	public List<MovieCategory> getCategories() {
		return movieService.getMovieCategories();
	}

	@GetMapping("/{movieId:\\d+}")
	@ResponseBody
	public Movie findMovie(@PathVariable("movieId") int id) {
		return movieService.findMovie(id);
	}
}
