package com.tc.suport;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class bbConstantes {

	private final String QUESTAO_DISSERTATIVA = "DISSERTATIVA";
	private final String QUESTAO_VF = "VERDADEIRO OU FALSO";
	private final String QUESTAO_OBJETIVA = "OBJETIVA";
	private final String QUESTAO_RELACAO = "RELACAIONAR";
	private final String QUESTAO_ORDENACAO = "ORDENAR";
	
	private final String DIFICULDADE_FACIL = "FÁCIL";
	private final String DIFICULDADE_MODERADA = "MODERADO";
	private final String DIFICULDADE_EXIGENTE = "EXIGENTE";
	private final String DIFICULDADE_MUITO_EGIGENTE = "MUITO EXIGENTE";
	private final String DIFICULDADE_EXTREMA = "EXTREMA";
	public String getQUESTAO_DISSERTATIVA() {
		return QUESTAO_DISSERTATIVA;
	}
	public String getQUESTAO_VF() {
		return QUESTAO_VF;
	}
	public String getQUESTAO_OBJETIVA() {
		return QUESTAO_OBJETIVA;
	}
	public String getQUESTAO_RELACAO() {
		return QUESTAO_RELACAO;
	}
	public String getQUESTAO_ORDENACAO() {
		return QUESTAO_ORDENACAO;
	}
	public String getDIFICULDADE_FACIL() {
		return DIFICULDADE_FACIL;
	}
	public String getDIFICULDADE_MODERADA() {
		return DIFICULDADE_MODERADA;
	}
	public String getDIFICULDADE_EXIGENTE() {
		return DIFICULDADE_EXIGENTE;
	}
	public String getDIFICULDADE_MUITO_EGIGENTE() {
		return DIFICULDADE_MUITO_EGIGENTE;
	}
	public String getDIFICULDADE_EXTREMA() {
		return DIFICULDADE_EXTREMA;
	}
	
	

}
