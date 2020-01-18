package com.example.cinema.cinemapz.serializer;

import com.example.cinema.cinemapz.dto.MovieCategoryDto;
import com.example.cinema.cinemapz.dto.MovieDto;
import com.example.cinema.cinemapz.dto.SimpleMovie;
import com.example.cinema.cinemapz.model.Movie;
import com.example.cinema.cinemapz.model.MovieCategory;
import com.example.cinema.cinemapz.resource.MovieCategoryResource.MovieCategoryProjection;
import com.example.cinema.cinemapz.resource.MovieResource.MovieProjection;
import com.example.cinema.cinemapz.resource.MovieResource.SimpleMovieProjection;

public class MovieSerializer {

    public static MovieDto serialize(MovieProjection movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setName(movie.getName());
        movieDto.setAge(movie.getAge());
        movieDto.setDescription(movie.getDescription());
        movieDto.setDuration(movie.getDuration());
        movieDto.setCategoryName(movie.getCategory());
        movieDto.setImageUrl(movie.getImage());

        return movieDto;
    }

    public static MovieCategoryDto serialize(MovieCategoryProjection movieCategory) {
        MovieCategoryDto categoryDto = new MovieCategoryDto();

        categoryDto.setId(movieCategory.getId());
        categoryDto.setName(movieCategory.getName());

        return categoryDto;
    }

    public static SimpleMovie serialize(SimpleMovieProjection simpleMovieProjection) {
        SimpleMovie simpleMovie = new SimpleMovie();
        simpleMovie.setId(simpleMovieProjection.getId());
        simpleMovie.setName(simpleMovieProjection.getName());
        simpleMovie.setImageUrl(simpleMovieProjection.getImage());
        return simpleMovie;
    }

}
