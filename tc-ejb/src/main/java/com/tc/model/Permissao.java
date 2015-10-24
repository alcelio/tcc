package com.tc.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Permissao
 *
 */
@Entity
@NamedQuery(name = "Permissao.findAll", query = "SELECT p FROM Permissao p")
public class Permissao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idPermissao;
	private String dsDescricao;
	private String dsPermissao;

	public Permissao() {
		super();
	}

	public Integer getIdPermissao() {
		return idPermissao;
	}

	public void setIdPermissao(Integer idPermissao) {
		this.idPermissao = idPermissao;
	}

	public String getDsDescricao() {
		return dsDescricao;
	}

	public void setDsDescricao(String dsDescricao) {
		this.dsDescricao = dsDescricao;
	}

	public String getDsPermissao() {
		return dsPermissao;
	}

	public void setDsPermissao(String dsPermissao) {
		this.dsPermissao = dsPermissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dsDescricao == null) ? 0 : dsDescricao.hashCode());
		result = prime * result + ((dsPermissao == null) ? 0 : dsPermissao.hashCode());
		result = prime * result + ((idPermissao == null) ? 0 : idPermissao.hashCode());
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
		Permissao other = (Permissao) obj;
		if (dsDescricao == null) {
			if (other.dsDescricao != null)
				return false;
		} else if (!dsDescricao.equals(other.dsDescricao))
			return false;
		if (dsPermissao == null) {
			if (other.dsPermissao != null)
				return false;
		} else if (!dsPermissao.equals(other.dsPermissao))
			return false;
		if (idPermissao == null) {
			if (other.idPermissao != null)
				return false;
		} else if (!idPermissao.equals(other.idPermissao))
			return false;
		return true;
	}

}
