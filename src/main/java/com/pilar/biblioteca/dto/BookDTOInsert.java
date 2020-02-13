package com.pilar.biblioteca.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pilar.biblioteca.enums.StatusBook;

@Component
public class BookDTOInsert {

private String name;
	
	private String author;
	
	private String synopsis;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date releaseDate;
	
	private StatusBook statusBook;
	
	private Integer userId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public StatusBook getStatusBook() {
		return statusBook;
	}

	public void setStatusBook(StatusBook statusBook) {
		this.statusBook = statusBook;
	}

	public Integer getUser() {
		return userId;
	}

	public void setUser(Integer userId) {
		this.userId = userId;
	}

	public BookDTOInsert(String name, String author, String synopsis, Date releaseDate, StatusBook statusBook,
			Integer userId) {
		super();
		this.name = name;
		this.author = author;
		this.synopsis = synopsis;
		this.releaseDate = releaseDate;
		this.statusBook = statusBook;
		this.userId = userId;
	}

	public BookDTOInsert() {
		super();
	}

	
	
	
}
