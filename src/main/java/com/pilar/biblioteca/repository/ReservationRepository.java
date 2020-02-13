package com.pilar.biblioteca.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pilar.biblioteca.domain.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

	@Query(value = "SELECT count(R.id) > 0 FROM RESERVATION R where initial_date <= :date and (end_date >= :date or end_date is null) and book_id = :book", nativeQuery = true)
	Boolean findReservationActive(@Param("date") LocalDate date,
			@Param("book") Integer book);
}
