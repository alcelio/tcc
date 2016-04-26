package com.tc.controller;

import static com.tc.util.WebGlobals.PAGINA_HOME;
import static com.tc.util.WebGlobals.PATH_APLICACAO;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.tc.data.UsuarioBeanDao;
import com.tc.model.Aluno;
import com.tc.model.Professor;
import com.tc.model.Usuario;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

@ManagedBean
@SessionScoped
public class MbLoginController implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	UsuarioBeanDao dao;
	private static Usuario usuarioLogado;
	private boolean professor;
	private boolean aluno;
	private boolean admin;

	public MbLoginController() {

	}

	public Usuario procuraUsuario(String login) {
		try {
			setUsuarioLogado(dao.buscaUsuarioPorLogin(login));
			verificaTipoUsuario(getUsuarioLogado());
		} catch (Exception e) {
			System.out.println("Erro" + e.getMessage());
		
		}
		return usuarioLogado;
	}

	private void verificaTipoUsuario(Usuario usuarioLogado) {
		if (usuarioLogado instanceof Usuario) {
			setAluno(true);
			setProfessor(true);
			setAdmin(true);
		}
		if (usuarioLogado instanceof Professor) {
			setProfessor(true);
			setAdmin(false);
			setAluno(false);
			//criaAgentePerfil("Professor");
		}
		if (usuarioLogado instanceof Aluno) {
			setProfessor(false);
			setAdmin(false);
			setAluno(true);
			//criaAgentePerfil("Aluno");
		}

	}

	private void criaAgentePerfil(String nome) {

		try {
			Runtime rt = Runtime.instance();
			Profile p = new ProfileImpl();
			
			p.setParameter(Profile.PLATFORM_ID,    "sink-platform");
	        p.setParameter(Profile.LOCAL_HOST,     "http://localhost:1099");
	        p.setParameter(Profile.CONTAINER_NAME, "sink-container");
	        p.setParameter(Profile.MTPS, "jade.mtp.http.MessageTransportProtocol(http://localhost:1099)");
			
			
			ContainerController cc = rt.createMainContainer(p);
			AgentController ac = cc.createNewAgent("AgentePerfil" + nome + usuarioLogado.getIdUsuario(),
					"com.tc.agentes.AgentePerfil", null);
			AgentController rma = cc.createNewAgent("rma", "jade.tools.rma.rma", null);
			ac.start();
			rma.start();
		} catch (StaleProxyException e) {
			System.out.println("Erro ao inicializar agente de perfil para o usuário: "
					+ MbLoginController.getUsuarioLogado().getNome()+ e.getMessage());
		}

	}

	public void efetuarLogout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(PATH_APLICACAO + PAGINA_HOME);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isProfessor() {
		return professor;
	}

	public void setProfessor(boolean professor) {
		this.professor = professor;
	}

	public boolean isAluno() {
		return aluno;
	}

	public void setAluno(boolean aluno) {
		this.aluno = aluno;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public static Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public static void setUsuarioLogado(Usuario usuarioLogado) {
		MbLoginController.usuarioLogado = usuarioLogado;
	}

}
