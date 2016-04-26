package com.tc.controller;

import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
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
import com.tc.model.Avaliacao;
import com.tc.model.QuestaoDissertativa;
import com.tc.model.QuestaoObjetiva;
import com.tc.model.QuestaoOrdenar;
import com.tc.model.QuestaoRelacionar;
import com.tc.model.QuestaoVF;
import com.tc.model.QuestoesAvaliacao;

@ManagedBean
@SessionScoped
public class MbResponderAvaliacao {

	@EJB
	AvaliacaoBeanDao daoAvaliacao;
	@EJB
	QuestaoBeanDao daoQuestao;

	private Avaliacao avaliacao;

	private String caminhoOrigem;

	private QuestaoDissertativa questaoDissertativa;

	private QuestaoObjetiva questaoObjetiva;

	private QuestaoOrdenar questaoOrdenar;

	private QuestaoRelacionar questaoRelacionar;

	private QuestaoVF questaoVF;

	private int indiceQuestao;

	private String tipoQuestao;
	
	private boolean confirmouInicio;

	@PostConstruct
	public void init() {
		setAvaliacao(new Avaliacao());
		setIndiceQuestao(0);
		setQuestaoDissertativa(new QuestaoDissertativa());
		setQuestaoObjetiva(new QuestaoObjetiva());
		setQuestaoOrdenar(new QuestaoOrdenar());
		setQuestaoVF(new QuestaoVF());
		setQuestaoRelacionar(new QuestaoRelacionar());
	}
	
	/**
	 * Método que retora true se houver uma questão para o vetor na posição informada no parametro
	 * 
	 * @param indice
	 * @return
	 */
	public boolean indicaVisibilidadeBotao(Integer indice){
		
		if(getAvaliacao() != null && getAvaliacao().getQuestoesAvaliacao() != null && getAvaliacao().getQuestoesAvaliacao().size() > ZERO  && indice < getAvaliacao().getQuestoesAvaliacao().size()){
			if(getAvaliacao().getQuestoesAvaliacao().get(indice).getQuestao().getIdQuestao() != null)
				return true;
		}
		
		return false;
	}

	/**
	 * Método que posiciona na questão anterior da lista de questões
	 */
	public void retarnaQuestao() {
		try {
			setIndiceQuestao(getIndiceQuestao() - 1);
			if (getIndiceQuestao() < ZERO)
				setIndiceQuestao(ZERO);
			posicionaQuestao();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_INFO, "Erro ao localizar questão anterior!", ""));
		}
	}

	/**
	 * Método que posiciona na próxima questão da lista de questões
	 */
	public void avanvaQuestao() {
		try {
			setIndiceQuestao(getIndiceQuestao() + 1);
			if (getIndiceQuestao() > getAvaliacao().getQuestoesAvaliacao().size())
				setIndiceQuestao(getAvaliacao().getQuestoesAvaliacao().size());
			posicionaQuestao();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_INFO, "Erro ao localizar a próxima questão!", ""));
		}
	}

	/**
	 * Método que posiciona a questão conforme o indice indicado na vavirável
	 * indiceQuestao
	 * 
	 * @throws Exception
	 */
	public void posicionaQuestao() {
		QuestoesAvaliacao questaoAvaliacao = getAvaliacao().getQuestoesAvaliacao().get(getIndiceQuestao());
		if (questaoAvaliacao == null)
			return;

		// Seta retorna quando for uma questão Vf
		if (questaoAvaliacao.getQuestao() != null && questaoAvaliacao.getQuestao() instanceof QuestaoVF) {
			setQuestaoVF((QuestaoVF) questaoAvaliacao.getQuestao());
			setTipoQuestao(getQuestaoVF().getTipoQuestao());
		}
		// Seta retorna quando for uma questão Ordenar
		if (questaoAvaliacao.getQuestao() != null && questaoAvaliacao.getQuestao() instanceof QuestaoOrdenar) {
			setQuestaoOrdenar((QuestaoOrdenar) questaoAvaliacao.getQuestao());
			setTipoQuestao(getQuestaoOrdenar().getTipoQuestao());
		}
		// Seta retorna quando for uma questão Relacionar
		if (questaoAvaliacao.getQuestao() != null && questaoAvaliacao.getQuestao() instanceof QuestaoRelacionar) {
			setQuestaoRelacionar((QuestaoRelacionar) questaoAvaliacao.getQuestao());
			setTipoQuestao(getQuestaoRelacionar().getTipoQuestao());
		}
		// Seta retorna quando for uma questão Objetiva
		if (questaoAvaliacao.getQuestao() != null && questaoAvaliacao.getQuestao() instanceof QuestaoObjetiva) {
			setQuestaoObjetiva((QuestaoObjetiva) questaoAvaliacao.getQuestao());
			setTipoQuestao(getQuestaoObjetiva().getTipoQuestao());
		}
		// Seta retorna quando for uma questão Dissertativa
		if (questaoAvaliacao.getQuestao() != null && questaoAvaliacao.getQuestao() instanceof QuestaoDissertativa) {
			setQuestaoDissertativa((QuestaoDissertativa) questaoAvaliacao.getQuestao());
			setTipoQuestao(getQuestaoDissertativa().getTipoQuestao());
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
		if (!isBlank(getTipoQuestao()) && !isBlank(tipoQuestao)  &&  getTipoQuestao().equals(tipoQuestao)) {
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

	public QuestaoDissertativa getQuestaoDissertativa() {
		return questaoDissertativa;
	}

	public void setQuestaoDissertativa(QuestaoDissertativa questaoDissertativa) {
		this.questaoDissertativa = questaoDissertativa;
	}

	public QuestaoObjetiva getQuestaoObjetiva() {
		return questaoObjetiva;
	}

	public void setQuestaoObjetiva(QuestaoObjetiva questaoObjetiva) {
		this.questaoObjetiva = questaoObjetiva;
	}

	public QuestaoOrdenar getQuestaoOrdenar() {
		return questaoOrdenar;
	}

	public void setQuestaoOrdenar(QuestaoOrdenar questaoOrdenar) {
		this.questaoOrdenar = questaoOrdenar;
	}

	public QuestaoRelacionar getQuestaoRelacionar() {
		return questaoRelacionar;
	}

	public void setQuestaoRelacionar(QuestaoRelacionar questaoRelacionar) {
		this.questaoRelacionar = questaoRelacionar;
	}

	public QuestaoVF getQuestaoVF() {
		return questaoVF;
	}

	public void setQuestaoVF(QuestaoVF questaoVF) {
		this.questaoVF = questaoVF;
	}

	private int getIndiceQuestao() {
		return indiceQuestao;
	}

	private void setIndiceQuestao(int indiceQuestao) {
		this.indiceQuestao = indiceQuestao;
	}

	public String getTipoQuestao() {
		return tipoQuestao;
	}

	public void setTipoQuestao(String tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}

	public boolean isConfirmouInicio() {
		return confirmouInicio;
	}

	public void setConfirmouInicio(boolean confirmouInicio) {
		this.confirmouInicio = confirmouInicio;
	}
	

}
