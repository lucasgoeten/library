package com.pilar.biblioteca.validate.fieldValidator;

import org.springframework.stereotype.Component;

import com.pilar.biblioteca.domain.Reservation;

@Component
public class ReservationEndDateValidator {

	public boolean validate(Reservation reservation) {
		return EndDateCantBeSmallerThanInitialDate(reservation);
	}
	
	private boolean EndDateCantBeSmallerThanInitialDate(Reservation reservation) {
		if (reservation.getEndDate() == null) {
			return true;
		}
		return reservation.getInitialDate().isBefore(reservation.getEndDate());
	}
}
