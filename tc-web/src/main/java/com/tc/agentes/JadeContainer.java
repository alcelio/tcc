package com.tc.agentes;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class JadeContainer {
	public static void main(String[] args) {
		try {
			Runtime rt = Runtime.instance();
			ProfileImpl pc = new ProfileImpl(false);
			pc.setParameter(ProfileImpl.MAIN_HOST, "localhost");
//			pc.setParameter(ProfileImpl.CONTAINER_NAME, "localhost");
			AgentContainer ac = rt.createAgentContainer(pc);
			AgentController agentControllerBOmbeiro =ac.createNewAgent("Bombeiro", Bombeiro.class.getName(), null);
			agentControllerBOmbeiro.start();
			
			AgentController agentControllerMedico =ac.createNewAgent("Medico", Medico.class.getName(), null);
			agentControllerMedico.start();
			
			AgentController agentControllerPolicial =ac.createNewAgent("Policial", Policial.class.getName(), null);
			agentControllerPolicial.start();
			ac.start();
		} catch (ControllerException e) {
			e.printStackTrace();
		}
	}

}
