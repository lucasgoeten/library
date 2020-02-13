package com.pilar.biblioteca.validate.fieldValidator;

import org.springframework.stereotype.Component;

import com.pilar.biblioteca.domain.User;
import com.pilar.biblioteca.enums.PermissionType;

@Component
public class BookUserValidator {

	public boolean validate(User user) {
		return userMustBeAdm(user);
	}
	
	private boolean userMustBeAdm(User user) {
		return user.getPermissionType().equals(PermissionType.ADM);
	}
}
