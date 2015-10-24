package com.tc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Acesso
 *
 */
@Entity
@NamedQuery(name = "Acesso.findAll", query = "SELECT a FROM Acesso a")
public class Acesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idAcesso;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaAcesso;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaSaida;

	// @OneToMany(mappedBy="acesso")
	// private List<Historico> historicoAcesso;
	//

	public Acesso() {
		// historicoAcesso = new ArrayList<Historico>();
	}

	public Integer getIdAcesso() {
		return this.idAcesso;
	}

	public void setIdAcesso(Integer idAcesso) {
		this.idAcesso = idAcesso;
	}

	public Date getDtaAcesso() {
		return this.dtaAcesso;
	}

	public void setDtaAcesso(Date dtaAcesso) {
		this.dtaAcesso = dtaAcesso;
	}

	public Date getDtaSaida() {
		return this.dtaSaida;
	}

	public void setDtaSaida(Date dtaSaida) {
		this.dtaSaida = dtaSaida;
	}

}
