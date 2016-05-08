package com.tc.controller;

import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static com.tc.util.IavaliarGlobal.STATUS_AVALIACAO_PENDETE;
import static com.tc.util.IntegerUtil.ZERO;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tc.data.AvaliacoesBeanDao;
import com.tc.data.QuestaoBeanDao;
import com.tc.data.RespostasBeanDao;
import com.tc.model.Aluno;
import com.tc.model.Avaliacoes;
import com.tc.model.Respostas;

@ManagedBean
@SessionScoped
public class MbResponderAvaliacao {

	@EJB
	AvaliacoesBeanDao daoAvaliacoes;
	@EJB
	QuestaoBeanDao daoQuestao;
	@EJB
	RespostasBeanDao daoRespostas;

	private Avaliacoes avaliacoes;

	private String caminhoOrigem;

	private int indiceQuestao;

	private int indiceQuestaoAnterior;

	private boolean confirmouInicio;

	private List<Respostas> respostas;

	@PostConstruct
	public void init() {
		setAvaliacoes(new Avaliacoes());
		setIndiceQuestao(0);
		setIndiceQuestaoAnterior(0);
	}

	/**
	 * Método que carrega para as respostas para a lista
	 */
	public void inicializaListaDeRespostas() {
		if (getAvaliacoes() != null && getAvaliacoes().getId() != null) {
			try {
				setRespostas(daoRespostas.buscaRespostasParaAvalaicaoAluno((Aluno) getAvaliacoes().getAluno(),
						getAvaliacoes().getAvaliacao()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Método que retora true se houver uma questão para o vetor na posição
	 * informada no parametro
	 * 
	 * @param indice
	 * @return
	 */
	public boolean indicaVisibilidadeBotao(Integer indice) {

		if (getAvaliacoes() != null && getAvaliacoes().getAvaliacao().getQuestoesAvaliacao() != null
				&& getAvaliacoes().getAvaliacao().getQuestoesAvaliacao().size() > ZERO
				&& indice < getAvaliacoes().getAvaliacao().getQuestoesAvaliacao().size()) {
			if (getAvaliacoes().getAvaliacao().getQuestoesAvaliacao().get(indice).getQuestao().getIdQuestao() != null)
				return true;
		}

		return false;
	}

	/**
	 * Método atualiza indice de questões da avaliação
	 */
	public void retarnaQuestao() {
		// Atualiza indice
		try {
			setIndiceQuestao(getIndiceQuestao() - 1);
			if (getIndiceQuestao() < ZERO) {
				setIndiceQuestao(ZERO);
				throw new Exception("Limite mínimo atingido.");
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_INFO, "Erro ao localizar questão anterior.", e.getMessage()));
		}

	}

	/**
	 * Método atualiza indice de questões da avaliação
	 */
	public void avanvaQuestao() {
		try {
			setIndiceQuestao(getIndiceQuestao() + 1);
			if (getIndiceQuestao() > getAvaliacoes().getAvaliacao().getQuestoesAvaliacao().size() - 1) {
				setIndiceQuestao(getAvaliacoes().getAvaliacao().getQuestoesAvaliacao().size() - 1);
				throw new Exception("Limite máximo atingido.");
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_INFO, "Erro ao localizar a próxima questão!", e.getMessage()));
		}

	}

	public String concluirAvaliacao() {
		try {
			getAvaliacoes().setAvalicaoRespondida(true);
			getAvaliacoes().setDtaRespondida(new Date());
			getAvaliacoes().setStatusAvaliacao(STATUS_AVALIACAO_PENDETE);
			getAvaliacoes().getAvaliacao().setRespostas(getRespostas());
			
			// Salva a avaliação
			daoAvaliacoes.update(getAvaliacoes());
			
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_INFO, "Erro ao finalizar a avalição.", e.getMessage()));
		}

		//Cria novas isntancias para próxima avaliação;
		setAvaliacoes(new Avaliacoes());
		setIndiceQuestao(0);
		setIndiceQuestaoAnterior(0);

		return goBack();
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

	/**
	 * Método que recebe uma string e compara se esta é um tipo de questão e
	 * retorna se este tipo é igual ao informado na questão
	 * 
	 * @param tipoQuestao
	 */
	public boolean qualTipoQuestao(String tipoQuestao) {
		if (!isBlank(tipoQuestao) && getAvaliacoes().getAvaliacao().getQuestoesAvaliacao().get(getIndiceQuestao())
				.getQuestao().getTipoQuestao().equals(tipoQuestao)) {
			return true;
		}
		return false;
	}

	public Avaliacoes getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(Avaliacoes avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public String getCaminhoOrigem() {
		return caminhoOrigem;
	}

	public void setCaminhoOrigem(String caminhoOrigem) {
		this.caminhoOrigem = caminhoOrigem;
	}

	public int getIndiceQuestao() {
		return indiceQuestao;
	}

	public void setIndiceQuestao(int indiceQuestao) {
		this.indiceQuestao = indiceQuestao;
	}

	public int getIndiceQuestaoAnterior() {
		return indiceQuestaoAnterior;
	}

	public void setIndiceQuestaoAnterior(int indiceQuestaoAnterior) {
		this.indiceQuestaoAnterior = indiceQuestaoAnterior;
	}

	public boolean isConfirmouInicio() {
		return confirmouInicio;
	}

	public void setConfirmouInicio(boolean confirmouInicio) {
		this.confirmouInicio = confirmouInicio;
	}

	public List<Respostas> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Respostas> respostas) {
		this.respostas = respostas;
	}

}
