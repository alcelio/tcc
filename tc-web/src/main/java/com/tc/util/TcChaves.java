package com.tc.util;

import javax.faces.context.FacesContext;

public class TcChaves {
	
	/**
	 * String com valor da página home da aplicação
	 */
	public static final String CAMINHO_HOME = "/restrito/home.jsf";
	
	/**
	 * Enderco raiz da aplicação
	 */
	public static final String PATH_APLICACAO = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
}
