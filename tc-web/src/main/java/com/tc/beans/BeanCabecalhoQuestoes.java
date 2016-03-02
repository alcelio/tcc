package com.tc.beans;

import com.tc.model.Disciplina;
import com.tc.model.TopicoEstudo;

/**
 * Classe para armazenar os dados do cabeçalho das questões
 * 
 * @author Alcélio Gomes
 *
 */
public class BeanCabecalhoQuestoes {

	private String grauDificuldade;
	private TopicoEstudo topicoEstudo;
	private Disciplina disciplina;
	private String tipoQuestao;
	
	public BeanCabecalhoQuestoes() {
		topicoEstudo = new TopicoEstudo();
		disciplina = new Disciplina();
	}

	public String getGrauDificuldade() {
		return grauDificuldade;
	}

	public void setGrauDificuldade(String grauDificulde) {
		this.grauDificuldade = grauDificulde;
	}

	public TopicoEstudo getTopicoEstudo() {
		return topicoEstudo;
	}

	public void setTopicoEstudo(TopicoEstudo topicoEstudo) {
		this.topicoEstudo = topicoEstudo;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public String getTipoQuestao() {
		return tipoQuestao;
	}

	public void setTipoQuestao(String tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}
	
}
