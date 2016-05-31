package com.tc.model.PK;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class AvalicoesPK implements Serializable {
	private static final long serialVersionUID = 1L;

//	@Column(insertable=false, updatable=false)
	private Integer idAvaliacao;

//	@Column(insertable=false, updatable=false)
	private Integer idAluno;
	
	public AvalicoesPK() {
	}

	public Integer getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(Integer idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAluno == null) ? 0 : idAluno.hashCode());
		result = prime * result + ((idAvaliacao == null) ? 0 : idAvaliacao.hashCode());
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
		AvalicoesPK other = (AvalicoesPK) obj;
		if (idAluno == null) {
			if (other.idAluno != null)
				return false;
		} else if (!idAluno.equals(other.idAluno))
			return false;
		if (idAvaliacao == null) {
			if (other.idAvaliacao != null)
				return false;
		} else if (!idAvaliacao.equals(other.idAvaliacao))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return getIdAluno().toString()+","+getIdAvaliacao()+toString();
	}
	
	
}
