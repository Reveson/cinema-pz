package com.example.cinema.cinemapz.resource;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cinema.cinemapz.model.MovieCategory;

public interface MovieCategoryResource extends JpaRepository<MovieCategory, Integer> {

}
