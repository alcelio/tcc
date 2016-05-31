package com.tc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * Entity implementation class for Entity: Historico
 *
 */
@Entity
@NamedQuery(name = "HistoricoResponderAvalicao.findAll", query = "SELECT h FROM HistoricoResponderAvaliacao h")
public class HistoricoResponderAvaliacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idHistorico;

	//bi-directional many-to-one association to Questao
	@ManyToOne//(fetch = FetchType.EAGER)
	@JoinColumn(name="idAluno")
	private Usuario aluno ;
	
	//bi-directional many-to-one association to Avaliacoes
	@ManyToOne//(fetch = FetchType.EAGER)
	@JoinColumn(name="idQuestao")
	private Questao questao ;
	
	@ManyToOne//(fetch = FetchType.EAGER)
	@JoinColumn(name="idAvaliacao")
	private Avaliacao avaliacao ;

	private long tempoRespondendo;

	public HistoricoResponderAvaliacao() {
	}

	

	public Integer getIdHistorico() {
		return idHistorico;
	}



	public void setIdHistorico(Integer idHistorico) {
		this.idHistorico = idHistorico;
	}


	public Usuario getAluno() {
		return aluno;
	}


	public void setAluno(Usuario aluno) {
		this.aluno = aluno;
	}


	public Questao getQuestao() {
		return questao;
	}


	public void setQuestao(Questao questao) {
		this.questao = questao;
	}


	public Avaliacao getAvaliacao() {
		return avaliacao;
	}


	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public long getTempoRespondendo() {
		return tempoRespondendo;
	}

	public void setTempoRespondendo(long tempoRespondendo) {
		this.tempoRespondendo = tempoRespondendo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((avaliacao == null) ? 0 : avaliacao.hashCode());
		result = prime * result + ((questao == null) ? 0 : questao.hashCode());
		result = prime * result + (int) (tempoRespondendo ^ (tempoRespondendo >>> 32));
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
		HistoricoResponderAvaliacao other = (HistoricoResponderAvaliacao) obj;
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
		if (questao == null) {
			if (other.questao != null)
				return false;
		} else if (!questao.equals(other.questao))
			return false;
		if (tempoRespondendo != other.tempoRespondendo)
			return false;
		return true;
	}

	
}
