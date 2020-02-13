package com.pilar.biblioteca.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pilar.biblioteca.domain.Book;
import com.pilar.biblioteca.domain.User;
import com.pilar.biblioteca.validate.fieldValidator.BookUserValidator;

@Component
public class BookValidator {

	@Autowired
	private BookUserValidator bookUserValidator;
	
	public boolean checkPersistence(Book book, User user) {
		return bookUserValidator.validate(user);
	}
}
