package com.pilar.biblioteca.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.pilar.biblioteca.domain.User;

@Component
public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String nome;
	
	public UserDto() {
	}
	
	public UserDto(User user) {
		this.id = user.getId();
		this.nome = user.getName();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


}
