package com.tc.agentes;

import static com.tc.util.IavaliarGlobal.SERVICO_APAGAR_FOGO;
import static com.tc.util.IavaliarGlobal.SERVICO_CORRIGIR_QUESTAO;
import static com.tc.util.IavaliarGlobal.SERVICO_PRENDER_LADRAO;
import static com.tc.util.IavaliarGlobal.SOLICITACAO_CORRECAO;
import static com.tc.util.IavaliarGlobal.SOLICITACAO_DOENTE;
import static com.tc.util.IavaliarGlobal.SOLICITACAO_FOGO;
import static com.tc.util.IavaliarGlobal.SOLICITACAO_LADRAO;

import com.tc.util.IavaliarGlobal;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class SolicitanteOriginal extends Agent {
	private static final long serialVersionUID = 1L;

	@Override
	protected void setup() {
		System.out.println("SMA - Agente SolicitanteOriginal inicializado...");
		
		Object[] args = getArguments();
		
		if (args != null && args.length > 0) {
		
			String argumento = (String) args[0];
			
			//Se o argumento for correção
			if (argumento.equals(SOLICITACAO_CORRECAO)) {
				
				ServiceDescription servico = new ServiceDescription();
				
				servico.setType(SERVICO_CORRIGIR_QUESTAO);
				
				busca(servico, SOLICITACAO_CORRECAO);

			}
			
			//Se o argumento for fogo
			if (argumento.equals(SOLICITACAO_FOGO)) {
				
				ServiceDescription servico = new ServiceDescription();
				
				servico.setType(SERVICO_APAGAR_FOGO);
				
				busca(servico, SOLICITACAO_FOGO);

			}
			
			//Se o argumento for ladrão
			if (argumento.equals(SOLICITACAO_LADRAO)) {
				
				ServiceDescription servico = new ServiceDescription();
				
				servico.setType(SERVICO_PRENDER_LADRAO);
				
				busca(servico, SOLICITACAO_LADRAO);

			}
			
			//Se o argumento for fogo
			if (argumento.equals(SOLICITACAO_DOENTE)) {
				
				ServiceDescription servico = new ServiceDescription();
				
				servico.setType(IavaliarGlobal.SERVICO_CURAR_DOENCA);
				
				busca(servico, SOLICITACAO_DOENTE);

			}
			
			//Comportamento para receber mensagens
			addBehaviour(new CyclicBehaviour(this) {
				private static final long serialVersionUID = 1L;

				@Override
				public void action() {
					ACLMessage msg = receive();
					if(msg != null){
						System.out.println(msg.getSender().getLocalName() + ": " +msg.getContent() );
					}else{
						block();
					}
					
				}
			});
		}
	}

	protected void busca(final ServiceDescription sd, final String pedido) {
		// a cada tres segundos tenta buscar por agentes que forncem o servico;
		
		addBehaviour(new TickerBehaviour(this, 3000) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onTick() {
				System.out.println("Buscando serviços..." );

				DFAgentDescription dfd = new DFAgentDescription();
				
				dfd.addServices(sd);
				
				try {
					DFAgentDescription[] resultado = DFService.search(myAgent, dfd);
					
					if (resultado.length != 0) {
						
						ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
						
						msg.addReceiver(resultado[0].getName());
						
						msg.setContent(pedido);
						
						myAgent.send(msg);
						
						stop();//Finaliza o comportamento
						
					}
				} catch (FIPAException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
