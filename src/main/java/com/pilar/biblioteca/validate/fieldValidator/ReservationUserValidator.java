package com.pilar.biblioteca.validate.fieldValidator;

import org.springframework.stereotype.Component;

import com.pilar.biblioteca.domain.Reservation;

@Component
public class ReservationUserValidator {

	public boolean validate (Reservation reservation) {
		return userCantBeNull(reservation);
	}
	
	private boolean userCantBeNull(Reservation reservation) {
		return !(reservation.getUser() == null);
	}
}
