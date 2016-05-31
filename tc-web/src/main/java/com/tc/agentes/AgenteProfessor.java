package com.tc.agentes;

import static com.tc.agentes.PlataformaAgentes.getMapInformacoes;
import static com.tc.agentes.PlataformaAgentes.getMapMensagensUsuarios;
import static com.tc.util.IavaliarGlobal.PREFIXO_AGENTE_PROFESSOR;

import javax.ejb.EJB;

import com.tc.beans.BeanMensagem;
import com.tc.beans.BeanSolicitacao;
import com.tc.data.RespostasBeanDao;
import com.tc.model.Questao;
import com.tc.model.QuestaoDissertativa;
import com.tc.model.QuestaoObjetiva;
import com.tc.model.QuestaoOrdenar;
import com.tc.model.QuestaoRelacionar;
import com.tc.model.QuestaoVF;
import com.tc.model.Respostas;
import com.tc.util.IavaliarGlobal;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

/**
 * Classe que implementa os comportamentos para o agente Professsor
 * 
 * @author Alcélio Gomes
 *
 */
public class AgenteProfessor extends Agent {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private RespostasBeanDao daoRespostas;

	@Override
	protected void setup() {
		System.out.println(PREFIXO_AGENTE_PROFESSOR + " inicializado...");
		// Descrição do servico
		ServiceDescription servico = new ServiceDescription();
		// Seu serviço é apagar fogo
		servico.setType(IavaliarGlobal.SERVICO_CORRIGIR_QUESTAO);

		servico.setName(this.getLocalName());

		registraServico(servico);

		recebeMensagens();

	}

	// Método para registar serviço
	public void registraServico(ServiceDescription sd) {

		DFAgentDescription dfd = new DFAgentDescription();

		dfd.addServices(sd);

		try {

			DFService.register(this, dfd);

		} catch (Exception e) {
			System.out.println("Erro ao registrar serviço.");
			e.printStackTrace();
		}
	}

	// Metodo para adicionar um comportamento para receber mensagens
	protected void recebeMensagens() {

		addBehaviour(new CyclicBehaviour(this) {
			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				ACLMessage msg = receive();

				if (msg != null) {

					BeanSolicitacao bean;
					try {
						bean = (BeanSolicitacao) msg.getContentObject();

						if (bean.getSolicitacao().equals(IavaliarGlobal.SOLICITACAO_CORRECAO)) {
							executaCorrecaoQuestao(bean);
							ACLMessage reply = msg.createReply();
							reply.setContent("Inicializando correção de questão");
							myAgent.send(reply);

						}
					} catch (UnreadableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					block();
				}
			}
		});
	}
	
	/**
	 * Método que executa a correção de uma questão
	 * @param bean
	 */
	private void executaCorrecaoQuestao(BeanSolicitacao bean) {
		System.out.println("SMA - Iniciando a correção da questao [" + bean.getIndiceQuestao() + "]");


		Respostas resp = corrigeQuestao(bean.getRespostas());
		getMapInformacoes().put(bean.getLoginUsuario(), resp);

		//Somente adiciona a mensage quando o aluno errou e não é uma questão dissertativa
		if (!resp.isRespondeuCorretamente() && !(resp.getQuestao() instanceof QuestaoDissertativa)) {
			BeanMensagem mensagem = new BeanMensagem();
			mensagem.setMensagem("Dica para questão [" + bean.getIndiceQuestao() + "]");
			mensagem.setDetalhe(bean.getRespostas().getQuestao().getRecomendacaoErro());
			mensagem.setIndiceQuestao(bean.getIndiceQuestao() -1 );
			mensagem.setQuestaoCorreta(resp.isRespondeuCorretamente());
			getMapMensagensUsuarios().put(bean.getRespostas().getAluno().getLogin(), mensagem);
		}
		System.out.println("SMA - Encerrdada correção da questão [" + bean.getIndiceQuestao() + "]");
		PlataformaAgentes.getMapEmCorrecao().remove(bean.getLoginUsuario());
	}
	
	
	/**
	 * Método para aplicar a correção da questão
	 * @param resposta
	 * @return
	 */
	private Respostas corrigeQuestao(Respostas resposta) {
		Questao questao = resposta.getQuestao();
		
		// Corrige questao objetiva
		if (questao instanceof QuestaoObjetiva) {
			QuestaoObjetiva q = (QuestaoObjetiva) questao;
			try {
				//q = (QuestaoObjetiva) daoQuestao.buscaPorId(questao.getIdQuestao());
				if (q.isRespObjOpcaoA() == resposta.isRespAlunoOpcaoA()
						&& q.isRespObjOpcaoB() == resposta.isRespAlunoOpcaoB()
						&& q.isRespObjOpcaoC() == resposta.isRespAlunoOpcaoC()
						&& q.isRespObjOpcaoD() == resposta.isRespAlunoOpcaoD()
						&& q.isRespObjOpcaoE() == resposta.isRespAlunoOpcaoE()) {

					resposta.setRespondeuCorretamente(true);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// Corrige questao V&F
		if (questao instanceof QuestaoVF) {
			try {
				QuestaoVF q = (QuestaoVF) questao;
				if (q.isRespVFopcaoA() == resposta.isRespAlunoOpcaoA()
						&& q.isRespVFopcaoB() == resposta.isRespAlunoOpcaoB()
						&& q.isRespVFopcaoC() == resposta.isRespAlunoOpcaoC()
						&& q.isRespVFopcaoD() == resposta.isRespAlunoOpcaoD()
						&& q.isRespVFopcaoE() == resposta.isRespAlunoOpcaoE()) {

					resposta.setRespondeuCorretamente(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// Corrige questao ordenar
		if (questao instanceof QuestaoOrdenar) {
			try {
				QuestaoOrdenar q = (QuestaoOrdenar) questao;
				if (q.getRespOrPrimeira() == resposta.getRespOrdemAlunoA()
						&& q.getRespOrSegunda() == resposta.getRespOrdemAlunoB()
						&& q.getRespOrTerceira() == resposta.getRespOrdemAlunoC()
						&& q.getRespOrQuarta() == resposta.getRespOrdemAlunoD()
						&& q.getRespOrQuinta() == resposta.getRespOrdemAlunoE()) {

					resposta.setRespondeuCorretamente(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// Corrige questao ordenar
		if (questao instanceof QuestaoOrdenar) {
			try {
				QuestaoOrdenar q = (QuestaoOrdenar) questao;
				if (q.getRespOrPrimeira() == resposta.getRespOrdemAlunoA()
						&& q.getRespOrSegunda() == resposta.getRespOrdemAlunoB()
						&& q.getRespOrTerceira() == resposta.getRespOrdemAlunoC()
						&& q.getRespOrQuarta() == resposta.getRespOrdemAlunoD()
						&& q.getRespOrQuinta() == resposta.getRespOrdemAlunoE()) {

					resposta.setRespondeuCorretamente(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// Corrige questao relacionar
		if (questao instanceof QuestaoRelacionar) {
			try {
				QuestaoRelacionar q = (QuestaoRelacionar) questao;
				if (q.getRespRelOpcaoA().equals(resposta.getRespAlunoRelOpcaoA())
						&& q.getRespRelOpcaoB().equals(resposta.getRespAlunoRelOpcaoB())
						&& q.getRespRelOpcaoC().equals(resposta.getRespAlunoRelOpcaoC())
						&& q.getRespRelOpcaoD().equals(resposta.getRespAlunoRelOpcaoD())
						&& q.getRespRelOpcaoE().equals(resposta.getRespAlunoRelOpcaoE())) {

					resposta.setRespondeuCorretamente(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		resposta.setCorrigidaAgente(true);
		return resposta;
	}

}
