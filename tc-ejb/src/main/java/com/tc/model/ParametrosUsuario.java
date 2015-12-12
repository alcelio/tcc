package com.tc.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.tc.model.PK.ParametrosUsuarioPK;

/**
 * Entity implementation class for Entity: ParametrosUsuario
 *
 */
@Entity
@NamedQuery(name = "ParametrosUsuario.findAll", query = "SELECT a FROM ParametrosUsuario a")
public class ParametrosUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ParametrosUsuarioPK id;
	
	//bi-directional many-to-one association to Avaliacao
	@ManyToOne
	@JoinColumn(name="idUsuario", insertable=false, updatable=false )
	private Usuario usuario;

	//bi-directional many-to-one association to Questao
	@ManyToOne
	@JoinColumn(name="idParametro", insertable=false, updatable=false)
	private Parametros parametro;
	
	private String desValorParametro;

	public ParametrosUsuario() {
		super();
	}

	public ParametrosUsuarioPK getId() {
		return id;
	}

	public void setId(ParametrosUsuarioPK id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((parametro == null) ? 0 : parametro.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		ParametrosUsuario other = (ParametrosUsuario) obj;
		if (desValorParametro == null) {
			if (other.desValorParametro != null)
				return false;
		} else if (!desValorParametro.equals(other.desValorParametro))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (parametro == null) {
			if (other.parametro != null)
				return false;
		} else if (!parametro.equals(other.parametro))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}   

	
   
}
