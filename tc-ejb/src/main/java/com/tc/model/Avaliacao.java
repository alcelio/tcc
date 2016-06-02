package com.tc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Classe implementada para a entidade Avaliacao
 *
 */
@Entity
@NamedQuery(name = "Avaliacao.findAll", query = "SELECT a FROM Avaliacao a")
public class Avaliacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idAvaliacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAvaliacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFimAvaliacao;
	
	@Temporal(TemporalType.DATE)
	private Date dataInclusao;
	
	private String tituloAvaliacao;
	private String orientacoes;
	private String criteriosCorrecao;
	private Long notaMaxima;
	private String conceitoGeral;
	private boolean concluida;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idDisciplina")
	private Disciplina disciplina;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idTurma")
	private Turma turma;

	@ManyToOne
	@JoinColumn(name = "idProfessor")
	private Usuario professor;

	//bi-directional many-to-one association to Questoesavaliacao
	@OneToMany(mappedBy="avaliacao", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT) 
	private List<QuestoesAvaliacao> questoesAvaliacao;
	
	//bi-directional many-to-one association to Questoesavaliacao
	@OneToMany(mappedBy="avaliacao", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT) 
	private List<Respostas> respostas;
	
	public Avaliacao() {
		turma = new Turma();
		questoesAvaliacao = new ArrayList<QuestoesAvaliacao>();
		disciplina = new Disciplina();
		respostas = new ArrayList<Respostas>();
	}

	public List<Respostas> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Respostas> respostas) {
		this.respostas = respostas;
	}

	public Integer getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(Integer idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public Date getDataFimAvaliacao() {
		return dataFimAvaliacao;
	}

	public void setDataFimAvaliacao(Date dataFimAvaliacao) {
		this.dataFimAvaliacao = dataFimAvaliacao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getTituloAvaliacao() {
		return tituloAvaliacao;
	}

	public void setTituloAvaliacao(String tituloAvaliacao) {
		this.tituloAvaliacao = tituloAvaliacao;
	}

	public String getOrientacoes() {
		return orientacoes;
	}

	public void setOrientacoes(String orientacoes) {
		this.orientacoes = orientacoes;
	}

	public String getCriteriosCorrecao() {
		return criteriosCorrecao;
	}

	public void setCriteriosCorrecao(String criteriosCorrecao) {
		this.criteriosCorrecao = criteriosCorrecao;
	}

	public Long getNotaMaxima() {
		return notaMaxima;
	}

	public void setNotaMaxima(Long notaMaxima) {
		this.notaMaxima = notaMaxima;
	}

	public String getConceitoGeral() {
		return conceitoGeral;
	}

	public void setConceitoGeral(String conceitoGeral) {
		this.conceitoGeral = conceitoGeral;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Usuario getProfessor() {
		return professor;
	}

	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}

	public List<QuestoesAvaliacao> getQuestoesAvaliacao() {
		return questoesAvaliacao;
	}

	public void setQuestoesAvaliacao(List<QuestoesAvaliacao> questoesAvaliacao) {
		this.questoesAvaliacao = questoesAvaliacao;
	}

	public boolean isConcluida() {
		return concluida;
	}

	public void setConcluida(boolean concluida) {
		this.concluida = concluida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conceitoGeral == null) ? 0 : conceitoGeral.hashCode());
		result = prime * result + (concluida ? 1231 : 1237);
		result = prime * result + ((criteriosCorrecao == null) ? 0 : criteriosCorrecao.hashCode());
		result = prime * result + ((dataAvaliacao == null) ? 0 : dataAvaliacao.hashCode());
		result = prime * result + ((dataFimAvaliacao == null) ? 0 : dataFimAvaliacao.hashCode());
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((disciplina == null) ? 0 : disciplina.hashCode());
		result = prime * result + ((idAvaliacao == null) ? 0 : idAvaliacao.hashCode());
		result = prime * result + ((notaMaxima == null) ? 0 : notaMaxima.hashCode());
		result = prime * result + ((orientacoes == null) ? 0 : orientacoes.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((questoesAvaliacao == null) ? 0 : questoesAvaliacao.hashCode());
		result = prime * result + ((respostas == null) ? 0 : respostas.hashCode());
		result = prime * result + ((tituloAvaliacao == null) ? 0 : tituloAvaliacao.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
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
		Avaliacao other = (Avaliacao) obj;
		if (conceitoGeral == null) {
			if (other.conceitoGeral != null)
				return false;
		} else if (!conceitoGeral.equals(other.conceitoGeral))
			return false;
		if (concluida != other.concluida)
			return false;
		if (criteriosCorrecao == null) {
			if (other.criteriosCorrecao != null)
				return false;
		} else if (!criteriosCorrecao.equals(other.criteriosCorrecao))
			return false;
		if (dataAvaliacao == null) {
			if (other.dataAvaliacao != null)
				return false;
		} else if (!dataAvaliacao.equals(other.dataAvaliacao))
			return false;
		if (dataFimAvaliacao == null) {
			if (other.dataFimAvaliacao != null)
				return false;
		} else if (!dataFimAvaliacao.equals(other.dataFimAvaliacao))
			return false;
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
		if (idAvaliacao == null) {
			if (other.idAvaliacao != null)
				return false;
		} else if (!idAvaliacao.equals(other.idAvaliacao))
			return false;
		if (notaMaxima == null) {
			if (other.notaMaxima != null)
				return false;
		} else if (!notaMaxima.equals(other.notaMaxima))
			return false;
		if (orientacoes == null) {
			if (other.orientacoes != null)
				return false;
		} else if (!orientacoes.equals(other.orientacoes))
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		if (questoesAvaliacao == null) {
			if (other.questoesAvaliacao != null)
				return false;
		} else if (!questoesAvaliacao.equals(other.questoesAvaliacao))
			return false;
		if (respostas == null) {
			if (other.respostas != null)
				return false;
		} else if (!respostas.equals(other.respostas))
			return false;
		if (tituloAvaliacao == null) {
			if (other.tituloAvaliacao != null)
				return false;
		} else if (!tituloAvaliacao.equals(other.tituloAvaliacao))
			return false;
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		return true;
	}
	
	
}
