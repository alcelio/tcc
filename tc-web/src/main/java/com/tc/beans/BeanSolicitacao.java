package com.tc.beans;

import java.io.Serializable;

import com.tc.model.Questao;
import com.tc.model.Respostas;

public class BeanSolicitacao implements Serializable{
	private static final long serialVersionUID = 1L;

	
	private String solicitacao;

	private Respostas respostas;
	
	private Questao questao;

	private int indiceQuestao;
	
	private String loginUsuario;
	
	public String getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(String solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Respostas getRespostas() {
		return respostas;
	}

	public void setRespostas(Respostas respostas) {
		this.respostas = respostas;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public int getIndiceQuestao() {
		return indiceQuestao;
	}

	public void setIndiceQuestao(int indiceQuestao) {
		this.indiceQuestao = indiceQuestao;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}
	
}
