package com.pilar.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pilar.biblioteca.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}