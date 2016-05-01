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
	private boolean respondida;
	private  Float vlrQuestao;
	private String vlrConceito;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaCorrecao;
	private boolean respAlunoOpcaoA = false;
	private boolean respAlunoOpcaoB = false;
	private boolean respAlunoOpcaoC = false;
	private boolean respAlunoOpcaoD = false;
	private boolean respAlunoOpcaoE = false;
	private String respOrdemAlunoA;
	private String respOrdemAlunoB;
	private String respOrdemAlunoC;
	private String respOrdemAlunoD;
	private String respOrdemAlunoE;
	private String respDissetativa;
	private String obsAlunoQuestao;

	//bi-directional many-to-one association to Avaliacao
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

	public String getRespOrdemAlunoA() {
		return respOrdemAlunoA;
	}

	public void setRespOrdemAlunoA(String respOrdemAlunoA) {
		this.respOrdemAlunoA = respOrdemAlunoA;
	}

	public String getRespOrdemAlunoB() {
		return respOrdemAlunoB;
	}

	public void setRespOrdemAlunoB(String respOrdemAlunoB) {
		this.respOrdemAlunoB = respOrdemAlunoB;
	}

	public String getRespOrdemAlunoC() {
		return respOrdemAlunoC;
	}

	public void setRespOrdemAlunoC(String respOrdemAlunoC) {
		this.respOrdemAlunoC = respOrdemAlunoC;
	}

	public String getRespOrdemAlunoD() {
		return respOrdemAlunoD;
	}

	public void setRespOrdemAlunoD(String respOrdemAlunoD) {
		this.respOrdemAlunoD = respOrdemAlunoD;
	}

	public String getRespOrdemAlunoE() {
		return respOrdemAlunoE;
	}

	public void setRespOrdemAlunoE(String respOrdemAlunoE) {
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
	
	public boolean isRespondida() {
		return respondida;
	}

	public void setRespondida(boolean respondida) {
		this.respondida = respondida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avaliacao == null) ? 0 : avaliacao.hashCode());
		result = prime * result + (corrigida ? 1231 : 1237);
		result = prime * result + ((dtaCorrecao == null) ? 0 : dtaCorrecao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((obsAlunoQuestao == null) ? 0 : obsAlunoQuestao.hashCode());
		result = prime * result + ((questao == null) ? 0 : questao.hashCode());
		result = prime * result + (respAlunoOpcaoA ? 1231 : 1237);
		result = prime * result + (respAlunoOpcaoB ? 1231 : 1237);
		result = prime * result + (respAlunoOpcaoC ? 1231 : 1237);
		result = prime * result + (respAlunoOpcaoD ? 1231 : 1237);
		result = prime * result + (respAlunoOpcaoE ? 1231 : 1237);
		result = prime * result + ((respDissetativa == null) ? 0 : respDissetativa.hashCode());
		result = prime * result + ((respOrdemAlunoA == null) ? 0 : respOrdemAlunoA.hashCode());
		result = prime * result + ((respOrdemAlunoB == null) ? 0 : respOrdemAlunoB.hashCode());
		result = prime * result + ((respOrdemAlunoC == null) ? 0 : respOrdemAlunoC.hashCode());
		result = prime * result + ((respOrdemAlunoD == null) ? 0 : respOrdemAlunoD.hashCode());
		result = prime * result + ((respOrdemAlunoE == null) ? 0 : respOrdemAlunoE.hashCode());
		result = prime * result + (respondida ? 1231 : 1237);
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
		if (respOrdemAlunoA == null) {
			if (other.respOrdemAlunoA != null)
				return false;
		} else if (!respOrdemAlunoA.equals(other.respOrdemAlunoA))
			return false;
		if (respOrdemAlunoB == null) {
			if (other.respOrdemAlunoB != null)
				return false;
		} else if (!respOrdemAlunoB.equals(other.respOrdemAlunoB))
			return false;
		if (respOrdemAlunoC == null) {
			if (other.respOrdemAlunoC != null)
				return false;
		} else if (!respOrdemAlunoC.equals(other.respOrdemAlunoC))
			return false;
		if (respOrdemAlunoD == null) {
			if (other.respOrdemAlunoD != null)
				return false;
		} else if (!respOrdemAlunoD.equals(other.respOrdemAlunoD))
			return false;
		if (respOrdemAlunoE == null) {
			if (other.respOrdemAlunoE != null)
				return false;
		} else if (!respOrdemAlunoE.equals(other.respOrdemAlunoE))
			return false;
		if (respondida != other.respondida)
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
