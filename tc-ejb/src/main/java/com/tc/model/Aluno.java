package com.tc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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

//	@ManyToOne
//	@JoinColumn(name = "idAvaliacao")
//	private Avaliacao avaliacao;

//	// //bi-directional many-to-one association to Avaliacao
//	@OneToMany(mappedBy = "aluno", fetch = FetchType.EAGER)
//	private List<Avaliacoes> avaliacoes;

	// bi-directional many-to-one association to AlunosTurma
	@OneToMany(mappedBy = "aluno")
	private List<AlunosTurma> turmasAluno;

	public Aluno() {
		super();
//		avaliacoes = new ArrayList<Avaliacoes>();
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

//	public List<Avaliacoes> getAvaliacoes() {
//		return avaliacoes;
//	}
//
//	public void setAvaliacoes(List<Avaliacoes> avaliacoes) {
//		this.avaliacoes = avaliacoes;
//	}

	public List<AlunosTurma> getTurmasAluno() {
		return turmasAluno;
	}

	public void setTurmasAluno(List<AlunosTurma> turmasAluno) {
		this.turmasAluno = turmasAluno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((foneResponsavel == null) ? 0 : foneResponsavel.hashCode());
		result = prime * result + ((nomeResponsavel == null) ? 0 : nomeResponsavel.hashCode());
		result = prime * result + ((turmasAluno == null) ? 0 : turmasAluno.hashCode());
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
		if (turmasAluno == null) {
			if (other.turmasAluno != null)
				return false;
		} else if (!turmasAluno.equals(other.turmasAluno))
			return false;
		return true;
	}
	
}
