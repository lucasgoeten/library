package com.pilar.biblioteca.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pilar.biblioteca.domain.Reservation;
import com.pilar.biblioteca.dto.ReservationDto;
import com.pilar.biblioteca.service.ReservationService;
import com.pilar.biblioteca.service.exeptions.ObjectIsNotValideException;
import com.pilar.biblioteca.validate.ReservationValidator;

@RestController
@RequestMapping(value="/reservations")
public class ReservationResource {

	@Autowired
	private ReservationService service;
	
	@Autowired
	private ReservationValidator reservationValidator;
		
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Reservation> find(@PathVariable Integer id) {
		Reservation obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Reservation obj){
		if (reservationValidator.checkPersistence(obj)) {
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} 
		
		throw new ObjectIsNotValideException("Existem campos inválidos! Não é possivel cadastrar a reserva!");
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Reservation obj, @PathVariable Integer id){
		if (reservationValidator.checkPersistence(obj)) {
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}
		
		throw new ObjectIsNotValideException("Existem campos inválidos! Não é possivel cadastrar a reserva!");
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Reservation> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ReservationDto>> findAll() {
		List<Reservation> list = service.findAll();
		List<ReservationDto> listDto = list.stream().map(obj -> new ReservationDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ReservationDto>> findPage(@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Reservation> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ReservationDto> listDto = list.map(obj -> new ReservationDto(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
