package com.pilar.biblioteca.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pilar.biblioteca.domain.Reservation;
import com.pilar.biblioteca.validate.fieldValidator.ReservationBookValidator;
import com.pilar.biblioteca.validate.fieldValidator.ReservationDateValidator;
import com.pilar.biblioteca.validate.fieldValidator.ReservationEndDateValidator;
import com.pilar.biblioteca.validate.fieldValidator.ReservationUserValidator;

@Component
public class ReservationValidator {

	@Autowired
	private ReservationDateValidator reservationDateValidator;
	
	@Autowired
	private ReservationBookValidator reservationBookValidator;
	
	@Autowired
	private ReservationUserValidator reservationUserValidator;
	
	@Autowired
	private ReservationEndDateValidator reservationEndDateValidator;
	
	public boolean checkPersistence(Reservation reservation) {
		return reservationDateValidator.validate(reservation) && 
			   reservationBookValidator.validate(reservation) && 
			   reservationUserValidator.validate(reservation) && 
			   reservationEndDateValidator.validate(reservation);
	}
}
