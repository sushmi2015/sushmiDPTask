package com.mainapp;

import com.exception.InfyAcademyException;

public class EmailValidator {

public boolean validateEmailId(String emailId) throws InfyAcademyException {
		
		if(emailId == null || emailId.isBlank())
			throw new com.exception.InfyAcademyException("Oops! You have entered Invalid Email-ID");
		return emailId.matches("\\w+@\\w\\.(com|in)");
	}
	
}
