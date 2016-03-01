package com.tc.beans;

import com.tc.model.Disciplina;
import com.tc.model.TopicoEstudo;
/**
 * Classe para informar disciplina e grau de dificuldade
 * @author Alcélio Gomes
 *
 */
public class BeanTopicoEstudo {
	
	private Disciplina disciplina;
	private TopicoEstudo  topicoEstudo;
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public TopicoEstudo getTopicoEstudo() {
		return topicoEstudo;
	}
	public void setTopicoEstudo(TopicoEstudo topicoEstudo) {
		this.topicoEstudo = topicoEstudo;
	}
	
}
