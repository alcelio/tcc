package com.tc.validators;

import javax.faces.validator.FacesValidator;


@FacesValidator("cpfValidator")
public class CepValidator {

	// Método que valida o Cep
	
	public static boolean validaCep(String cep) {
		return cep.matches("\\d{5}-\\d{3}"); 
	}

}
