package com.tc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.tc.model.PK.RespostaPK;

/**
 * Entity implementation class for Entity: Acao
 *
 */
@Entity
@NamedQuery(name = "Respostas.findAll", query = "SELECT a FROM Respostas a")
public class Respostas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private RespostaPK id;

	//bi-directional many-to-one association to Questao
	@ManyToOne//(fetch = FetchType.EAGER)
	@JoinColumn(name="idAluno", insertable=false, updatable=false)
	private Usuario aluno ;
	
	//bi-directional many-to-one association to Avaliacoes
	@ManyToOne//(fetch = FetchType.EAGER)
	@JoinColumn(name="idQuestao", insertable=false, updatable=false)
	private Questao questao ;
	
	@ManyToOne//(fetch = FetchType.EAGER)
	@JoinColumn(name="idAvaliacao", insertable=false, updatable=false)
	private Avaliacao avaliacao ;
	
	
	
	private boolean respAlunoOpcaoA = false;
	private boolean respAlunoOpcaoB = false;
	private boolean respAlunoOpcaoC = false;
	private boolean respAlunoOpcaoD = false;
	private boolean respAlunoOpcaoE = false;
	
	private int respOrdemAlunoA;
	private int respOrdemAlunoB;
	private int respOrdemAlunoC;
	private int respOrdemAlunoD;
	private int respOrdemAlunoE;
	
	private String RespAlunoRelOpcaoA;
	private String RespAlunoRelOpcaoB;
	private String RespAlunoRelOpcaoC;
	private String RespAlunoRelOpcaoD;
	private String RespAlunoRelOpcaoE;
	
	@Column(length=1000)
	private String obsAlunoQuestao;
	
	@Column(length=2000)
	private String respDissetativa;
	
	private boolean respondeuCorretamente;
	
	private boolean corrigidaAgente;
	
	public Respostas() {
	}

	public RespostaPK getId() {
		return id;
	}

	public void setId(RespostaPK id) {
		this.id = id;
	}

	public boolean isRespondeuCorretamente() {
		return respondeuCorretamente;
	}

	public void setRespondeuCorretamente(boolean respondeuCorretamente) {
		this.respondeuCorretamente = respondeuCorretamente;
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

	public boolean isRespAlunoOpcaoA() {
		return respAlunoOpcaoA;
	}

	public void setRespAlunoOpcaoA(boolean respAlunoOpcaoA) {
		this.respAlunoOpcaoA = respAlunoOpcaoA;
	}

	public boolean isRespAlunoOpcaoB() {
		return respAlunoOpcaoB;
	}

	public void setRespAlunoOpcaoB(boolean respAlunoOpcaoB) {
		this.respAlunoOpcaoB = respAlunoOpcaoB;
	}

	public boolean isRespAlunoOpcaoC() {
		return respAlunoOpcaoC;
	}

	public void setRespAlunoOpcaoC(boolean respAlunoOpcaoC) {
		this.respAlunoOpcaoC = respAlunoOpcaoC;
	}

	public boolean isRespAlunoOpcaoD() {
		return respAlunoOpcaoD;
	}

	public void setRespAlunoOpcaoD(boolean respAlunoOpcaoD) {
		this.respAlunoOpcaoD = respAlunoOpcaoD;
	}

	public boolean isRespAlunoOpcaoE() {
		return respAlunoOpcaoE;
	}

	public void setRespAlunoOpcaoE(boolean respAlunoOpcaoE) {
		this.respAlunoOpcaoE = respAlunoOpcaoE;
	}

	public int getRespOrdemAlunoA() {
		return respOrdemAlunoA;
	}

	public void setRespOrdemAlunoA(int respOrdemAlunoA) {
		this.respOrdemAlunoA = respOrdemAlunoA;
	}

	public int getRespOrdemAlunoB() {
		return respOrdemAlunoB;
	}

	public void setRespOrdemAlunoB(int respOrdemAlunoB) {
		this.respOrdemAlunoB = respOrdemAlunoB;
	}

	public int getRespOrdemAlunoC() {
		return respOrdemAlunoC;
	}

	public void setRespOrdemAlunoC(int respOrdemAlunoC) {
		this.respOrdemAlunoC = respOrdemAlunoC;
	}

	public int getRespOrdemAlunoD() {
		return respOrdemAlunoD;
	}

	public void setRespOrdemAlunoD(int respOrdemAlunoD) {
		this.respOrdemAlunoD = respOrdemAlunoD;
	}

	public int getRespOrdemAlunoE() {
		return respOrdemAlunoE;
	}

	public void setRespOrdemAlunoE(int respOrdemAlunoE) {
		this.respOrdemAlunoE = respOrdemAlunoE;
	}

	public String getRespDissetativa() {
		return respDissetativa;
	}

	public void setRespDissetativa(String respDissetativa) {
		this.respDissetativa = respDissetativa;
	}

	public String getObsAlunoQuestao() {
		return obsAlunoQuestao;
	}

	public void setObsAlunoQuestao(String obsAlunoQuestao) {
		this.obsAlunoQuestao = obsAlunoQuestao;
	}
	

	public String getRespAlunoRelOpcaoA() {
		return RespAlunoRelOpcaoA;
	}

	public void setRespAlunoRelOpcaoA(String respAlunoRelOpcaoA) {
		RespAlunoRelOpcaoA = respAlunoRelOpcaoA;
	}

	public String getRespAlunoRelOpcaoB() {
		return RespAlunoRelOpcaoB;
	}

	public void setRespAlunoRelOpcaoB(String respAlunoRelOpcaoB) {
		RespAlunoRelOpcaoB = respAlunoRelOpcaoB;
	}

	public String getRespAlunoRelOpcaoC() {
		return RespAlunoRelOpcaoC;
	}

	public void setRespAlunoRelOpcaoC(String respAlunoRelOpcaoC) {
		RespAlunoRelOpcaoC = respAlunoRelOpcaoC;
	}

	public String getRespAlunoRelOpcaoD() {
		return RespAlunoRelOpcaoD;
	}

	public void setRespAlunoRelOpcaoD(String respAlunoRelOpcaoD) {
		RespAlunoRelOpcaoD = respAlunoRelOpcaoD;
	}

	public String getRespAlunoRelOpcaoE() {
		return RespAlunoRelOpcaoE;
	}
	

	public boolean isCorrigidaAgente() {
		return corrigidaAgente;
	}

	public void setCorrigidaAgente(boolean corrigidaAgente) {
		this.corrigidaAgente = corrigidaAgente;
	}

	public void setRespAlunoRelOpcaoE(String respAlunoRelOpcaoE) {
		RespAlunoRelOpcaoE = respAlunoRelOpcaoE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((RespAlunoRelOpcaoA == null) ? 0 : RespAlunoRelOpcaoA.hashCode());
		result = prime * result + ((RespAlunoRelOpcaoB == null) ? 0 : RespAlunoRelOpcaoB.hashCode());
		result = prime * result + ((RespAlunoRelOpcaoC == null) ? 0 : RespAlunoRelOpcaoC.hashCode());
		result = prime * result + ((RespAlunoRelOpcaoD == null) ? 0 : RespAlunoRelOpcaoD.hashCode());
		result = prime * result + ((RespAlunoRelOpcaoE == null) ? 0 : RespAlunoRelOpcaoE.hashCode());
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((avaliacao == null) ? 0 : avaliacao.hashCode());
		result = prime * result + (corrigidaAgente ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((obsAlunoQuestao == null) ? 0 : obsAlunoQuestao.hashCode());
		result = prime * result + ((questao == null) ? 0 : questao.hashCode());
		result = prime * result + (respAlunoOpcaoA ? 1231 : 1237);
		result = prime * result + (respAlunoOpcaoB ? 1231 : 1237);
		result = prime * result + (respAlunoOpcaoC ? 1231 : 1237);
		result = prime * result + (respAlunoOpcaoD ? 1231 : 1237);
		result = prime * result + (respAlunoOpcaoE ? 1231 : 1237);
		result = prime * result + ((respDissetativa == null) ? 0 : respDissetativa.hashCode());
		result = prime * result + respOrdemAlunoA;
		result = prime * result + respOrdemAlunoB;
		result = prime * result + respOrdemAlunoC;
		result = prime * result + respOrdemAlunoD;
		result = prime * result + respOrdemAlunoE;
		result = prime * result + (respondeuCorretamente ? 1231 : 1237);
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
		Respostas other = (Respostas) obj;
		if (RespAlunoRelOpcaoA == null) {
			if (other.RespAlunoRelOpcaoA != null)
				return false;
		} else if (!RespAlunoRelOpcaoA.equals(other.RespAlunoRelOpcaoA))
			return false;
		if (RespAlunoRelOpcaoB == null) {
			if (other.RespAlunoRelOpcaoB != null)
				return false;
		} else if (!RespAlunoRelOpcaoB.equals(other.RespAlunoRelOpcaoB))
			return false;
		if (RespAlunoRelOpcaoC == null) {
			if (other.RespAlunoRelOpcaoC != null)
				return false;
		} else if (!RespAlunoRelOpcaoC.equals(other.RespAlunoRelOpcaoC))
			return false;
		if (RespAlunoRelOpcaoD == null) {
			if (other.RespAlunoRelOpcaoD != null)
				return false;
		} else if (!RespAlunoRelOpcaoD.equals(other.RespAlunoRelOpcaoD))
			return false;
		if (RespAlunoRelOpcaoE == null) {
			if (other.RespAlunoRelOpcaoE != null)
				return false;
		} else if (!RespAlunoRelOpcaoE.equals(other.RespAlunoRelOpcaoE))
			return false;
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
		if (corrigidaAgente != other.corrigidaAgente)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (obsAlunoQuestao == null) {
			if (other.obsAlunoQuestao != null)
				return false;
		} else if (!obsAlunoQuestao.equals(other.obsAlunoQuestao))
			return false;
		if (questao == null) {
			if (other.questao != null)
				return false;
		} else if (!questao.equals(other.questao))
			return false;
		if (respAlunoOpcaoA != other.respAlunoOpcaoA)
			return false;
		if (respAlunoOpcaoB != other.respAlunoOpcaoB)
			return false;
		if (respAlunoOpcaoC != other.respAlunoOpcaoC)
			return false;
		if (respAlunoOpcaoD != other.respAlunoOpcaoD)
			return false;
		if (respAlunoOpcaoE != other.respAlunoOpcaoE)
			return false;
		if (respDissetativa == null) {
			if (other.respDissetativa != null)
				return false;
		} else if (!respDissetativa.equals(other.respDissetativa))
			return false;
		if (respOrdemAlunoA != other.respOrdemAlunoA)
			return false;
		if (respOrdemAlunoB != other.respOrdemAlunoB)
			return false;
		if (respOrdemAlunoC != other.respOrdemAlunoC)
			return false;
		if (respOrdemAlunoD != other.respOrdemAlunoD)
			return false;
		if (respOrdemAlunoE != other.respOrdemAlunoE)
			return false;
		if (respondeuCorretamente != other.respondeuCorretamente)
			return false;
		return true;
	}


	
}
