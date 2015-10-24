package com.tc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * The persistent class for the Endereco database table.
 * 
 */
@Entity
@NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int idEndereco;
	@Column(nullable = false)
	private String dsCEP;
	@Column(nullable = true)
	private String dsBairro;
	@Column(nullable = true)
	private String dsEndereco;
	@Column(nullable = true)
	private String dsNumero;
	@Column(length = 30)
	private String latitude;
	@Column(length = 30)
	private String longitude;
	private String dsComplemento;
	private String dsCidade;
	@Column(length = 2)
	private String dsEstado;

	@OneToOne
	@PrimaryKeyJoinColumn(name = "idEndereco")
	private Usuario usuario;

	public Endereco() {
	}

	public int getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getDsCEP() {
		return dsCEP;
	}

	public void setDsCEP(String dsCEP) {
		this.dsCEP = dsCEP;
	}

	public String getDsBairro() {
		return dsBairro;
	}

	public void setDsBairro(String dsBairro) {
		this.dsBairro = dsBairro;
	}

	public String getDsEndereco() {
		return dsEndereco;
	}

	public void setDsEndereco(String dsEndereco) {
		this.dsEndereco = dsEndereco;
	}

	public String getDsNumero() {
		return dsNumero;
	}

	public void setDsNumero(String dsNumero) {
		this.dsNumero = dsNumero;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDsComplemento() {
		return dsComplemento;
	}

	public void setDsComplemento(String dsComplemento) {
		this.dsComplemento = dsComplemento;
	}

	public String getDsCidade() {
		return dsCidade;
	}

	public void setDsCidade(String dsCidade) {
		this.dsCidade = dsCidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDsEstado() {
		return dsEstado;
	}

	public void setDsEstado(String dsEstado) {
		this.dsEstado = dsEstado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dsBairro == null) ? 0 : dsBairro.hashCode());
		result = prime * result + ((dsCEP == null) ? 0 : dsCEP.hashCode());
		result = prime * result + ((dsCidade == null) ? 0 : dsCidade.hashCode());
		result = prime * result + ((dsComplemento == null) ? 0 : dsComplemento.hashCode());
		result = prime * result + ((dsEndereco == null) ? 0 : dsEndereco.hashCode());
		result = prime * result + ((dsEstado == null) ? 0 : dsEstado.hashCode());
		result = prime * result + ((dsNumero == null) ? 0 : dsNumero.hashCode());
		result = prime * result + idEndereco;
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
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
		Endereco other = (Endereco) obj;
		if (dsBairro == null) {
			if (other.dsBairro != null)
				return false;
		} else if (!dsBairro.equals(other.dsBairro))
			return false;
		if (dsCEP == null) {
			if (other.dsCEP != null)
				return false;
		} else if (!dsCEP.equals(other.dsCEP))
			return false;
		if (dsCidade == null) {
			if (other.dsCidade != null)
				return false;
		} else if (!dsCidade.equals(other.dsCidade))
			return false;
		if (dsComplemento == null) {
			if (other.dsComplemento != null)
				return false;
		} else if (!dsComplemento.equals(other.dsComplemento))
			return false;
		if (dsEndereco == null) {
			if (other.dsEndereco != null)
				return false;
		} else if (!dsEndereco.equals(other.dsEndereco))
			return false;
		if (dsEstado == null) {
			if (other.dsEstado != null)
				return false;
		} else if (!dsEstado.equals(other.dsEstado))
			return false;
		if (dsNumero == null) {
			if (other.dsNumero != null)
				return false;
		} else if (!dsNumero.equals(other.dsNumero))
			return false;
		if (idEndereco != other.idEndereco)
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}