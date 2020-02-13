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

import com.pilar.biblioteca.controller.BookController;
import com.pilar.biblioteca.domain.Book;
import com.pilar.biblioteca.domain.User;
import com.pilar.biblioteca.dto.BookDTOInsert;
import com.pilar.biblioteca.dto.BookDto;
import com.pilar.biblioteca.service.BookService;
import com.pilar.biblioteca.service.UserService;
import com.pilar.biblioteca.service.exeptions.ObjectIsNotValideException;
import com.pilar.biblioteca.validate.BookValidator;

@RestController
@RequestMapping(value="/books")
public class BookResource {
	
	@Autowired
	private BookService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookController bookController;
	
	@Autowired
	private BookValidator bookValidator;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Book> find(@PathVariable Integer id) {
		Book obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody BookDTOInsert obj){
		Book book = bookController.converterDTOToEntity(obj);
		User user = userService.find(obj.getUser());
		if (bookValidator.checkPersistence(book, user)) {
			book = service.insert(book);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(book.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		
		throw new ObjectIsNotValideException("Existem campos inválidos! Não é possivel cadastrar a reserva!");
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Book obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Book> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<BookDto>> findAll() {
		List<Book> list = service.findAll();
		List<BookDto> listDto = list.stream().map(obj -> new BookDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<BookDto>> findPage(@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Book> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<BookDto> listDto = list.map(obj -> new BookDto(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
