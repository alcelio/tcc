package com.tc.controller;

import static com.tc.controller.MbLoginController.getUsuarioLogado;
import static com.tc.util.IavaliarGlobal.PAGINA_AVALIACOES_PROFESSOR_A_CORRIGIR;
import static com.tc.util.IavaliarGlobal.PAGINA_CORRECAO_AVALIACAO;
import static com.tc.util.IavaliarGlobal.PAGINA_ESTATISTICAS_PROFESSOR;
import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static com.tc.util.IavaliarGlobal.STATUS_AVALIACAO_PENDETE_CORRECAO;
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

import com.tc.beans.BeanEstatiticasAluno;
import com.tc.beans.BeanEstatiticasTurma;
import com.tc.data.AlunosTurmaBeanDao;
import com.tc.data.AvaliacaoBeanDao;
import com.tc.data.AvaliacoesBeanDao;
import com.tc.data.RespostasBeanDao;
import com.tc.model.Avaliacao;
import com.tc.model.Avaliacoes;
import com.tc.model.Disciplina;
import com.tc.model.Respostas;
import com.tc.model.Turma;
import com.tc.suport.IavaliarService;
import com.tc.util.IavaliarGlobal;

@ManagedBean(name = "mbAvaliacoesProfessor")
@SessionScoped
public class MbAvaliacoesProfessor {

	@EJB
	AvaliacoesBeanDao daoAvaliacoes;
	@EJB
	AvaliacaoBeanDao daoAvaliacao;
	@EJB
	AlunosTurmaBeanDao daoAlunosTurma;
	@EJB
	RespostasBeanDao daoRespostas;

	private Avaliacao avaliacao;

	private Avaliacoes avaliacoes;

	private String status = "";

	private Disciplina disciplina;

	private List<Avaliacao> listaAvaliacao;

	private List<Avaliacoes> avaliacoesPendenteCorrecao;

	private String caminhoOrigem;

	private Turma turma;

	private BeanEstatiticasAluno estatisticasAluno;

	private BeanEstatiticasTurma estatisticasTurma;

	private List<Respostas> respostasDissertativas;

	@ManagedProperty("#{iavaliarService}")
	private IavaliarService service;

	@PostConstruct
	public void init() {
		setDisciplina(new Disciplina());
		setTurma(new Turma());
		// Carrega as avaliações para o aluno logado
		try {
			// Carrega todas as listaAvaliacao do professor
			setListaAvaliacao(daoAvaliacao.listarAvaliacoesConcluidas(getUsuarioLogado(), getDisciplina(), getTurma()));
			// Carrega toas as listaAvaliacao com o estatus pendente de correcao
			setAvaliacoesPendenteCorrecao(daoAvaliacoes.listarAvaliacoesProfessor(getUsuarioLogado(),
					STATUS_AVALIACAO_PENDETE_CORRECAO, getDisciplina(), getTurma()));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_ERROR, "Erro ao aplicar filtros!", e.getMessage()));
			return;
		}
	}

	/**
	 * Grava as respostas editadas no bd
	 * 
	 * @return
	 */
	public String gravaCorrecoesDissertativas() {
		
		for (Respostas resp : getRespostasDissertativas()) {
			daoRespostas.update(resp);
		}
		
		
		try {
			getAvaliacao().setConcluida(true);
			daoAvaliacao.update(getAvaliacao());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goBack();
	}

	/**
	 * Método para gerar os dados estatísticos para a avaliação por turma;
	 * 
	 * @throws Exception
	 */
	public String geraEstatisticasAvaliacao() throws Exception {
		BeanEstatiticasTurma e = new BeanEstatiticasTurma();

		e.setQtAlunos(daoAlunosTurma.listarAlunosPorTurma(getTurma()).size());

		setEstatisticasTurma(e);
		
		return PAGINA_ESTATISTICAS_PROFESSOR;
	}

	/**
	 * Método que aplica filtro nas avaliações de acordo disciplina e status de
	 * avaliação selecionados;
	 */
	public void aplicaFiltroAvaliacaoPendentes() {
		try {
			setAvaliacoesPendenteCorrecao(
					daoAvaliacoes.listarAvaliacoesProfessor(getUsuarioLogado(), null, getDisciplina(), getTurma()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_ERROR, "Erro ao aplicar filtros!", e.getMessage()));
		}
	}

	/**
	 * Método que aplica filtro nas avaliações de acordo disciplina e status de
	 * avaliação selecionados;
	 */
	public void aplicaFiltroAvaliacao() {
		try {
			setListaAvaliacao(daoAvaliacao.listarAvaliacoesConcluidas(getUsuarioLogado(), getDisciplina(), getTurma()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_ERROR, "Erro ao aplicar filtros!", e.getMessage()));
		}
	}

	/**
	 * Retorna veradeiro se o status da avaliação estiver como pendente
	 * 
	 * @return boolean
	 */
	public boolean corrigir(String status) {
		if (status != null && STATUS_AVALIACAO_PENDETE_CORRECAO.equals(status))
			return true;
		return false;
	}

	public String corrigirAvaliacao() {
		try {
			setRespostasDissertativas(daoRespostas.buscarDissertativas(getAvaliacoes().getAluno(), getAvaliacao()));

		} catch (Exception e) {
			e.printStackTrace();
		}

		setaCaminhoOrigem(PAGINA_AVALIACOES_PROFESSOR_A_CORRIGIR);

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

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Avaliacoes getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(Avaliacoes avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public List<Avaliacao> getListaAvaliacao() {
		return listaAvaliacao;
	}

	public void setListaAvaliacao(List<Avaliacao> listaAvaliacao) {
		this.listaAvaliacao = listaAvaliacao;
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

	public List<Avaliacoes> getAvaliacoesPendenteCorrecao() {
		return avaliacoesPendenteCorrecao;
	}

	public void setAvaliacoesPendenteCorrecao(List<Avaliacoes> avaliacoesPendenteCorrecao) {
		this.avaliacoesPendenteCorrecao = avaliacoesPendenteCorrecao;
	}

	public BeanEstatiticasAluno getEstatisticasAluno() {
		return estatisticasAluno;
	}

	public void setEstatisticasAluno(BeanEstatiticasAluno estatisticasAluno) {
		this.estatisticasAluno = estatisticasAluno;
	}

	public BeanEstatiticasTurma getEstatisticasTurma() {
		return estatisticasTurma;
	}

	public void setEstatisticasTurma(BeanEstatiticasTurma estatisticasTurma) {
		this.estatisticasTurma = estatisticasTurma;
	}

	public List<Respostas> getRespostasDissertativas() {
		return respostasDissertativas;
	}

	public void setRespostasDissertativas(List<Respostas> respostasDissertativas) {
		this.respostasDissertativas = respostasDissertativas;
	}
}
