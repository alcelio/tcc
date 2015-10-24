package com.tc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the Turno database table.
 * 
 */
@Entity
@NamedQuery(name = "Turno.findAll", query = "SELECT t FROM Turno t")
public class Turno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idTurno;
	@Column(nullable = false)
	private String dsTurno;

	// bi-directional many-to-one association to Turma
	// @OneToMany(mappedBy="turno")
	// private List<Turma> turmas;

	public Turno() {
	}

	public Integer getIdTurno() {
		return this.idTurno;
	}

	public void setIdTurno(Integer idTurno) {
		this.idTurno = idTurno;
	}

	public String getDsTurno() {
		return this.dsTurno;
	}

	public void setDsTurno(String dsTurno) {
		this.dsTurno = dsTurno;
	}

	// public List<Turma> getTurmas() {
	// return this.turmas;
	// }
	//
	// public void setTurmas(List<Turma> turmas) {
	// this.turmas = turmas;
	// }
	//
	// public Turma addTurma(Turma turma) {
	// getTurmas().add(turma);
	// turma.setTurno(this);
	//
	// return turma;
	// }
	//
	// public Turma removeTurma(Turma turma) {
	// getTurmas().remove(turma);
	// turma.setTurno(null);
	//
	// return turma;
	// }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTurno;
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
		Turno other = (Turno) obj;
		if (idTurno != other.idTurno)
			return false;
		return true;
	}

}