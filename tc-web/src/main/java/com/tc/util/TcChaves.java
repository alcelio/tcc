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

	/**
	 * Chave para saber se deve trocar a cor da tela de fundo;
	 */
	public static final String  IND_TROCA_COR_FUNDO = "1";
	
	/**
	 * Chave para buscar um parametro como teste
	 * [idParametro , idUsuario]
	 */
	public final static String[]	PARAM_USUARIO_TESTE			= { "1", "11" };
}
