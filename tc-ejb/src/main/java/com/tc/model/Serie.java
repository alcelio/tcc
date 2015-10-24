package com.tc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * The persistent class for the Serie database table.
 * 
 */
@Entity
@NamedQuery(name = "Serie.findAll", query = "SELECT s FROM Serie s")
public class Serie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idSerie;
	private String dsSerie;

	// bi-directional many-to-one association to Turma
	@OneToMany(mappedBy = "serie")
	private List<Turma> turmas;

	public Serie() {
	}

	public Integer getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(Integer idSerie) {
		this.idSerie = idSerie;
	}

	public String getDsSerie() {
		return dsSerie;
	}

	public void setDsSerie(String dsSerie) {
		this.dsSerie = dsSerie;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dsSerie == null) ? 0 : dsSerie.hashCode());
		result = prime * result + ((idSerie == null) ? 0 : idSerie.hashCode());
		result = prime * result + ((turmas == null) ? 0 : turmas.hashCode());
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
		Serie other = (Serie) obj;
		if (dsSerie == null) {
			if (other.dsSerie != null)
				return false;
		} else if (!dsSerie.equals(other.dsSerie))
			return false;
		if (idSerie == null) {
			if (other.idSerie != null)
				return false;
		} else if (!idSerie.equals(other.idSerie))
			return false;
		if (turmas == null) {
			if (other.turmas != null)
				return false;
		} else if (!turmas.equals(other.turmas))
			return false;
		return true;
	}

}