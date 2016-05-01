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
	
	@Column(length=1000)
	private String obsAlunoQuestao;
	
	private boolean respondida;
	@Column(length=30)
	private String statusAvaliacao;
	
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
	
	@Column(length=2000)
	private String respDissetativa;
	
	

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

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Usuario getProfessor() {
		return professor;
	}
	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}

	public Usuario getAluno() {
		return aluno;
	}

	public void setAluno(Usuario aluno) {
		this.aluno = aluno;
	}


	public boolean isRespondida() {
		return respondida;
	}

	public void setRespondida(boolean respondida) {
		this.respondida = respondida;
	}
	

	public String getStatusAvaliacao() {
		return statusAvaliacao;
	}

	public void setStatusAvaliacao(String statusAvaliacao) {
		this.statusAvaliacao = statusAvaliacao;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((obsAlunoQuestao == null) ? 0 : obsAlunoQuestao.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
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
		result = prime * result + (respondida ? 1231 : 1237);
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
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
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
		if (respondida != other.respondida)
			return false;
		if (statusAvaliacao == null) {
			if (other.statusAvaliacao != null)
				return false;
		} else if (!statusAvaliacao.equals(other.statusAvaliacao))
			return false;
		return true;
	}

}
