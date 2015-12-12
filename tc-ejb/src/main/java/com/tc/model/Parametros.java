package com.tc.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ParametrosSistema
 *
 */
@Entity
@NamedQuery(name = "Parametros.findAll", query = "SELECT p FROM Parametros p")
public class Parametros implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idParametro;
	private String desParametro;
	private String desFuncaoParametro;
	private String vlrPadrao;

	public Parametros() {
		super();
	}

	public Integer getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
	}

	public String getDesParametro() {
		return desParametro;
	}

	public void setDesParametro(String desParametro) {
		this.desParametro = desParametro;
	}

	public String getDesFuncaoParametro() {
		return desFuncaoParametro;
	}

	public void setDesFuncaoParametro(String desFuncaoParametro) {
		this.desFuncaoParametro = desFuncaoParametro;
	}

	public String getVlrPadrao() {
		return vlrPadrao;
	}

	public void setVlrPadrao(String vlrPadrao) {
		this.vlrPadrao = vlrPadrao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desFuncaoParametro == null) ? 0 : desFuncaoParametro.hashCode());
		result = prime * result + ((desParametro == null) ? 0 : desParametro.hashCode());
		result = prime * result + ((idParametro == null) ? 0 : idParametro.hashCode());
		result = prime * result + ((vlrPadrao == null) ? 0 : vlrPadrao.hashCode());
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
		Parametros other = (Parametros) obj;
		if (desFuncaoParametro == null) {
			if (other.desFuncaoParametro != null)
				return false;
		} else if (!desFuncaoParametro.equals(other.desFuncaoParametro))
			return false;
		if (desParametro == null) {
			if (other.desParametro != null)
				return false;
		} else if (!desParametro.equals(other.desParametro))
			return false;
		if (idParametro == null) {
			if (other.idParametro != null)
				return false;
		} else if (!idParametro.equals(other.idParametro))
			return false;
		if (vlrPadrao == null) {
			if (other.vlrPadrao != null)
				return false;
		} else if (!vlrPadrao.equals(other.vlrPadrao))
			return false;
		return true;
	}

}
