package com.tc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * The persistent class for the Sexo database table.
 * 
 */
@Entity
@NamedQuery(name = "Sexo.findAll", query = "SELECT s FROM Sexo s")
public class Sexo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private byte idSexo;
	@Column(nullable = false, length = 10)
	private String dsSexo;

	// bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy = "sexo")
	private List<Usuario> usuarios;

	public Sexo() {
	}

	public byte getIdSexo() {
		return this.idSexo;
	}

	public void setIdSexo(byte idSexo) {
		this.idSexo = idSexo;
	}

	public String getDsSexo() {
		return this.dsSexo;
	}

	public void setDsSexo(String dsSexo) {
		this.dsSexo = dsSexo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dsSexo == null) ? 0 : dsSexo.hashCode());
		result = prime * result + idSexo;
		result = prime * result + ((usuarios == null) ? 0 : usuarios.hashCode());
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
		Sexo other = (Sexo) obj;
		if (dsSexo == null) {
			if (other.dsSexo != null)
				return false;
		} else if (!dsSexo.equals(other.dsSexo))
			return false;
		if (idSexo != other.idSexo)
			return false;
		if (usuarios == null) {
			if (other.usuarios != null)
				return false;
		} else if (!usuarios.equals(other.usuarios))
			return false;
		return true;
	}
	

}