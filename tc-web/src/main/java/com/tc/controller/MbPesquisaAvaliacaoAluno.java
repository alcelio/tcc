package com.tc.controller;

import static com.tc.controller.MbLoginController.getUsuarioLogado;
import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static com.tc.util.IavaliarGlobal.PAGINA_RESPONDER_AVALIACAO;
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

import com.tc.data.AvaliacaoBeanDao;
import com.tc.data.AvaliacoesBeanDao;
import com.tc.model.Avaliacoes;
import com.tc.model.Disciplina;
import com.tc.suport.IavaliarService;

@ManagedBean
@SessionScoped
public class MbPesquisaAvaliacaoAluno {

	@EJB
	AvaliacaoBeanDao daoAvaliacao;
	@EJB
	AvaliacoesBeanDao daoAvaliacoes;

	@ManagedProperty("#{iavaliarService}")
	private IavaliarService service;

	private Disciplina disciplina = new Disciplina();
	
	
	private String status="";

	private List<Avaliacoes> avaliacoes;

	private String caminhoOrigem;

	@PostConstruct
	public void init() {
		// Carrega as avaliações para o aluno logado
		try {
			setAvaliacoes(daoAvaliacoes.listarAvaliacoesAluno(getUsuarioLogado(), getStatus(),
					getDisciplina()));
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
			setAvaliacoes(daoAvaliacoes.listarAvaliacoesAluno(getUsuarioLogado(), getStatus(),
					getDisciplina()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_ERROR, "Erro ao aplicar filtro para as avaliações!", e.getMessage()));
		}
	}

	public String responderAvaliacao() {
		return PAGINA_RESPONDER_AVALIACAO;
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
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public void setService(IavaliarService service) {
		this.service = service;
	}
	
}
