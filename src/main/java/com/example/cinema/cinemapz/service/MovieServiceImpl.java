package com.example.cinema.cinemapz.service;

import com.example.cinema.cinemapz.dto.MovieCategoryDto;
import com.example.cinema.cinemapz.dto.MovieDto;
import com.example.cinema.cinemapz.dto.ProjectionIdWithEpoch;
import com.example.cinema.cinemapz.resource.MovieResource.MovieProjection;
import com.example.cinema.cinemapz.resource.MovieResource.SimpleMovieProjection;
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
    public List<SimpleMovie> getMovies(String lang) {
        return movieResource.findAllSimple(lang).stream().map(MovieSerializer::serialize).collect(
                Collectors.toList());
    }

    @Override
    public List<SimpleMovie> getMovies(int categoryId, String lang) {
        return movieResource.findByCategorySimple(categoryId, lang).stream()
                .map(MovieSerializer::serialize).collect(
                        Collectors.toList());
    }

    @Override
    public List<MovieCategoryDto> getMovieCategories(String lang) {
        return movieCategoryRepository.findAllWithTranslation(lang)
                .stream().map(MovieSerializer::serialize)
                .collect(Collectors.toList());
    }

    @Override
    public MovieDto findMovie(int id, String lang) {
        MovieProjection movie = movieResource.findByIdProjection(id, lang)
                .orElseThrow(() -> new NoEntityFoundException(ErrorCode.MOVIE_NOT_EXISTS));
        return MovieSerializer.serialize(movie);
    }

    @Override
    public List<ProjectionIdWithEpoch> getMovieProjectionDates(int movieId) {
        return movieResource.getProjectionDates(movieId);
    }


}
