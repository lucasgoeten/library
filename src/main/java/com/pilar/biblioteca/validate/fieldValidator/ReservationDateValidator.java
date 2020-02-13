package com.pilar.biblioteca.validate.fieldValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pilar.biblioteca.domain.Reservation;
import com.pilar.biblioteca.repository.ReservationRepository;

@Component
public class ReservationDateValidator {

	@Autowired
	private ReservationRepository reservationRepository;
	
	public boolean validate(Reservation reservation) {
		return dateCantBeNull(reservation) && canBeReservation(reservation);
	}
	
	private boolean dateCantBeNull(Reservation reservation) {
		return !(reservation.getInitialDate() == null);
	}
	
	private boolean canBeReservation(Reservation reservation) {
		return !(reservationRepository.findReservationActive(reservation.getInitialDate(), reservation.getBook().getId()));
	}
}
