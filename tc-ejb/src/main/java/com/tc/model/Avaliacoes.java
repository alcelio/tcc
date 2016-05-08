package com.tc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tc.model.PK.AvalicoesPK;

/**
 * Entity implementation class for Entity: Avaliacoes
 *
 */
@Entity
@NamedQuery(name = "Avaliacoes.findAll", query = "SELECT l FROM Avaliacoes l")
public class Avaliacoes implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AvalicoesPK id;

	private boolean avaliacaoCorrigida;

	private boolean avalicaoRespondida;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaCorrecaoSistema;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaCorrecaoProfessor;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaRespondida;
	
	@Column(length=30)
	private String statusAvaliacao;
	
	//bi-directional many-to-one association to Avaliacao
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="idAvaliacao", insertable=false, updatable=false  )
	private Avaliacao avaliacao;
	
	//bi-directional many-to-one association to Aluno
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="idAluno", insertable=false, updatable=false  )
	private Usuario aluno;
	
	//bi-directional many-to-one association to Aluno
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="idProfessor" )
	private Usuario professor;
	
		
	public Avaliacoes() {
	}

	public AvalicoesPK getId() {
		return id;
	}

	public void setId(AvalicoesPK id) {
		this.id = id;
	}

	public boolean isAvaliacaoCorrigida() {
		return avaliacaoCorrigida;
	}

	public void setAvaliacaoCorrigida(boolean avaliacaoCorrigida) {
		this.avaliacaoCorrigida = avaliacaoCorrigida;
	}

	public boolean isAvalicaoRespondida() {
		return avalicaoRespondida;
	}

	public void setAvalicaoRespondida(boolean avalicaoRespondida) {
		this.avalicaoRespondida = avalicaoRespondida;
	}

	public Date getDtaCorrecaoSistema() {
		return dtaCorrecaoSistema;
	}

	public void setDtaCorrecaoSistema(Date dtaCorrecaoSistema) {
		this.dtaCorrecaoSistema = dtaCorrecaoSistema;
	}

	public Date getDtaCorrecaoProfessor() {
		return dtaCorrecaoProfessor;
	}

	public void setDtaCorrecaoProfessor(Date dtaCorrecaoProfessor) {
		this.dtaCorrecaoProfessor = dtaCorrecaoProfessor;
	}

	public String getStatusAvaliacao() {
		return statusAvaliacao;
	}

	public void setStatusAvaliacao(String statusAvaliacao) {
		this.statusAvaliacao = statusAvaliacao;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Usuario getAluno() {
		return aluno;
	}

	public void setAluno(Usuario aluno) {
		this.aluno = aluno;
	}

	public Usuario getProfessor() {
		return professor;
	}

	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}

	public Date getDtaRespondida() {
		return dtaRespondida;
	}

	public void setDtaRespondida(Date dtaRespondida) {
		this.dtaRespondida = dtaRespondida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((avaliacao == null) ? 0 : avaliacao.hashCode());
		result = prime * result + (avaliacaoCorrigida ? 1231 : 1237);
		result = prime * result + (avalicaoRespondida ? 1231 : 1237);
		result = prime * result + ((dtaCorrecaoProfessor == null) ? 0 : dtaCorrecaoProfessor.hashCode());
		result = prime * result + ((dtaCorrecaoSistema == null) ? 0 : dtaCorrecaoSistema.hashCode());
		result = prime * result + ((dtaRespondida == null) ? 0 : dtaRespondida.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((statusAvaliacao == null) ? 0 : statusAvaliacao.hashCode());
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
		Avaliacoes other = (Avaliacoes) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (avaliacao == null) {
			if (other.avaliacao != null)
				return false;
		} else if (!avaliacao.equals(other.avaliacao))
			return false;
		if (avaliacaoCorrigida != other.avaliacaoCorrigida)
			return false;
		if (avalicaoRespondida != other.avalicaoRespondida)
			return false;
		if (dtaCorrecaoProfessor == null) {
			if (other.dtaCorrecaoProfessor != null)
				return false;
		} else if (!dtaCorrecaoProfessor.equals(other.dtaCorrecaoProfessor))
			return false;
		if (dtaCorrecaoSistema == null) {
			if (other.dtaCorrecaoSistema != null)
				return false;
		} else if (!dtaCorrecaoSistema.equals(other.dtaCorrecaoSistema))
			return false;
		if (dtaRespondida == null) {
			if (other.dtaRespondida != null)
				return false;
		} else if (!dtaRespondida.equals(other.dtaRespondida))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		if (statusAvaliacao == null) {
			if (other.statusAvaliacao != null)
				return false;
		} else if (!statusAvaliacao.equals(other.statusAvaliacao))
			return false;
		return true;
	}

}
