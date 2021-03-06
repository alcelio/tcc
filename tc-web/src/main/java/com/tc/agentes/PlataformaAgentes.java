package com.tc.agentes;

import static com.tc.util.IavaliarGlobal.AGENTE_SOLICITANTE;
import static com.tc.util.IavaliarGlobal.PREFIXO_AGENTE_ALUNO;
import static com.tc.util.IavaliarGlobal.PREFIXO_AGENTE_PROFESSOR;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.tc.beans.BeanMensagem;
import com.tc.beans.BeanSolicitacao;
import com.tc.controller.AgentesController;
import com.tc.controller.MbLoginController;
import com.tc.model.Avisos;
import com.tc.model.Usuario;

import jade.core.Agent;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

public class PlataformaAgentes extends Agent {
	private static final long serialVersionUID = 1L;
	
	private static AgentContainer agentContainer;

	private static HashMap<String, BeanMensagem> mapMenasagensUsuario;
	
	private static HashMap<String, Boolean> mapEmCorrecao;
	
	private static HashMap<String, Object > mapInformacoes;
	
	private static List<Avisos> listaAvisos;
	
	public static boolean isPlataformaInicializada;
	
	public static void main(String[] args) {
		try {
			getMainContainer();
			taskPersisteAvisos();
			//criaContainerAgentes();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * Método para obter a intância do container principal da aplicação, seguindo o padrão singleton, caso ela já tenha sido criado
 * @return
 * @throws ControllerException
 */
	public static AgentContainer getMainContainer() throws ControllerException {
		
		if (agentContainer == null) {
			//Necessária para obeter a execução do framawork JADE
			Runtime rt = Runtime.instance();
			
			//Cria e seta algumas propriedades para o container que irá receber os agentes
			Properties p = new ExtendedProperties();
			p.setProperty("gui", "true");
			p.setProperty(ProfileImpl.CONTAINER_NAME, "Iavaliar");
			ProfileImpl pc = new ProfileImpl(p);
			
			//Cria o container para que irá armazenar os agentes
			agentContainer = rt.createMainContainer(pc);
			
			//Cria adiciona o agente professor ao container
			AgentController agentControllerProfessor = agentContainer.createNewAgent(PREFIXO_AGENTE_PROFESSOR, AgenteProfessor.class.getName(), null);
			agentControllerProfessor.start();

			//Cria adiciona o agente aluno ao container
			AgentController agentControllerAluno = agentContainer.createNewAgent(PREFIXO_AGENTE_ALUNO,AgenteAluno.class.getName(), null);
			agentControllerAluno.start();
			
			//Inicializa o container
			agentContainer.start();
			
			isPlataformaInicializada = true;
		}
		return agentContainer;
	}

	public static void criaNovoAgente(Usuario usuario, String nome, Class<?> clazz, Object[] args) throws StaleProxyException, ControllerException {
		AgentController agentController = getMainContainer().createNewAgent(nome,clazz.getName(), args);
		agentController.start();
	}
	
	/**
	 * Método que envia uma mensagem solicitando o serviço descrito no bean passaso como parametro
	 * @param descricaoSolicitação
	 * @throws ControllerException 
	 * @throws StaleProxyException 
	 */
	public static void enviaSolicitacaoServico(BeanSolicitacao bean) throws StaleProxyException, ControllerException{
		criaNovoAgente(MbLoginController.getUsuarioLogado(), AGENTE_SOLICITANTE,Solicitante.class, new Object[] { bean });
	}

	/**
	 * Mapa para guardar as mensagens dos agentes para o usuário
	 * 
	 * @return
	 */
	public static HashMap<String, BeanMensagem> getMapMensagensUsuarios() {
		if (mapMenasagensUsuario == null)
			mapMenasagensUsuario = new HashMap<String, BeanMensagem>();
		return mapMenasagensUsuario;
	}
	
	public static List<Avisos> getListaDeAvisos(){
		if(listaAvisos == null){
			listaAvisos = new ArrayList<Avisos>();
		}
		return listaAvisos;
	}
	
	/**
	 * Mapa para guardar os nomes dos agentes ativos no sistema
	 * 
	 * @return
	 */
	public static HashMap<String, Object> getMapInformacoes() {
		if (mapInformacoes == null)
			mapInformacoes = new HashMap<String, Object>();
		return mapInformacoes;
	}
	
	/**
	 * Mapa para guardar os nomes dos agentes ativos no sistema
	 * 
	 * @return
	 */
	public synchronized static HashMap<String, Boolean> getMapEmCorrecao() {
		if (mapEmCorrecao == null)
			mapEmCorrecao = new HashMap<String, Boolean>();
		return mapEmCorrecao;
	}

	public static void criaContainerAgentes() {
		try {
			//Necessária para obeter a execução do framawork JADE
			Runtime rt = Runtime.instance();
			//Cria e seta algumas propriedades para o container que irá receber os agentes
			Properties p = new ExtendedProperties();
			p.setProperty(ProfileImpl.CONTAINER_NAME, "Iavaliar");
			ProfileImpl pc = new ProfileImpl(false);
			pc.setParameter(ProfileImpl.MAIN_HOST, "localhost");
			
			//Cria o container para que irá armazenar os agentes
			AgentContainer ac = rt.createAgentContainer(pc);

			//Cria adiciona o agente professpr ao container
			AgentController agentControllerProfessor = ac.createNewAgent(PREFIXO_AGENTE_PROFESSOR, AgenteProfessor.class.getName(), null);
			//Iniciliaza o agente
			agentControllerProfessor.start();
			//Cria adiciona o agente professpr ao container
			AgentController agentControllerAluno = ac.createNewAgent(PREFIXO_AGENTE_ALUNO,AgenteAluno.class.getName(), null);
			//Iniciliaza o agente
			agentControllerAluno.start();
			
			////Iniciliaza o container de agentes.
			ac.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * Utilizado para persistir as avisos geradas pelos agentes;
	 * */
	private static void taskPersisteAvisos() {
		
		// Converte os minutos para miles,
		int miles = 1 * 1 * 10000;
		System.out.println("Gravando avisos...");
		Timer timer = new Timer("Thr-gravaAviso-task");
		TimerTask t = new TimerTask() {
			@Override
			public void run() {
				try {
					PlataformaAgentes.gravarAvisosGerados();
				} catch (Exception e) {
					System.out.println("Erro ao gravar avisos...");
				}
			}
		};
		timer.schedule(t, new Date(), miles);
	}
	
	private static void gravarAvisosGerados(){
		if(listaAvisos != null && listaAvisos.size() > 0){
			for (Avisos aviso : listaAvisos) {
				try {
					if(!AgentesController.daoAvisos.isExisteAviso(aviso.getUsuario(), aviso.getTipoAviso())){
						AgentesController.daoAvisos.create(aviso);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				listaAvisos.clear();
			}
		}
	}

}
