package com.example.cinema.cinemapz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.cinema.cinemapz.dto.SeatDto;
import com.example.cinema.cinemapz.service.MovieService;
import com.example.cinema.cinemapz.service.TicketService;

@Controller
@RequestMapping("tickets")
public class TicketController {

	@Autowired
	MovieService movieService;

	@Autowired
	TicketService ticketService;

	@GetMapping("/projection/{projectionId:\\d+}")
	@ResponseBody
	public List<SeatDto> getSeats(@PathVariable("projectionId") int projectionId) {
		return ticketService.getSeats(projectionId);
	}

	@PostMapping("/projection/{projectionId:\\d+}/book")
	@ResponseBody
	public ResponseEntity<String> bookTickets(@PathVariable("projectionId") int projectionId, @RequestParam("seats") List<Integer> seats) {

		return new ResponseEntity<>(HttpStatus.OK);
	}


}