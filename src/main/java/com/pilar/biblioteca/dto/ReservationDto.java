package com.pilar.biblioteca.dto;

import java.io.Serializable;

import com.pilar.biblioteca.domain.Reservation;

public class ReservationDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	
	public ReservationDto() {
	}

	public ReservationDto(Reservation reservation) {
		this.id = reservation.getId();
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
