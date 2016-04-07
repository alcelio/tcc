package com.tc.controller;

import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
		try {
			avaliacoes = daoAvaliacao.listarAvaliacoesAluno(MbLoginController.getUsuarioLogado());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_ERROR, "Selecione uma disciplina para prosseguir!", e.getMessage()));
			return;
		}
		disciplinas = daoDisciplina.listarDisciplina();
		turmas = daoTurma.listarTurmas();
	}

	public String responderAvaliacao(){
		return "";
	}
	
	public void setaCaminhoOrigem(String origem) {
		this.caminhoOrigem = origem;
	}

	/**
	 * Método que indica para que página deve seguir o programa
	 * @return
	 */
	public String goBack(){
		if(isBlank(getCaminhoOrigem())){
			return PAGINA_HOME;
		}else{
			return getCaminhoOrigem();
		}
	}

	public List<Avaliacao> getAvaliacoes() throws Exception {
		avaliacoes = daoAvaliacao.listarAvaliacoesAluno(MbLoginController.getUsuarioLogado());
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

	public String getCaminhoOrigem() {
		return caminhoOrigem;
	}

	public void setCaminhoOrigem(String caminhoOrigem) {
		this.caminhoOrigem = caminhoOrigem;
	}
	

}
