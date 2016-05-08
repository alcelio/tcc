package com.tc.controller;

import static com.tc.controller.MbLoginController.getUsuarioLogado;
import static com.tc.util.IavaliarGlobal.PAGINA_CORRECAO_AVALIACAO;
import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tc.data.AvaliacoesBeanDao;
import com.tc.model.Avaliacoes;
import com.tc.model.Disciplina;
import com.tc.model.Turma;
import com.tc.suport.IavaliarService;
import com.tc.util.IavaliarGlobal;

@ManagedBean
@SessionScoped
public class MbPesquisaAvaliacaoProfessor {

	@EJB
	AvaliacoesBeanDao daoAvaliacoes;

	private String status="";

	private Disciplina disciplina;

	private List<Avaliacoes> avaliacoes;

	private String caminhoOrigem;
	
	private Turma turma;
	
	@ManagedProperty("#{iavaliarService}")
	private IavaliarService service;

	@PostConstruct
	public void init() {
		setDisciplina(new Disciplina());
		setTurma(new Turma());
		// Carrega as avaliações para o aluno logado
		try {
			setAvaliacoes(daoAvaliacoes.listarAvaliacoesProfessor(MbLoginController.getUsuarioLogado(),
					getStatus(), getDisciplina()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_ERROR, "Erro ao aplicar filtros!", e.getMessage()));
			return;
		}
	}

	/**
	 * Método que aplica filtro nas avaliações de acordo disciplina e status de
	 * avaliação selecionados;
	 */
	public void aplicaFiltroAvaliacao() {
		try {
			setAvaliacoes(daoAvaliacoes.listarAvaliacoesProfessor(getUsuarioLogado(),
					getStatus(), getDisciplina()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_ERROR, "Erro ao aplicar filtros!", e.getMessage()));
		}
	}
	/**
	 * Retorna veradeiro se o status da avaliação estiver como pendente
	 * @return boolean
	 */
	public boolean corrigir(Integer status) {
		if (status != null && IavaliarGlobal.STATUS_AVALIACAO_PENDETE.equals(status))
			return true;
		return false;
	}

	public String corrigirAvaliacao() {
		return PAGINA_CORRECAO_AVALIACAO;
	}

	public void setaCaminhoOrigem(String origem) {
		this.caminhoOrigem = origem;
	}

	/**
	 * Método que indica para que página deve seguir o programa
	 * 
	 * @return
	 */
	public String goBack() {
		if (isBlank(getCaminhoOrigem())) {
			return PAGINA_HOME;
		} else {
			return getCaminhoOrigem();
		}
	}

	
	public List<Avaliacoes> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacoes> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public String getCaminhoOrigem() {
		return caminhoOrigem;
	}

	public void setCaminhoOrigem(String caminhoOrigem) {
		this.caminhoOrigem = caminhoOrigem;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<String> getStatusAvaliacao() {
		return service.getStatusAvaliacao();
	}
	
	public void setService(IavaliarService service) {
		this.service = service;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	

}
