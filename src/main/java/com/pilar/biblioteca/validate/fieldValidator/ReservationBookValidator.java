package com.pilar.biblioteca.validate.fieldValidator;

import org.springframework.stereotype.Component;

import com.pilar.biblioteca.domain.Reservation;

@Component
public class ReservationBookValidator {

	public boolean validate(Reservation reservation) {
		return bookCantBeNull(reservation);
	}
	
	private boolean bookCantBeNull(Reservation reservation) {
		return !(reservation.getBook() == null);
	}
}
