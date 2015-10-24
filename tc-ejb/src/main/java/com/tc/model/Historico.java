package com.tc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Historico
 *
 */
@Entity
@NamedQuery(name = "Historico.findAll", query = "SELECT h FROM Historico h")
public class Historico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "genHistotico")
	@SequenceGenerator(sequenceName = "historico_idHistorico_seq", name = "genHistotico")
	private Integer idHistorico;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaMomentoAcao;

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "idAcesso")
	private Acesso acesso;

	@ManyToOne
	@JoinColumn(name = "idAcao")
	private Acao acao;

	public Historico() {
		acao = new Acao();
		acesso = new Acesso();
		usuario = new Usuario();
	}

	public Integer getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(Integer idHistorico) {
		this.idHistorico = idHistorico;
	}

	public Date getDtaMomentoAcao() {
		return dtaMomentoAcao;
	}

	public void setDtaMomentoAcao(Date dtaMomentoAcao) {
		this.dtaMomentoAcao = dtaMomentoAcao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Acesso getAcesso() {
		return acesso;
	}

	public void setAcesso(Acesso acesso) {
		this.acesso = acesso;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acao == null) ? 0 : acao.hashCode());
		result = prime * result + ((acesso == null) ? 0 : acesso.hashCode());
		result = prime * result + ((dtaMomentoAcao == null) ? 0 : dtaMomentoAcao.hashCode());
		result = prime * result + ((idHistorico == null) ? 0 : idHistorico.hashCode());
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
		Historico other = (Historico) obj;
		if (acao == null) {
			if (other.acao != null)
				return false;
		} else if (!acao.equals(other.acao))
			return false;
		if (acesso == null) {
			if (other.acesso != null)
				return false;
		} else if (!acesso.equals(other.acesso))
			return false;
		if (dtaMomentoAcao == null) {
			if (other.dtaMomentoAcao != null)
				return false;
		} else if (!dtaMomentoAcao.equals(other.dtaMomentoAcao))
			return false;
		if (idHistorico == null) {
			if (other.idHistorico != null)
				return false;
		} else if (!idHistorico.equals(other.idHistorico))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
