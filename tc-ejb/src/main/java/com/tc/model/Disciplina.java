package com.tc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Disciplina
 *
 */
@Entity
@NamedQuery(name = "Disciplina.findAll", query = "SELECT d FROM Disciplina d")
public class Disciplina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idDisciplina;
	private String dsDisciplina;
	// private Image imgSimbolo;

//	// BIdirecional para Questão
//	@OneToMany(mappedBy = "disciplina")
//	private List<Questao> questoes;

	// BIdirecional para Topico Estudo
	@OneToMany(mappedBy = "disciplina")
	private List<TopicoEstudo> topicoEstudo;
	
	@OneToMany(mappedBy = "disciplina")
	private List<Avaliacao> avaliacoes;

	public Disciplina() {
//		questoes = new ArrayList<Questao>();
		avaliacoes= new ArrayList<Avaliacao>();
	}

	public Integer getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Integer idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getDsDisciplina() {
		return dsDisciplina;
	}

	public void setDsDisciplina(String dsDisciplina) {
		this.dsDisciplina = dsDisciplina;
	}

//	public List<Questao> getQuestoes() {
//		return questoes;
//	}
//
//	public void setQuestoes(List<Questao> questoes) {
//		this.questoes = questoes;
//	}

	public List<TopicoEstudo> getTopicoEstudo() {
		return topicoEstudo;
	}

	public void setTopicoEstudo(List<TopicoEstudo> topicoEstudo) {
		this.topicoEstudo = topicoEstudo;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avaliacoes == null) ? 0 : avaliacoes.hashCode());
		result = prime * result + ((dsDisciplina == null) ? 0 : dsDisciplina.hashCode());
		result = prime * result + ((idDisciplina == null) ? 0 : idDisciplina.hashCode());
		result = prime * result + ((topicoEstudo == null) ? 0 : topicoEstudo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		if (avaliacoes == null) {
			if (other.avaliacoes != null)
				return false;
		} else if (!avaliacoes.equals(other.avaliacoes))
			return false;
		if (dsDisciplina == null) {
			if (other.dsDisciplina != null)
				return false;
		} else if (!dsDisciplina.equals(other.dsDisciplina))
			return false;
		if (idDisciplina == null) {
			if (other.idDisciplina != null)
				return false;
		} else if (!idDisciplina.equals(other.idDisciplina))
			return false;
		if (topicoEstudo == null) {
			if (other.topicoEstudo != null)
				return false;
		} else if (!topicoEstudo.equals(other.topicoEstudo))
			return false;
		return true;
	}
	
}
