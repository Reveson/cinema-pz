package com.example.cinema.cinemapz.controller;

import com.example.cinema.cinemapz.dto.MovieCategoryDto;
import com.example.cinema.cinemapz.dto.MovieDto;
import com.example.cinema.cinemapz.dto.RestFields;
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

import com.example.cinema.cinemapz.dto.ProjectionIdWithEpoch;
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
    public List<SimpleMovie> getMovies(
			@RequestParam(value = "lang", defaultValue = RestFields.LANG_PL) String lang
	) {
        return movieService.getMovies(lang);
    }

    @GetMapping("/categories")
    @ResponseBody
    public List<MovieCategoryDto> getCategories(
            @RequestParam(value = "lang", defaultValue = RestFields.LANG_PL) String lang) {
        return movieService.getMovieCategories(lang);
    }

    @GetMapping("/categories/{categoryId:\\d+}")
    @ResponseBody
    public List<SimpleMovie> getMovies(@PathVariable("categoryId") int categoryId,
			@RequestParam(value = "lang", defaultValue = RestFields.LANG_PL) String lang) {
        return movieService.getMovies(categoryId, lang);
    }

    @GetMapping("/{movieId:\\d+}")
    @ResponseBody
    public MovieDto findMovie(@PathVariable("movieId") int id,
			@RequestParam(value = "lang", defaultValue = RestFields.LANG_PL) String lang) {
        return movieService.findMovie(id, lang);
    }

    @GetMapping("/{movieId:\\d+}/projections")
    @ResponseBody
    public List<ProjectionIdWithEpoch> getMovieProjectionTimes(
            @PathVariable("movieId") int movieId) {
        return movieService.getMovieProjectionDates(movieId);
    }


}
