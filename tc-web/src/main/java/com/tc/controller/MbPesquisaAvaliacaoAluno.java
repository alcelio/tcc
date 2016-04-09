package com.tc.controller;

import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static com.tc.util.IavaliarGlobal.PAGINA_RESPONDER_AVALIACAO;
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
import com.tc.model.Avaliacao;
import com.tc.model.Disciplina;
import com.tc.model.StatusAvaliacao;

@ManagedBean
@SessionScoped
public class MbPesquisaAvaliacaoAluno {

	@EJB
	AvaliacaoBeanDao daoAvaliacao;

	private StatusAvaliacao statusAvaliacao = new StatusAvaliacao();

	private Disciplina disciplina = new Disciplina();

	private List<Avaliacao> avaliacoes;

	private Avaliacao avaliacao;

	private String caminhoOrigem;

	@PostConstruct
	public void init() {
		setAvaliacao(new Avaliacao());
		// Carrega as avaliações para o aluno logado
		try {
			setAvaliacoes(daoAvaliacao.listarAvaliacoesAluno(MbLoginController.getUsuarioLogado(), getStatusAvaliacao(),
					getDisciplina()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_ERROR, "Selecione uma disciplina para prosseguir!", e.getMessage()));
			return;
		}
	}

	/**
	 * Método que aplica filtro nas avaliações de acordo disciplina e status de
	 * avaliação selecionados;
	 */
	public void aplicaFiltroAvaliacao() {
		try {
			setAvaliacoes(daoAvaliacao.listarAvaliacoesAluno(MbLoginController.getUsuarioLogado(), getStatusAvaliacao(),
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

	public List<Avaliacao> getAvaliacoes() throws Exception {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
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

	public StatusAvaliacao getStatusAvaliacao() {
		return statusAvaliacao;
	}

	public void setStatusAvaliacao(StatusAvaliacao statusAvaliacao) {
		this.statusAvaliacao = statusAvaliacao;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

}
