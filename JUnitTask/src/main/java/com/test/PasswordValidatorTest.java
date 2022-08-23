package com.test;

import com.exception.InfyAcademyException;

public class PasswordValidatorTest {
	public boolean validatePassword(String password) throws InfyAcademyException {
		if(password == null || password.isBlank())
			throw new InfyAcademyException("Oops! You have entered an invalid Password.");
		return password.matches("[A-Za-z0-9]{8,20}");
	}


}
