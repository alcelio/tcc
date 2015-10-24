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
	// bi-directional many-to-one association to Questoesavaliacao
	// @OneToMany
	// private List<QuestoesAvaliacao> questoesAvaliacao;

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

	public Questao() {
		professor = new Professor();
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

	public Usuario getProfessor() {
		return professor;
	}

	public void setProfessor(Usuario professor) {
		this.professor = professor;
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
	
}