package com.example.cinema.cinemapz.serializer;

import com.example.cinema.cinemapz.dto.SeatDto;
import com.example.cinema.cinemapz.model.Seat;

public class SeatSerializer {

	public static SeatDto serialize(Seat seat, boolean occupied) {
		SeatDto seatDto = new SeatDto();
		seatDto.setId(seat.getId());
		seatDto.setCodeLetter(seat.getLetter());
		seatDto.setCodeNumber(seat.getNumber());
		seatDto.setOccupied(occupied);
		return seatDto;
	}
}
