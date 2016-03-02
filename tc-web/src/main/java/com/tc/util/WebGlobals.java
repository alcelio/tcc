package com.tc.util;

import javax.faces.context.FacesContext;

public class WebGlobals {
	/**
	 * Enderco raiz da aplicação
	 */
	public static final String PATH_APLICACAO = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
	
	/**
	 * Pagina principal
	 */
	public static final String PAGINA_HOME = "/restrito/home.jsf";


}
