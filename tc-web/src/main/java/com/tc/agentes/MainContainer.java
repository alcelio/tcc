package com.tc.agentes;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;

public class MainContainer {

	public static void main(String[] args) {
		try {
			Runtime rt = Runtime.instance();
			Properties p = new ExtendedProperties();
			p.setProperty("gui", "true");
			p.setProperty(ProfileImpl.CONTAINER_NAME, "Iavaliar");
			ProfileImpl pc = new ProfileImpl(p);
			
			AgentContainer container = rt.createMainContainer(pc);
			container.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
