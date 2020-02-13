package com.pilar.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pilar.biblioteca.domain.Book;
import com.pilar.biblioteca.repository.BookRepository;
import com.pilar.biblioteca.service.exeptions.DataIntegrityException;
import com.pilar.biblioteca.service.exeptions.ObjectNotFoundException;

@Service
public class BookService {

	@Autowired
	private BookRepository repo;

	public Book find(Integer id) {
		Optional<Book> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id " + 
										id + ", tipo: " + Book.class.getName()));
	}
	
	public Book insert(Book obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Book update(Book obj) {
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
	
	public List<Book> findAll() {
		return repo.findAll();
	}
	
	public Page<Book> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
}
