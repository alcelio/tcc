package com.tc.controller;

import static com.tc.util.IavaliarGlobal.PAGINA_CORRECAO_AVALIACAO;
import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static com.tc.util.IavaliarGlobal.STATUS_AVALIACAO_2_PENDETE;
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
import com.tc.model.Turma;

@ManagedBean
@SessionScoped
public class MbPesquisaAvaliacaoProfessor {

	@EJB
	AvaliacaoBeanDao daoAvaliacao;

	private StatusAvaliacao statusAvaliacao = new StatusAvaliacao();

	private Disciplina disciplina;

	private List<Avaliacao> avaliacoes;

	private Avaliacao avaliacao;

	private Turma turma;

	private String caminhoOrigem;

	@PostConstruct
	public void init() {
		setAvaliacao(new Avaliacao());
		setDisciplina(new Disciplina());
		setTurma(new Turma());
		// Carrega as avaliações para o aluno logado
		try {
			setAvaliacoes(daoAvaliacao.listarAvaliacoesProfessor(MbLoginController.getUsuarioLogado(),
					getStatusAvaliacao(), getDisciplina(), getTurma()));
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
			setAvaliacoes(daoAvaliacao.listarAvaliacoesProfessor(MbLoginController.getUsuarioLogado(),
					getStatusAvaliacao(), getDisciplina(), getTurma()));
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
		if (status != null && STATUS_AVALIACAO_2_PENDETE.equals(status))
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

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

}
