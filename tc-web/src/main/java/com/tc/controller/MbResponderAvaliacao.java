package com.tc.controller;

import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static com.tc.util.IavaliarGlobal.STATUS_AVALIACAO_4_RESPONDIDA;
import static com.tc.util.IntegerUtil.ZERO;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static org.apache.commons.lang3.StringUtils.isBlank;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tc.data.AvaliacaoBeanDao;
import com.tc.data.QuestaoBeanDao;
import com.tc.exceptions.ScreenException;
import com.tc.model.Avaliacao;

@ManagedBean
@SessionScoped
public class MbResponderAvaliacao {

	@EJB
	AvaliacaoBeanDao daoAvaliacao;
	@EJB
	QuestaoBeanDao daoQuestao;

	private Avaliacao avaliacao;

	private String caminhoOrigem;

	private int indiceQuestao;
	
	private int indiceQuestaoAnterior;

	private boolean confirmouInicio;

	@PostConstruct
	public void init() {
		setAvaliacao(new Avaliacao());
		setIndiceQuestao(0);
		setIndiceQuestaoAnterior(0);
	}
	

	/**
	 * Método que retora true se houver uma questão para o vetor na posição
	 * informada no parametro
	 * 
	 * @param indice
	 * @return
	 */
	public boolean indicaVisibilidadeBotao(Integer indice) {

		if (getAvaliacao() != null && getAvaliacao().getQuestoesAvaliacao() != null
				&& getAvaliacao().getQuestoesAvaliacao().size() > ZERO
				&& indice < getAvaliacao().getQuestoesAvaliacao().size()) {
			if (getAvaliacao().getQuestoesAvaliacao().get(indice).getQuestao().getIdQuestao() != null)
				return true;
		}

		return false;
	}

	/**
	 * Método atualiza indice de questões da avaliação
	 */
	public void retarnaQuestao() {
		//Atualiza indice
		try {
			setIndiceQuestaoAnterior(getIndiceQuestao());
			setIndiceQuestao(getIndiceQuestao() - 1);
			atualizaRespostas();
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
			setIndiceQuestaoAnterior(getIndiceQuestao());
			setIndiceQuestao(getIndiceQuestao() + 1);
			atualizaRespostas();
			if (getIndiceQuestao() > getAvaliacao().getQuestoesAvaliacao().size()-1) {
				setIndiceQuestao(getAvaliacao().getQuestoesAvaliacao().size()-1);
				throw new Exception("Limite máximo atingido.");
			}
		
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_INFO, "Erro ao localizar a próxima questão!", e.getMessage()));
		}
		
	}
	
	public String concluirAvaliacao(){
		setAvaliacao(new Avaliacao());
		setIndiceQuestao(0);
		setIndiceQuestaoAnterior(0);
		
//		getAvaliacao().setDataRespondida(new Date());
		getAvaliacao().setRespondida(true);
		getAvaliacao().setStatusAvaliacao(STATUS_AVALIACAO_4_RESPONDIDA);
		//Salava a avaliação
		daoAvaliacao.update(getAvaliacao());
		
		
		return goBack();
	}

	/**
	 * Método que atualiza os dados da avaliação no banco de dados
	 * @throws ScreenException 
	 */
	private void atualizaRespostas() throws ScreenException{
		getAvaliacao().getQuestoesAvaliacao().get(getIndiceQuestaoAnterior()).setRespondida(true);
		daoAvaliacao.update(getAvaliacao());
		if(getAvaliacao() != null && getAvaliacao().getIdAvaliacao() != null){
			try {
				setAvaliacao(daoAvaliacao.buscaPorId(getAvaliacao().getIdAvaliacao()));
			} catch (Exception e) {
				throw new ScreenException("Erro ao recarregar dados atualizados da avaliação.");
			}
		}
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
		if (!isBlank(tipoQuestao) && getAvaliacao().getQuestoesAvaliacao().get(getIndiceQuestao()).getQuestao().getTipoQuestao().equals(tipoQuestao)) {
			return true;
		}
		return false;
	}

	public String getCaminhoOrigem() {
		return caminhoOrigem;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}


	public int getIndiceQuestao() {
		return indiceQuestao;
	}

	public void setIndiceQuestao(int indiceQuestao) {
		this.indiceQuestao = indiceQuestao;
	}


	public boolean isConfirmouInicio() {
		return confirmouInicio;
	}

	public void setConfirmouInicio(boolean confirmouInicio) {
		this.confirmouInicio = confirmouInicio;
	}

	public int getIndiceQuestaoAnterior() {
		return indiceQuestaoAnterior;
	}

	public void setIndiceQuestaoAnterior(int indiceQuestaoAnterior) {
		this.indiceQuestaoAnterior = indiceQuestaoAnterior;
	}

	

}
