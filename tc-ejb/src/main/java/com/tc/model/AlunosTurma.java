package com.tc.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.tc.model.PK.AlunosTurmaPK;

/**
 * Entity implementation class for Entity: AlunosTurma
 *
 */
@Entity
@NamedQuery(name = "AlunosTurma.findAll", query = "SELECT q FROM AlunosTurma q")
public class AlunosTurma implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AlunosTurmaPK id;

	//bi-directional many-to-one association to Avaliacao
	@ManyToOne
	@JoinColumn(name="idUsuario", insertable=false, updatable=false )
	private Usuario aluno;

	//bi-directional many-to-one association to Questao
	@ManyToOne
	@JoinColumn(name="idTurma", insertable=false, updatable=false)
	private Turma turma;

	public AlunosTurma() {
	}

	public AlunosTurmaPK getId() {
		return id;
	}

	public void setId(AlunosTurmaPK id) {
		this.id = id;
	}

	public Usuario getAluno() {
		return aluno;
	}

	public void setAluno(Usuario aluno) {
		this.aluno = aluno;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
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
		AlunosTurma other = (AlunosTurma) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		return true;
	}
	
	}
