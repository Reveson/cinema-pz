package com.example.cinema.cinemapz.service;

import com.example.cinema.cinemapz.dto.MovieCategoryDto;
import com.example.cinema.cinemapz.dto.MovieDto;
import com.example.cinema.cinemapz.dto.ProjectionIdWithEpoch;
import com.example.cinema.cinemapz.serializer.MovieSerializer;

import java.util.List;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cinema.cinemapz.error.ErrorCode;
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
    public List<MovieCategoryDto> getMovieCategories() {
        List<MovieCategory> movieCategoryList = movieCategoryRepository.findAll();
        return movieCategoryList.stream().map(MovieSerializer::serialize)
                .collect(Collectors.toList());
    }

    @Override
    public MovieDto findMovie(int id) {
        Movie movie = movieResource.findById(id)
                .orElseThrow(() -> new NoEntityFoundException(ErrorCode.MOVIE_NOT_EXISTS));
        return MovieSerializer.serialize(movie);
    }

    @Override
    public List<ProjectionIdWithEpoch> getMovieProjectionDates(int movieId) {
        return movieResource.getProjectionDates(movieId);
    }


}
