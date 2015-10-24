package com.tc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Alunos
 *
 */
@Entity
@DiscriminatorValue("Aluno")
public class Aluno extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nomeResponsavel;
	private String foneResponsavel;

	@ManyToOne
	@JoinColumn(name = "idAvaliacao")
	private Avaliacao avaliacao;

	// //bi-directional many-to-one association to Avaliacao
	@OneToMany(mappedBy = "aluno")
	private List<Avaliacao> avaliacoes;

	//bi-directional many-to-one association to AlunosTurma
	@OneToMany(mappedBy="aluno")
	private List<AlunosTurma> turmasAluno;


	public Aluno() {
		super();
		turmasAluno = new ArrayList<AlunosTurma>();
	}

	public String getNomeResponsavel() {
		return this.nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getFoneResponsavel() {
		return this.foneResponsavel;
	}

	public void setFoneResponsavel(String foneResponsavel) {
		this.foneResponsavel = foneResponsavel;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
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
		int result = super.hashCode();
		result = prime * result + ((avaliacao == null) ? 0 : avaliacao.hashCode());
		result = prime * result + ((avaliacoes == null) ? 0 : avaliacoes.hashCode());
		result = prime * result + ((foneResponsavel == null) ? 0 : foneResponsavel.hashCode());
		result = prime * result + ((nomeResponsavel == null) ? 0 : nomeResponsavel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (avaliacao == null) {
			if (other.avaliacao != null)
				return false;
		} else if (!avaliacao.equals(other.avaliacao))
			return false;
		if (avaliacoes == null) {
			if (other.avaliacoes != null)
				return false;
		} else if (!avaliacoes.equals(other.avaliacoes))
			return false;
		if (foneResponsavel == null) {
			if (other.foneResponsavel != null)
				return false;
		} else if (!foneResponsavel.equals(other.foneResponsavel))
			return false;
		if (nomeResponsavel == null) {
			if (other.nomeResponsavel != null)
				return false;
		} else if (!nomeResponsavel.equals(other.nomeResponsavel))
			return false;
		return true;
	}

}
