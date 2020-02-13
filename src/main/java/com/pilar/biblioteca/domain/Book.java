package com.pilar.biblioteca.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pilar.biblioteca.enums.StatusBook;


@Entity
public class Book implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String author;
	
	private String synopsis;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date releaseDate;
	
	private StatusBook statusBook;
	
	
	public Book() {
	}
	
	
	public Book(Integer id, String name, String author, String synopsis, Date releaseDate, StatusBook statusBook) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.synopsis = synopsis;
		this.releaseDate = releaseDate;
		this.statusBook = statusBook;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	}
