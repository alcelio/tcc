package com.tc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the StatusAvaliacao database table.
 * 
 */
@Entity
@NamedQuery(name = "StatusAvaliacao.findAll", query = "SELECT s FROM StatusAvaliacao s")
public class StatusAvaliacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idStatusAvaliacao;
	@Column(nullable = false)
	private String dsStatusAvaliacao;

	public StatusAvaliacao() {
	}

	public Integer getIdStatusAvaliacao() {
		return this.idStatusAvaliacao;
	}

	public void setIdStatusAvaliacao(Integer idStatusAvaliacao) {
		this.idStatusAvaliacao = idStatusAvaliacao;
	}

	public String getDsStatusAvaliacao() {
		return this.dsStatusAvaliacao;
	}

	public void setDsStatusAvaliacao(String dsStatusAvliacao) {
		this.dsStatusAvaliacao = dsStatusAvliacao;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dsStatusAvaliacao == null) ? 0 : dsStatusAvaliacao.hashCode());
		result = prime * result + ((idStatusAvaliacao == null) ? 0 : idStatusAvaliacao.hashCode());
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
		StatusAvaliacao other = (StatusAvaliacao) obj;
		if (dsStatusAvaliacao == null) {
			if (other.dsStatusAvaliacao != null)
				return false;
		} else if (!dsStatusAvaliacao.equals(other.dsStatusAvaliacao))
			return false;
		if (idStatusAvaliacao == null) {
			if (other.idStatusAvaliacao != null)
				return false;
		} else if (!idStatusAvaliacao.equals(other.idStatusAvaliacao))
			return false;
		return true;
	}

	
}