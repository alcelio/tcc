package com.tc.beans;

public class BeanMensagem {
	private String mensagem;
	private String detalhe;
	private int indiceQuestao;
	private boolean isQuestaoCorreta;
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getDetalhe() {
		return detalhe;
	}
	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
	public int getIndiceQuestao() {
		return indiceQuestao;
	}
	public void setIndiceQuestao(int indiceQuestao) {
		this.indiceQuestao = indiceQuestao;
	}
	public boolean isQuestaoCorreta() {
		return isQuestaoCorreta;
	}
	public void setQuestaoCorreta(boolean isQuestaoCorreta) {
		this.isQuestaoCorreta = isQuestaoCorreta;
	}
	
	
	

}
