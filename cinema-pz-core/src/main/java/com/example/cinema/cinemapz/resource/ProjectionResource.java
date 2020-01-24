package com.example.cinema.cinemapz.resource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cinema.cinemapz.model.Projection;

public interface ProjectionResource extends JpaRepository<Projection, Integer> {

}
