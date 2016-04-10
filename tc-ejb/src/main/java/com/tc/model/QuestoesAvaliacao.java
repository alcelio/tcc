package com.tc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tc.model.PK.QuestoesAvaliacaoPK;

/**
 * Entity implementation class for Entity: QuestoesAvaliacao
 *
 */
@Entity
@NamedQuery(name = "QuestoesAvaliacao.findAll", query = "SELECT q FROM QuestoesAvaliacao q")
public class QuestoesAvaliacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private QuestoesAvaliacaoPK id;

	private boolean corrigida;
	private  Float vlrQuestao;
	private String vlrConceito;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaCorrecao;

	//bi-directional many-to-one association to Avaliacao
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="idAvaliacao", insertable=false, updatable=false  )
	private Avaliacao avaliacao;

	//bi-directional many-to-one association to Questao
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idQuestao", insertable=false, updatable=false)
	private Questao questao;
	
	public QuestoesAvaliacao() {
	}

	public QuestoesAvaliacaoPK getId() {
		return id;
	}

	public void setId(QuestoesAvaliacaoPK id) {
		this.id = id;
	}

	public boolean isCorrigida() {
		return corrigida;
	}

	public void setCorrigida(boolean corrigida) {
		this.corrigida = corrigida;
	}

	public Float getVlrQuestao() {
		return vlrQuestao;
	}

	public void setVlrQuestao(Float vlrQuestao) {
		this.vlrQuestao = vlrQuestao;
	}

	public String getVlrConceito() {
		return vlrConceito;
	}

	public void setVlrConceito(String vlrConceito) {
		this.vlrConceito = vlrConceito;
	}

	public Date getDtaCorrecao() {
		return dtaCorrecao;
	}

	public void setDtaCorrecao(Date dtaCorrecao) {
		this.dtaCorrecao = dtaCorrecao;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avaliacao == null) ? 0 : avaliacao.hashCode());
		result = prime * result + (corrigida ? 1231 : 1237);
		result = prime * result + ((dtaCorrecao == null) ? 0 : dtaCorrecao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((questao == null) ? 0 : questao.hashCode());
		result = prime * result + ((vlrConceito == null) ? 0 : vlrConceito.hashCode());
		result = prime * result + ((vlrQuestao == null) ? 0 : vlrQuestao.hashCode());
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
		QuestoesAvaliacao other = (QuestoesAvaliacao) obj;
		if (avaliacao == null) {
			if (other.avaliacao != null)
				return false;
		} else if (!avaliacao.equals(other.avaliacao))
			return false;
		if (corrigida != other.corrigida)
			return false;
		if (dtaCorrecao == null) {
			if (other.dtaCorrecao != null)
				return false;
		} else if (!dtaCorrecao.equals(other.dtaCorrecao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (questao == null) {
			if (other.questao != null)
				return false;
		} else if (!questao.equals(other.questao))
			return false;
		if (vlrConceito == null) {
			if (other.vlrConceito != null)
				return false;
		} else if (!vlrConceito.equals(other.vlrConceito))
			return false;
		if (vlrQuestao == null) {
			if (other.vlrQuestao != null)
				return false;
		} else if (!vlrQuestao.equals(other.vlrQuestao))
			return false;
		return true;
	}
}
