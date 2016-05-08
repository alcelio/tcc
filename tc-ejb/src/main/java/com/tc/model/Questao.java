package com.tc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the Questao database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQuery(name = "Questao.findAll", query = "SELECT q FROM Questao q")
public abstract class Questao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "genQuestao")
	@SequenceGenerator(sequenceName = "questao_idQuestao_seq", name = "genQuestao")
	private Integer idQuestao;

	private boolean publica = true;
	@Column(length= 2000) 
	private String pergunta;
	@Column(length= 30)
	private String tipoQuestao;
	@Column(length= 30)
	private String grauDificuldade;
	@Temporal(TemporalType.DATE)
	private Date dataInclusao;
	
	@Column(length= 1000)
	private String opcaoA;
	@Column(length= 1000)
	private String recomendacaoErroOpcaoA;
	@Column(length= 1000)
	private String opcaoB;
	@Column(length= 1000)
	private String recomendacaoErroOpcaoB;
	@Column(length= 1000)
	private String opcaoC;
	@Column(length= 1000)
	private String recomendacaoErroOpcaoC;
	@Column(length= 1000)
	private String opcaoD;
	@Column(length= 1000)
	private String recomendacaoErroOpcaoD;
	@Column(length= 1000)
	private String opcaoE;
	@Column(length= 1000)
	private String recomendacaoErroOpcaoE;
	
	@ManyToOne
	@JoinColumn(name = "idProfessor")
	private Usuario professor;

	@ManyToOne
	@JoinColumn(name = "idDisciplina")
	private Disciplina disciplina;

	@ManyToOne
	@JoinColumn(name = "idTopicoEstudo")
	private TopicoEstudo topicoEstudo;
	
	//bi-directional many-to-one association to Questoesavaliacao
	@OneToMany(mappedBy="questao")
	private List<QuestoesAvaliacao> questoesAvaliacao;
	
	@Column(length= 1000)
	private String recomendacaoErro;
	
	@Column(length= 1000)
	private String recomendacaoAcerto;
	
	@Temporal(TemporalType.TIME)
	private Date tempoEstimadoResponder;
	

	public Questao() {
		professor = new Usuario();
		topicoEstudo = new TopicoEstudo();
		disciplina = new Disciplina();
	}

	public Integer getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(Integer idQuestao) {
		this.idQuestao = idQuestao;
	}

	public boolean isPublica() {
		return publica;
	}

	public void setPublica(boolean publica) {
		this.publica = publica;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getTipoQuestao() {
		return tipoQuestao;
	}

	public void setTipoQuestao(String tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}

	public String getGrauDificuldade() {
		return grauDificuldade;
	}

	public void setGrauDificuldade(String grauDificuldade) {
		this.grauDificuldade = grauDificuldade;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getOpcaoA() {
		return opcaoA;
	}

	public void setOpcaoA(String opcaoA) {
		this.opcaoA = opcaoA;
	}

	public String getOpcaoB() {
		return opcaoB;
	}

	public void setOpcaoB(String opcaoB) {
		this.opcaoB = opcaoB;
	}

	public String getOpcaoC() {
		return opcaoC;
	}

	public void setOpcaoC(String opcaoC) {
		this.opcaoC = opcaoC;
	}

	public String getOpcaoD() {
		return opcaoD;
	}

	public void setOpcaoD(String opcaoD) {
		this.opcaoD = opcaoD;
	}

	public String getOpcaoE() {
		return opcaoE;
	}

	public void setOpcaoE(String opcaoE) {
		this.opcaoE = opcaoE;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public TopicoEstudo getTopicoEstudo() {
		return topicoEstudo;
	}

	public void setTopicoEstudo(TopicoEstudo topicoEstudo) {
		this.topicoEstudo = topicoEstudo;
	}

	public List<QuestoesAvaliacao> getQuestoesAvaliacao() {
		return questoesAvaliacao;
	}

	public void setQuestoesAvaliacao(List<QuestoesAvaliacao> questoesAvaliacao) {
		this.questoesAvaliacao = questoesAvaliacao;
	}

	public String getRecomendacaoErro() {
		return recomendacaoErro;
	}

	public void setRecomendacaoErro(String recomendacaoErro) {
		this.recomendacaoErro = recomendacaoErro;
	}

	public String getRecomendacaoAcerto() {
		return recomendacaoAcerto;
	}

	public void setRecomendacaoAcerto(String recomendacaoAcerto) {
		this.recomendacaoAcerto = recomendacaoAcerto;
	}
	

	public Usuario getProfessor() {
		return professor;
	}

	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}

	public String getRecomendacaoErroOpcaoA() {
		return recomendacaoErroOpcaoA;
	}

	public void setRecomendacaoErroOpcaoA(String recomendacaoErroOpcaoA) {
		this.recomendacaoErroOpcaoA = recomendacaoErroOpcaoA;
	}

	public String getRecomendacaoErroOpcaoB() {
		return recomendacaoErroOpcaoB;
	}

	public void setRecomendacaoErroOpcaoB(String recomendacaoErroOpcaoB) {
		this.recomendacaoErroOpcaoB = recomendacaoErroOpcaoB;
	}

	public String getRecomendacaoErroOpcaoC() {
		return recomendacaoErroOpcaoC;
	}

	public void setRecomendacaoErroOpcaoC(String recomendacaoErroOpcaoC) {
		this.recomendacaoErroOpcaoC = recomendacaoErroOpcaoC;
	}

	public String getRecomendacaoErroOpcaoD() {
		return recomendacaoErroOpcaoD;
	}

	public void setRecomendacaoErroOpcaoD(String recomendacaoErroOpcaoD) {
		this.recomendacaoErroOpcaoD = recomendacaoErroOpcaoD;
	}

	public String getRecomendacaoErroOpcaoE() {
		return recomendacaoErroOpcaoE;
	}

	public void setRecomendacaoErroOpcaoE(String recomendacaoErroOpcaoE) {
		this.recomendacaoErroOpcaoE = recomendacaoErroOpcaoE;
	}

	public Date getTempoEstimadoResponder() {
		return tempoEstimadoResponder;
	}

	public void setTempoEstimadoResponder(Date tempoEstimadoResponder) {
		this.tempoEstimadoResponder = tempoEstimadoResponder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((disciplina == null) ? 0 : disciplina.hashCode());
		result = prime * result + ((grauDificuldade == null) ? 0 : grauDificuldade.hashCode());
		result = prime * result + ((idQuestao == null) ? 0 : idQuestao.hashCode());
		result = prime * result + ((opcaoA == null) ? 0 : opcaoA.hashCode());
		result = prime * result + ((opcaoB == null) ? 0 : opcaoB.hashCode());
		result = prime * result + ((opcaoC == null) ? 0 : opcaoC.hashCode());
		result = prime * result + ((opcaoD == null) ? 0 : opcaoD.hashCode());
		result = prime * result + ((opcaoE == null) ? 0 : opcaoE.hashCode());
		result = prime * result + ((pergunta == null) ? 0 : pergunta.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + (publica ? 1231 : 1237);
		result = prime * result + ((questoesAvaliacao == null) ? 0 : questoesAvaliacao.hashCode());
		result = prime * result + ((recomendacaoAcerto == null) ? 0 : recomendacaoAcerto.hashCode());
		result = prime * result + ((recomendacaoErro == null) ? 0 : recomendacaoErro.hashCode());
		result = prime * result + ((recomendacaoErroOpcaoA == null) ? 0 : recomendacaoErroOpcaoA.hashCode());
		result = prime * result + ((recomendacaoErroOpcaoB == null) ? 0 : recomendacaoErroOpcaoB.hashCode());
		result = prime * result + ((recomendacaoErroOpcaoC == null) ? 0 : recomendacaoErroOpcaoC.hashCode());
		result = prime * result + ((recomendacaoErroOpcaoD == null) ? 0 : recomendacaoErroOpcaoD.hashCode());
		result = prime * result + ((recomendacaoErroOpcaoE == null) ? 0 : recomendacaoErroOpcaoE.hashCode());
		result = prime * result + ((tempoEstimadoResponder == null) ? 0 : tempoEstimadoResponder.hashCode());
		result = prime * result + ((tipoQuestao == null) ? 0 : tipoQuestao.hashCode());
		result = prime * result + ((topicoEstudo == null) ? 0 : topicoEstudo.hashCode());
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
		Questao other = (Questao) obj;
		if (dataInclusao == null) {
			if (other.dataInclusao != null)
				return false;
		} else if (!dataInclusao.equals(other.dataInclusao))
			return false;
		if (disciplina == null) {
			if (other.disciplina != null)
				return false;
		} else if (!disciplina.equals(other.disciplina))
			return false;
		if (grauDificuldade == null) {
			if (other.grauDificuldade != null)
				return false;
		} else if (!grauDificuldade.equals(other.grauDificuldade))
			return false;
		if (idQuestao == null) {
			if (other.idQuestao != null)
				return false;
		} else if (!idQuestao.equals(other.idQuestao))
			return false;
		if (opcaoA == null) {
			if (other.opcaoA != null)
				return false;
		} else if (!opcaoA.equals(other.opcaoA))
			return false;
		if (opcaoB == null) {
			if (other.opcaoB != null)
				return false;
		} else if (!opcaoB.equals(other.opcaoB))
			return false;
		if (opcaoC == null) {
			if (other.opcaoC != null)
				return false;
		} else if (!opcaoC.equals(other.opcaoC))
			return false;
		if (opcaoD == null) {
			if (other.opcaoD != null)
				return false;
		} else if (!opcaoD.equals(other.opcaoD))
			return false;
		if (opcaoE == null) {
			if (other.opcaoE != null)
				return false;
		} else if (!opcaoE.equals(other.opcaoE))
			return false;
		if (pergunta == null) {
			if (other.pergunta != null)
				return false;
		} else if (!pergunta.equals(other.pergunta))
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		if (publica != other.publica)
			return false;
		if (questoesAvaliacao == null) {
			if (other.questoesAvaliacao != null)
				return false;
		} else if (!questoesAvaliacao.equals(other.questoesAvaliacao))
			return false;
		if (recomendacaoAcerto == null) {
			if (other.recomendacaoAcerto != null)
				return false;
		} else if (!recomendacaoAcerto.equals(other.recomendacaoAcerto))
			return false;
		if (recomendacaoErro == null) {
			if (other.recomendacaoErro != null)
				return false;
		} else if (!recomendacaoErro.equals(other.recomendacaoErro))
			return false;
		if (recomendacaoErroOpcaoA == null) {
			if (other.recomendacaoErroOpcaoA != null)
				return false;
		} else if (!recomendacaoErroOpcaoA.equals(other.recomendacaoErroOpcaoA))
			return false;
		if (recomendacaoErroOpcaoB == null) {
			if (other.recomendacaoErroOpcaoB != null)
				return false;
		} else if (!recomendacaoErroOpcaoB.equals(other.recomendacaoErroOpcaoB))
			return false;
		if (recomendacaoErroOpcaoC == null) {
			if (other.recomendacaoErroOpcaoC != null)
				return false;
		} else if (!recomendacaoErroOpcaoC.equals(other.recomendacaoErroOpcaoC))
			return false;
		if (recomendacaoErroOpcaoD == null) {
			if (other.recomendacaoErroOpcaoD != null)
				return false;
		} else if (!recomendacaoErroOpcaoD.equals(other.recomendacaoErroOpcaoD))
			return false;
		if (recomendacaoErroOpcaoE == null) {
			if (other.recomendacaoErroOpcaoE != null)
				return false;
		} else if (!recomendacaoErroOpcaoE.equals(other.recomendacaoErroOpcaoE))
			return false;
		if (tempoEstimadoResponder == null) {
			if (other.tempoEstimadoResponder != null)
				return false;
		} else if (!tempoEstimadoResponder.equals(other.tempoEstimadoResponder))
			return false;
		if (tipoQuestao == null) {
			if (other.tipoQuestao != null)
				return false;
		} else if (!tipoQuestao.equals(other.tipoQuestao))
			return false;
		if (topicoEstudo == null) {
			if (other.topicoEstudo != null)
				return false;
		} else if (!topicoEstudo.equals(other.topicoEstudo))
			return false;
		return true;
	}

	
}