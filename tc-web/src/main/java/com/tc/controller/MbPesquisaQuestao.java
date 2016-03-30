package com.tc.controller;

import static com.tc.util.IavaliarGlobal.PAGINA_HOME;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;

import com.tc.data.DisciplinaBeanDao;
import com.tc.data.QuestaoBeanDao;
import com.tc.data.TopicoEstudoBeanDao;
import com.tc.model.Disciplina;
import com.tc.model.Questao;
import com.tc.model.TopicoEstudo;
import com.tc.suport.QuestaoService;

@ManagedBean(name = "dtFilterView")
@SessionScoped
public class MbPesquisaQuestao {
	@EJB
	QuestaoBeanDao dao;
	@EJB
	TopicoEstudoBeanDao daoTopicos;
	@EJB
	DisciplinaBeanDao daoDisciplina;

	@ManagedProperty("#{questaoService}")
	private QuestaoService service;

	private List<Questao> questoes;
	private List<Disciplina> disciplinas;
	private List<TopicoEstudo> topicosEstudo;
	private List<Questao> filtroQuestao;
	private List<String> pergunta;
	private Questao questao;
	private String caminhoOrigem;

	@PostConstruct
	public void init() {
		questoes = dao.listarQuestoes();
		topicosEstudo = daoTopicos.listarTopicoEstudo();
		disciplinas = daoDisciplina.listarDisciplina();

	}

	public void setaCaminhoOrigem(String origem) {
		this.caminhoOrigem = origem;
	}

	public String goBack() {
		if (StringUtils.isBlank(this.caminhoOrigem)) {
			return PAGINA_HOME;
		}
		return this.caminhoOrigem;
	}

	public List<String> getTipoQuestao() {
		return service.getTipoQuestao();
	}

	public List<String> getGrauDificuldade() {
		return service.getGrauDificuldade();
	}

	public List<Questao> getQuestoes() {
		questoes = dao.listarQuestoes();
		return questoes;
	}

	public List<Questao> getFiltroQuestao() {
		return filtroQuestao;
	}

	public void setFiltroQuestao(List<Questao> filtroQuestao) {
		this.filtroQuestao = filtroQuestao;
	}

	public void setService(QuestaoService service) {
		this.service = service;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public List<TopicoEstudo> getTopicosEstudo() {
		return topicosEstudo;
	}

	public List<String> getPergunta() {
		return pergunta;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

}
