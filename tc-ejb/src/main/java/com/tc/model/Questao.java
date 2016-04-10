package com.tc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
	private String pergunta;
	private String tipoQuestao;
	private String grauDificuldade;
	@Temporal(TemporalType.DATE)
	private Date dataInclusao;
	private String opcaoA;
	private String opcaoB;
	private String opcaoC;
	private String opcaoD;
	private String opcaoE;
	

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
	
	private String recomendacaoErro;
	
	private String recomendacaoAcerto;

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