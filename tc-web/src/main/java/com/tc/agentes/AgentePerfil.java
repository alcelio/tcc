package com.tc.agentes;

import jade.core.Agent;

/**
 * Classe que implementa os comportamentos para o agente
 * @author Alcélio Gomes
 *
 */

public class AgentePerfil extends Agent{
	private static final long serialVersionUID = 1L;

	@Override
	protected void setup() {
		super.setup();
		System.out.println("SMA - Agente " + getLocalName() + " foi ativdo no sistema");
		
	}
	
	
	@Override
	public void doDelete() {
		super.doDelete();
		System.out.println("SMA  - Agente" + getLocalName()+ " foi desativado.");
	}

}
