package com.pilar.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pilar.biblioteca.domain.Reservation;
import com.pilar.biblioteca.repository.ReservationRepository;
import com.pilar.biblioteca.service.exeptions.DataIntegrityException;
import com.pilar.biblioteca.service.exeptions.ObjectNotFoundException;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository repo;

	public Reservation find(Integer id) {
		Optional<Reservation> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id " + 
										id + ", tipo: " + Reservation.class.getName()));
	}
	
	public Reservation insert(Reservation obj) {
		if (!repo.findReservationActive(obj.getInitialDate(), obj.getBook().getId())) {
			obj.setId(null);
			return repo.save(obj);
		}
		
		return null;
	}
	
	public Reservation update(Reservation obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar o livro!");
		}
	}
	
	public List<Reservation> findAll() {
		return repo.findAll();
	}
	
	public Page<Reservation> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
}
