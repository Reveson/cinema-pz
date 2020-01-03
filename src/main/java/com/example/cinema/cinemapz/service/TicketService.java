package com.example.cinema.cinemapz.service;

import java.util.List;
import java.util.Set;

import com.example.cinema.cinemapz.dto.SeatDto;

public interface TicketService {

	List<SeatDto> getSeats(int projectionId);

}
