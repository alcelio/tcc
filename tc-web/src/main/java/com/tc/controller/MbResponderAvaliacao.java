package com.tc.controller;

import static com.tc.agentes.PlataformaAgentes.getMapEmCorrecao;
import static com.tc.agentes.PlataformaAgentes.getMapInformacoes;
import static com.tc.agentes.PlataformaAgentes.getMapMensagensUsuarios;
import static com.tc.controller.MbLoginController.getUsuarioLogado;
import static com.tc.util.IavaliarGlobal.MENSAGEM_AVISO_CORRIGIR;
import static com.tc.util.IavaliarGlobal.PAGINA_AVALIACOES_PROFESSOR;
import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static com.tc.util.IavaliarGlobal.SOLICITACAO_CORRECAO;
import static com.tc.util.IavaliarGlobal.STATUS_AVALIACAO_PENDETE_CORRECAO;
import static com.tc.util.IavaliarGlobal.TIPO_AVISO_CORRECAO;
import static com.tc.util.IntegerUtil.ZERO;
import static java.lang.Boolean.TRUE;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static javax.faces.context.FacesContext.getCurrentInstance;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tc.agentes.PlataformaAgentes;
import com.tc.beans.BeanMensagem;
import com.tc.beans.BeanSolicitacao;
import com.tc.data.AvaliacoesBeanDao;
import com.tc.data.AvisosBeanDao;
import com.tc.data.HistoricoResponderAvalicaoBeanDao;
import com.tc.data.QuestaoBeanDao;
import com.tc.data.RespostasBeanDao;
import com.tc.model.Aluno;
import com.tc.model.Avaliacoes;
import com.tc.model.Avisos;
import com.tc.model.HistoricoResponderAvaliacao;
import com.tc.model.QuestaoDissertativa;
import com.tc.model.QuestoesAvaliacao;
import com.tc.model.Respostas;

import jade.core.Agent;

@ManagedBean
@SessionScoped
public class MbResponderAvaliacao extends Agent {
	private static final long serialVersionUID = 3343701879628402434L;

	@EJB
	AvaliacoesBeanDao daoAvaliacoes;
	@EJB
	QuestaoBeanDao daoQuestao;
	@EJB
	RespostasBeanDao daoRespostas;
	@EJB
	HistoricoResponderAvalicaoBeanDao daoHistorico;
	@EJB
	AvisosBeanDao daoAvisos;

	private Avaliacoes avaliacoes;

	private String caminhoOrigem;

	private int indiceQuestao;

	private int indiceQuestaoAnterior;

	private boolean confirmouInicio;

	private List<Respostas> respostas;
	
	private long tempoInicial;

	private BeanMensagem msg;

	/**
	 * Método para buscar as informações que o agente professor colocou no map
	 * de informações
	 */
	private void atualizaRespostas() {
		boolean isAguardar = true;
		while (isAguardar) {
			if (getMapInformacoes().containsKey(getUsuarioLogado().getLogin())) {
				isAguardar = false;
			}
		}
		Respostas respostasRecebida = (Respostas) getMapInformacoes().get(getUsuarioLogado().getLogin());

		if (respostasRecebida != null) {
			getRespostas().get(getIndiceQuestao())
					.setRespondeuCorretamente(respostasRecebida.isRespondeuCorretamente());
			getRespostas().get(getIndiceQuestao()).setCorrigidaAgente(respostasRecebida.isCorrigidaAgente());
		}

		getMapInformacoes().remove(getUsuarioLogado().getLogin());
	}

	private void exibeMensagem() {
		// Aguarda agente terminar correção da questão
		boolean isAguardar = true;
		while (isAguardar) {
			if (!getMapEmCorrecao().containsKey(getUsuarioLogado().getLogin())) {
				isAguardar = false;
			}
		}
		BeanMensagem msg = getMapMensagensUsuarios().get(getUsuarioLogado().getLogin());

		if (msg != null) {
			getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_INFO, msg.getMensagem(), msg.getDetalhe()));
			// Remonve correção pendende para o usuário
			PlataformaAgentes.getMapEmCorrecao().remove(getUsuarioLogado().getLogin());
			// Remove as mensagens deposiadas para o usuário
			PlataformaAgentes.getMapMensagensUsuarios().remove(getUsuarioLogado().getLogin());
			// Para atualizar a resposta na base de daos
		}

	}

	@PostConstruct
	public void init() {
		setAvaliacoes(new Avaliacoes());
		setIndiceQuestao(0);
	}


	/**
	 * Método que carrega para as respostas para a lista
	 */
	public void inicializaListaDeRespostas() {
		tempoInicial = System.currentTimeMillis();
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
			geraHistorico();
			solicitaCorrecao();
			atualizaRespostas();
			setIndiceQuestao(getIndiceQuestao() - 1);
			if (getIndiceQuestao() < ZERO) {
				setIndiceQuestao(ZERO);
				throw new Exception("Limite mínimo atingido.");
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_INFO, "Erro ao localizar questão anterior.", e.getMessage()));
		}finally {
			exibeMensagem();
		}

	}

	private void geraHistorico(){
		HistoricoResponderAvaliacao hist = new HistoricoResponderAvaliacao();
		hist.setAluno(getAvaliacoes().getAluno()); 
		hist.setAvaliacao(getAvaliacoes().getAvaliacao()); 
		hist.setQuestao(getAvaliacoes().getAvaliacao().getQuestoesAvaliacao().get(getIndiceQuestao()).getQuestao());
		
		hist.setTempoRespondendo(System.currentTimeMillis() - tempoInicial);
		daoHistorico.create(hist);
		tempoInicial = System.currentTimeMillis();
		
		try {
			long count = daoHistorico.tempoMedioPorQuestao(getAvaliacoes().getAvaliacao().getQuestoesAvaliacao().get(getIndiceQuestao()).getQuestao());
			
			System.out.println("Tempo para resolver questão: "+ count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
				
	}
	
	private void solicitaCorrecao() {
		try {
			getMapEmCorrecao().put(getUsuarioLogado().getLogin(), TRUE);
			BeanSolicitacao bean = new BeanSolicitacao();
			bean.setSolicitacao(SOLICITACAO_CORRECAO);
			bean.setRespostas(getRespostas().get(getIndiceQuestao()));
			bean.setIndiceQuestao(getIndiceQuestao() + 1);
			bean.setLoginUsuario(getUsuarioLogado().getLogin());
			PlataformaAgentes.enviaSolicitacaoServico(bean);

		} catch (Exception e) {
			getMapEmCorrecao().remove(getUsuarioLogado().getLogin());
			e.printStackTrace();
		}
	}
	
	/**
	 * Método atualiza indice de questões da avaliação
	 * 
	 * @throws InterruptedException
	 */
	public void avancaQuestao() throws InterruptedException {
		try {
			geraHistorico();
			solicitaCorrecao();
			atualizaRespostas();
			
			setIndiceQuestao(getIndiceQuestao() + 1);
			if (getIndiceQuestao() > getAvaliacoes().getAvaliacao().getQuestoesAvaliacao().size() - 1) {
				setIndiceQuestao(getAvaliacoes().getAvaliacao().getQuestoesAvaliacao().size() - 1);
				throw new Exception("Limite máximo atingido.");
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_INFO, "Erro ao localizar a próxima questão!", e.getMessage()));
		} finally {
			exibeMensagem();
		}

	}

	public String concluirAvaliacao() {
		try {
			getAvaliacoes().setAvalicaoRespondida(true);
			getAvaliacoes().setDtaRespondida(new Date());
			getAvaliacoes().setStatusAvaliacao(STATUS_AVALIACAO_PENDETE_CORRECAO);
			getAvaliacoes().getAvaliacao().setRespostas(getRespostas());

			// Salva a avaliação
			daoAvaliacoes.update(getAvaliacoes());
			
			//avisaFinalizacaoAvaliacao();
			geraAvisoCorrecao();

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_INFO, "Erro ao finalizar a avalição.", e.getMessage()));
		}

		// Cria novas isntancias para próxima avaliação;
		setAvaliacoes(new Avaliacoes());
		setIndiceQuestao(0);
		setIndiceQuestaoAnterior(0);

		// pararAtualizador();
		return goBack();
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
	
	/**
	 * Método que executa os procedimentos depois que um aluno finaliza de responder a avaliação
	 * @param bean
	 * @throws Exception
	 */
	private void geraAvisoCorrecao() throws Exception{
		List<QuestoesAvaliacao> listaQuestoes = getAvaliacoes().getAvaliacao().getQuestoesAvaliacao();
		
		boolean isQuestaoDissertativa =false;
		if(listaQuestoes != null && listaQuestoes.size() > 0){
			for (QuestoesAvaliacao questoesAvaliacao : listaQuestoes) {
				if(questoesAvaliacao.getQuestao() instanceof QuestaoDissertativa){
					isQuestaoDissertativa = true;
					break;
				}
			}
		}
		
		if(isQuestaoDissertativa){
			if(!daoAvisos.isExisteAviso(getAvaliacoes().getProfessor(), TIPO_AVISO_CORRECAO)){
				Avisos aviso = new Avisos();
				aviso.setDataGeracao(new Date());
				aviso.setDescricao(MENSAGEM_AVISO_CORRIGIR);
				aviso.setLink(PAGINA_AVALIACOES_PROFESSOR);
				aviso.setTipoAviso(TIPO_AVISO_CORRECAO);
				aviso.setUsuario(getAvaliacoes().getProfessor());
				aviso.setAtivo(true);
				daoAvisos.create(aviso);
			}
		}
		

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

	public void setaCaminhoOrigem(String caminhoOrigem) {
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

	public BeanMensagem getMsg() {
		return msg;
	}

	public void setMsg(BeanMensagem msg) {
		this.msg = msg;
	}

	
}
