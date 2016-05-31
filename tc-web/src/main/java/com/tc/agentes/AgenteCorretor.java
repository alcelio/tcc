package com.tc.agentes;

import static com.tc.util.IavaliarGlobal.SOLICITACAO_CORRECAO;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.tc.data.QuestaoBeanDao;
import com.tc.data.QuestoesAvaliacaoBeanDao;
import com.tc.data.RespostasBeanDao;
import com.tc.model.Questao;
import com.tc.model.QuestaoObjetiva;
import com.tc.model.QuestaoOrdenar;
import com.tc.model.QuestaoRelacionar;
import com.tc.model.QuestaoVF;
import com.tc.model.Respostas;

import jade.core.Agent;
import jade.core.Location;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class AgenteCorretor extends Agent {
	private static final long serialVersionUID = 1L;

	@EJB
	RespostasBeanDao daoRespostas;
	@EJB
	QuestaoBeanDao daoQuestao;
	@EJB
	QuestoesAvaliacaoBeanDao daoQuestoesAvaliacao;

	@Override
	protected void setup() {
		System.out.println(" Agente Corretor de questão inicializado...\n Aguardando informações...");
		//Descrição do serviço
		ServiceDescription servico = new ServiceDescription();
		servico.setType(SOLICITACAO_CORRECAO);
		servico.setName(this.getLocalName());
		registraServico(servico);
		
		addBehaviour(new CyclicBehaviour(this) {
			private static final long serialVersionUID = 1L;

			Respostas resposta;

			@Override
			public void action() {
				ACLMessage msg = receive(); // captura a nova mensagem
				if (msg != null) {
					System.out.println("SMA- Recebendo questão para correção");
					try {
						resposta = (Respostas) msg.getContentObject();

						if (resposta.getQuestao() != null) {
							resposta = corrigeQuestao(resposta);
						}

						if (!resposta.isRespondeuCorretamente()) {
							FacesContext.getCurrentInstance().addMessage(null,
									new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dica do professor:",
											resposta.getQuestao().getRecomendacaoErro()));
						}

					} catch (UnreadableException e) {
					}

				} else {
					// aguarda nova mensagem
					block();
				}

			}
		});

	}

	//Método para registar serviço
	protected void registraServico(ServiceDescription sd) {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		} catch (Exception e) {
			System.out.println("Erro ao registrar serviço.");
			e.printStackTrace();
		}
	}
	
	protected void recebeMensagens(final String mensagem, final String resposta){
		addBehaviour(new CyclicBehaviour(this) {
			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				ACLMessage msg = receive();
				if(msg != null){
					if(msg.getContent().equalsIgnoreCase(mensagem)){
						ACLMessage reply = msg.createReply();
						reply.setContent(resposta);
					}
				}
			}
		});
	}

	private Respostas corrigeQuestao(Respostas resp) {

		Questao questao = resp.getQuestao();
		Respostas resposta = resp;
		// Corrige questao objetiva
		if (questao instanceof QuestaoObjetiva) {
			QuestaoObjetiva q;
			try {
				q = (QuestaoObjetiva) daoQuestao.buscaPorId(questao.getIdQuestao());
				if (q.isRespObjOpcaoA() == resposta.isRespAlunoOpcaoA()
						&& q.isRespObjOpcaoB() == resposta.isRespAlunoOpcaoB()
						&& q.isRespObjOpcaoC() == resposta.isRespAlunoOpcaoB()
						&& q.isRespObjOpcaoD() == resposta.isRespAlunoOpcaoC()
						&& q.isRespObjOpcaoE() == resposta.isRespAlunoOpcaoD()) {

					resposta.setRespondeuCorretamente(true);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// Corrige questao V&F
		if (questao instanceof QuestaoVF) {
			try {
				QuestaoVF q = (QuestaoVF) daoQuestao.buscaPorId(questao.getIdQuestao());
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
				QuestaoOrdenar q = (QuestaoOrdenar) daoQuestao.buscaPorId(questao.getIdQuestao());
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
				QuestaoOrdenar q = (QuestaoOrdenar) daoQuestao.buscaPorId(questao.getIdQuestao());
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
				QuestaoRelacionar q = (QuestaoRelacionar) daoQuestao.buscaPorId(questao.getIdQuestao());
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
		return resposta;

	}

	@Override
	public void doMove(Location destination) {
		System.out.println("Migração para" + destination.getName());
	}

}
