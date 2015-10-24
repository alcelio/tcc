package com.tc.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;

import com.tc.data.AvaliacaoBeanDao;
import com.tc.data.DisciplinaBeanDao;
import com.tc.data.StatuAvaliacaoDao;
import com.tc.data.TurmaBeanDao;
import com.tc.model.Avaliacao;
import com.tc.model.Disciplina;
import com.tc.model.Turma;

@ManagedBean(name = "dtFtAvaliacao")
@SessionScoped
public class MbPesquisaAvaliacaoAluno {
	@EJB
	DisciplinaBeanDao daoDisciplina;
	@EJB
	AvaliacaoBeanDao daoAvaliacao;
	@EJB	
	TurmaBeanDao daoTurma;
	@EJB
	StatuAvaliacaoDao daoStatusAvaliacao;
	
	private List<Avaliacao> avaliacoes;
	private List<Disciplina> disciplinas;
	private List<Avaliacao> filtroAvaliacao;
	private List<Turma> turmas;
	private List<String> enunciadoAvaliacao;
	private String caminhoOrigem;
	private Avaliacao avaliacao;

	@PostConstruct
	public void init() {
		avaliacoes = daoAvaliacao.listarAvaliacoes();
		disciplinas = daoDisciplina.listarDisciplina();
		turmas = daoTurma.listarTurmas();
		//List<Avaliacao> teste = daoAvaliacao.buscaAvaliacoesTurmaAluno(BeanUsuarioLogado.usualioLogado);
	}

	public String responderAvaliacao(){
		return "";
	}
	
	public void setaCaminhoOrigem(String origem) {
		this.caminhoOrigem = origem;
	}

	public String goBack() {
		if (StringUtils.isBlank(this.caminhoOrigem)) {
			return "/restrito/home.jsf";
		}
		return this.caminhoOrigem;
	}

	public List<Avaliacao> getAvaliacoes() {
		avaliacoes = daoAvaliacao.listarAvaliacoes();
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Avaliacao> getFiltroAvaliacao() {
		return filtroAvaliacao;
	}

	public void setFiltroAvaliacao(List<Avaliacao> filtroAvaliacao) {
		this.filtroAvaliacao = filtroAvaliacao;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<String> getEnunciadoAvaliacao() {
		return enunciadoAvaliacao;
	}

	public void setEnunciadoAvaliacao(List<String> enunciadoAvaliacao) {
		this.enunciadoAvaliacao = enunciadoAvaliacao;
	}


	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
	

}
