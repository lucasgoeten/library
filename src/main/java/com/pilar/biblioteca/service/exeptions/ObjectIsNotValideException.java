package com.pilar.biblioteca.service.exeptions;

public class ObjectIsNotValideException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectIsNotValideException(String msg) {
		super(msg);
	}
	
	public ObjectIsNotValideException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
