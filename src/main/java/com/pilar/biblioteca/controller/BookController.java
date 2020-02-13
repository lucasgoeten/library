package com.pilar.biblioteca.controller;

import org.springframework.stereotype.Component;

import com.pilar.biblioteca.domain.Book;
import com.pilar.biblioteca.dto.BookDTOInsert;

@Component
public class BookController {

	public Book converterDTOToEntity(BookDTOInsert bookDTOInsert) {
		Book book = new Book();
		book.setAuthor(bookDTOInsert.getAuthor());
		book.setName(bookDTOInsert.getName());
		book.setReleaseDate(bookDTOInsert.getReleaseDate());
		book.setStatusBook(bookDTOInsert.getStatusBook());
		book.setSynopsis(bookDTOInsert.getSynopsis());
		return book;
	}
}
