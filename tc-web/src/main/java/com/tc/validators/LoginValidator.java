package com.tc.validators;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.tc.data.UsuarioBeanDao;

@FacesValidator("loginValidator")
@ManagedBean
public class LoginValidator implements Validator {

	@EJB
	UsuarioBeanDao dao = new UsuarioBeanDao();

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
			return;
		}
		String login = (String) value;
		boolean isExiste = false;
		try {
			isExiste = dao.isExisteLogin(login);
		} catch (Exception e) {
			System.out.println("Erro ao verificar a existência de login no banco");
		}

		if (isExiste) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login já foi utilizado, informe outro!", ""));
		}
	}

}
