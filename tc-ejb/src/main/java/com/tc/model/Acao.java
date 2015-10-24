package com.tc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Acao
 *
 */
@Entity
@NamedQuery(name = "Acao.findAll", query = "SELECT a FROM Acao a")
public class Acao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idAcao;

	private String dsAcao;

	// bi-directional many-to-one association to Historico
	@OneToMany(mappedBy = "acao")
	private List<Historico> historicoAcao;

	public Acao() {
		historicoAcao = new ArrayList<Historico>();
	}

	public Integer getIdAcao() {
		return this.idAcao;
	}

	public void setIdAcao(Integer idAcao) {
		this.idAcao = idAcao;
	}

	public String getDsAcao() {
		return this.dsAcao;
	}

	public void setDsAcao(String dsAcao) {
		this.dsAcao = dsAcao;
	}

}
