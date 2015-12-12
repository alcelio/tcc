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
@NamedQuery(name = "ParametrosAgentes.findAll", query = "SELECT a FROM ParametrosAgentes a")
public class ParametrosAgentes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idParametroAgente;

	@ManyToOne
	@JoinColumn(name = "idParametro")
	private Parametros parametro;
		
	private String desValorParametro;

	public ParametrosAgentes() {
	}

	public Integer getIdParametroAgente() {
		return idParametroAgente;
	}

	public void setIdParametroAgente(Integer idParametroAgente) {
		this.idParametroAgente = idParametroAgente;
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
	
}
