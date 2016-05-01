package com.tc.controller;

import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static com.tc.util.IavaliarGlobal.PAGINA_INCLUI_QUESTAO_DISSERTATIVA;
import static com.tc.util.IavaliarGlobal.PAGINA_INCLUI_QUESTAO_OBJETIVA;
import static com.tc.util.IavaliarGlobal.PAGINA_INCLUI_QUESTAO_ORDENAR;
import static com.tc.util.IavaliarGlobal.PAGINA_INCLUI_QUESTAO_RELACIONAR;
import static com.tc.util.IavaliarGlobal.PAGINA_INCLUI_QUESTAO_VF;

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
import com.tc.model.QuestaoDissertativa;
import com.tc.model.QuestaoObjetiva;
import com.tc.model.QuestaoOrdenar;
import com.tc.model.QuestaoRelacionar;
import com.tc.model.QuestaoVF;
import com.tc.model.TopicoEstudo;
import com.tc.suport.IavaliarService;

@ManagedBean(name = "dtFilterView")
@SessionScoped
public class MbPesquisaQuestao {
	@EJB
	QuestaoBeanDao dao;
	@EJB
	TopicoEstudoBeanDao daoTopicos;
	@EJB
	DisciplinaBeanDao daoDisciplina;

	@ManagedProperty("#{iavaliarService}")
	private IavaliarService service;

	private List<Questao> questoes;
	private List<Disciplina> disciplinas;
	private List<TopicoEstudo> topicosEstudo;
	private List<Questao> filtroQuestao;
	private List<String> pergunta;
	private Questao questao;
	private String caminhoOrigem;
	private Disciplina disciplina;
	
	private QuestaoDissertativa dissertativa;
	private QuestaoOrdenar ordenar;
	private QuestaoRelacionar relacionar;
	private QuestaoVF vf;
	private QuestaoObjetiva objetiva;

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
	
	public String abrePaginaQuestao(){
		
		if(getQuestao() instanceof QuestaoObjetiva){
			setObjetiva((QuestaoObjetiva) getQuestao());
			return PAGINA_INCLUI_QUESTAO_OBJETIVA;
		}
		
		if(getQuestao() instanceof QuestaoDissertativa){
			setDissertativa((QuestaoDissertativa) getQuestao());
			return PAGINA_INCLUI_QUESTAO_DISSERTATIVA;
		}
		
		if(getQuestao() instanceof QuestaoRelacionar){
			setRelacionar((QuestaoRelacionar) getQuestao());
			return PAGINA_INCLUI_QUESTAO_RELACIONAR;
		}
		
		if(getQuestao() instanceof QuestaoOrdenar){	
			setOrdenar((QuestaoOrdenar) getQuestao());
			return PAGINA_INCLUI_QUESTAO_ORDENAR;
		}
		
		if(getQuestao() instanceof QuestaoVF){
			setVf((QuestaoVF) getQuestao());
			return PAGINA_INCLUI_QUESTAO_VF;
		}
		return null;
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

	public void setService(IavaliarService service) {
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

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public QuestaoDissertativa getDissertativa() {
		return dissertativa;
	}

	public void setDissertativa(QuestaoDissertativa dissertativa) {
		this.dissertativa = dissertativa;
	}

	public QuestaoOrdenar getOrdenar() {
		return ordenar;
	}

	public void setOrdenar(QuestaoOrdenar ordenar) {
		this.ordenar = ordenar;
	}

	public QuestaoRelacionar getRelacionar() {
		return relacionar;
	}

	public void setRelacionar(QuestaoRelacionar relacionar) {
		this.relacionar = relacionar;
	}

	public QuestaoVF getVf() {
		return vf;
	}

	public void setVf(QuestaoVF vf) {
		this.vf = vf;
	}

	public QuestaoObjetiva getObjetiva() {
		return objetiva;
	}

	public void setObjetiva(QuestaoObjetiva objetiva) {
		this.objetiva = objetiva;
	}
	

}
