package com.tc.agentes;

import static com.tc.agentes.PlataformaAgentes.getMapEmCorrecao;
import static com.tc.util.IavaliarGlobal.PREFIXO_AGENTE_PROFESSOR;
import static com.tc.util.IavaliarGlobal.SOLICITACAO_CORRECAO;

import java.io.IOException;

import com.tc.beans.BeanSolicitacao;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class Solicitante extends Agent {
	private static final long serialVersionUID = 1L;

	@Override
	protected void setup() {
		System.out.println("SMA - Agente Solicitante inicializado...");
		
		final BeanSolicitacao bean;
		
		Object[] args = getArguments();
		
		if (args != null && args.length > 0) {
			
			bean = (BeanSolicitacao) args[0];

			if (bean.getSolicitacao().equals(SOLICITACAO_CORRECAO)) {
				solicitaCorrecao(bean);
			}
			//Comportamento para receber mensagens
			addBehaviour(new CyclicBehaviour(this) {
				private static final long serialVersionUID = 1L;

				@Override
				public void action() {
					ACLMessage msg = receive();
					//Ao receber a mensagem de resposta a solicitação é enecerrada
					if(msg != null ){
						System.out.println(msg.getSender().getLocalName() + ": " +msg.getContent() );
						doDelete();
					}else{
						block();
					}
					
				}
			});
		}
	}
	
	protected void solicitaCorrecao(final BeanSolicitacao bean) {
		
		addBehaviour(new OneShotBehaviour() {
			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				
				try {
					ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
					
					msg.addReceiver(new AID(PREFIXO_AGENTE_PROFESSOR, AID.ISLOCALNAME));
					msg.setContentObject(bean);
					myAgent.send(msg);

				} catch (IOException e) {
					e.printStackTrace();
				}

				
			}
		});		
	}

}
