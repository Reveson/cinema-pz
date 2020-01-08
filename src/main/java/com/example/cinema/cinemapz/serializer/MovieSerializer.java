package com.example.cinema.cinemapz.serializer;

import com.example.cinema.cinemapz.dto.MovieCategoryDto;
import com.example.cinema.cinemapz.dto.MovieDto;
import com.example.cinema.cinemapz.model.Movie;
import com.example.cinema.cinemapz.model.MovieCategory;

public class MovieSerializer {

    public static MovieDto serialize(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setName(movie.getName());
        movieDto.setAge(movie.getAge());
        movieDto.setDescription(movie.getDescription());
        movieDto.setDuration(movie.getDuration());
        movieDto.setCategoryName(movie.getMovieCategory().getName());

        return movieDto;
    }

    public static MovieCategoryDto serialize(MovieCategory movieCategory) {
        MovieCategoryDto categoryDto = new MovieCategoryDto();

        categoryDto.setId(movieCategory.getId());
        categoryDto.setName(movieCategory.getName());

        return categoryDto;
    }

}
