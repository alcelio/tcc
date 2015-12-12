package com.tc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * Entity implementation class for Entity: ParametrosUsuario
 *
 */
@Entity
@NamedQuery(name = "ParametrosSistema.findAll", query = "SELECT a FROM ParametrosSistema a")
public class ParametrosSistema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idParametroSistema;

	@ManyToOne
	@JoinColumn(name = "idParametro")
	private Parametros parametro;
		
	private String desValorParametro;

	public ParametrosSistema() {
	}

	public Integer getIdParametroSistema() {
		return idParametroSistema;
	}

	public void setIdParametroSistema(Integer idParametroSistema) {
		this.idParametroSistema = idParametroSistema;
	}

	public Parametros getParametro() {
		return parametro;
	}

	public void setParametro(Parametros parametro) {
		this.parametro = parametro;
	}

	public String getDesValorParametro() {
		return desValorParametro;
	}

	public void setDesValorParametro(String desValorParametro) {
		this.desValorParametro = desValorParametro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desValorParametro == null) ? 0 : desValorParametro.hashCode());
		result = prime * result + ((idParametroSistema == null) ? 0 : idParametroSistema.hashCode());
		result = prime * result + ((parametro == null) ? 0 : parametro.hashCode());
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
		ParametrosSistema other = (ParametrosSistema) obj;
		if (desValorParametro == null) {
			if (other.desValorParametro != null)
				return false;
		} else if (!desValorParametro.equals(other.desValorParametro))
			return false;
		if (idParametroSistema == null) {
			if (other.idParametroSistema != null)
				return false;
		} else if (!idParametroSistema.equals(other.idParametroSistema))
			return false;
		if (parametro == null) {
			if (other.parametro != null)
				return false;
		} else if (!parametro.equals(other.parametro))
			return false;
		return true;
	}   
   
}
