package com.tc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Professors
 *
 */
@Entity
@DiscriminatorValue("Professor")
public class Professor extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private String titulacao;

	// bi-directional many-to-many association to Disciplina
	// @ManyToMany
	// @JoinTable( name="ProfessorLeciona", joinColumns={
	// @JoinColumn(name="idProfessor")}
	// , inverseJoinColumns={@JoinColumn(name="idDisciplina")})
	// private List<Disciplina> disciplinas;
	//

	@OneToMany(mappedBy = "professor")
	List<Questao> questoes;

	@OneToMany(mappedBy = "professor")
	private List<Avaliacao> avaliacaoes;

	public Professor() {
		avaliacaoes = new ArrayList<Avaliacao>();
	}

	public String getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public List<Avaliacao> getAvaliacaoes() {
		return avaliacaoes;
	}

	public void setAvaliacaoes(List<Avaliacao> avaliacaoes) {
		this.avaliacaoes = avaliacaoes;
	}

}
