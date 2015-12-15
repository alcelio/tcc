package com.tc.exceptions;

import static com.tc.util.IavaliarUtil.brakeLineChar;
import static com.tc.util.IavaliarUtil.removerAcentos;

import com.tc.util.IavaliarUtil;

public class ScreenException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String	screenMessage;

	private boolean	mostraJanela	= true;
	
	/**
	 * Construtor da classe
	 * 
	 * @param screenMessage
	 *            Mensagem que será exibida na tela
	 * @param cause
	 *            Exceção causadora do erro
	 */
	public ScreenException(String screenMessage, Throwable cause) {
		super(removerAcentos(screenMessage, true), cause);
		this.screenMessage = brakeLineChar(screenMessage, 40);
	}
	
	/**
	 * Construtor da classe
	 * 
	 * @param screenMessage
	 *            Mensagem que será exibida na tela
	 */
	public ScreenException(String screenMessage) {
		super( IavaliarUtil.removerAcentos(screenMessage, true));
		this.screenMessage = brakeLineChar(screenMessage, 50);
	}
	
	/**
	 * Construtor da classe
	 * 
	 * @param screenMessage
	 *            Mensagem que será exibida na tela
	 */
	public ScreenException(String screenMessage, boolean mostraJanela) {
		super(IavaliarUtil.removerAcentos(screenMessage, true));
		this.screenMessage = brakeLineChar(screenMessage, 50);
		this.mostraJanela = mostraJanela;
	}
	
	/**
	 * Retorna a mensagem que será exibida
	 * 
	 * @return String
	 */
	public String getScreenMessage() {
		if (screenMessage == null)
			screenMessage = super.getMessage();
		
		return screenMessage;
	}
	
	/**
	 * Retorna indicador que diz se deve ou não mostrar mensagem
	 * 
	 * @return boolean
	 */
	public boolean isMostraJanela() {
		return mostraJanela;
	}
}
