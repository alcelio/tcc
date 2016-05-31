package com.tc.agentes;

import static com.tc.util.IavaliarGlobal.SERVICO_APAGAR_FOGO;
import static com.tc.util.IavaliarGlobal.SOLICITACAO_FOGO;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class Bombeiro extends Agent {
	private static final long serialVersionUID = 1L;

	@Override
	protected void setup() {
		
		System.out.println(" Agente Bombeiro inicializado...\n Aguardando informações...");
		
		// Descrição do servico
		ServiceDescription servico = new ServiceDescription();
		//Seu serviço é apagar fogo
		servico.setType(SERVICO_APAGAR_FOGO);
		
		servico.setName(this.getLocalName());
		
		registraServico(servico);
		
		recebeMensagens(SOLICITACAO_FOGO, "Vou apagar o fogo.");
		
	}
	
	//Método para registar serviço
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
	protected void recebeMensagens(final String mensagem, final String resposta){
		
		addBehaviour(new CyclicBehaviour(this) {
			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				ACLMessage msg = receive();

				if(msg != null){
					
					if(msg.getContent().equals(mensagem)){
					
						ACLMessage reply = msg.createReply();
						
						reply.setContent(resposta);
						
						myAgent.send(reply);
					}
				}else{
					block();
				}
			}
		});
	}

}
