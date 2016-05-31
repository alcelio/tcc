package com.tc.agentes;

import static com.tc.util.IavaliarGlobal.AGENTE_CORRETOR;
import static com.tc.util.IavaliarGlobal.PREFIXO_AGENTE_ALUNO;

import java.io.IOException;

import com.tc.model.Respostas;
import com.tc.util.IavaliarGlobal;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Classe que implementa os comportamentos para o agente
 * 
 * @author Alcélio Gomes
 *
 */

public class AgenteAluno extends Agent {
	private static final long serialVersionUID = 1L;

	@Override
	protected void setup() {
		System.out.println(PREFIXO_AGENTE_ALUNO +" inicializado...");

		addBehaviour(new TickerBehaviour(this, 3000) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onTick() {
//				if (!getMapAgentes().containsValue(getLocalName()))
//					doDelete();

//				System.out.println("SMA - Agente " + getLocalName() + " trabalhando...");

			}
		});

		addBehaviour(new CyclicBehaviour() {
			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				ACLMessage message = receive();
				if (message != null) {
					System.out.println("Mensagem recebida: " + message.getContent() + ". Enviada por: "
							+ message.getSender().getLocalName());
				} else {
					block();
				}

			}
		});

	}
	
	public boolean solicitaCorrecao(Respostas respostas){
		try {
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setContentObject(respostas);
			msg.addReceiver(new AID(AGENTE_CORRETOR, AID.ISLOCALNAME));
			this.send(msg);
			return true;
	} catch (IOException e) {
		e.printStackTrace();
		return false;
	}
		
	}

	@Override
	protected void takeDown() {
		System.out.println("SMA - Agente " + this.getLocalName() + " foi encerrado no sistema.");

	}

}
