package com.tc.controller;

import static com.tc.util.TcChaves.CAMINHO_HOME;
import static com.tc.util.TcChaves.PATH_APLICACAO;

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

@ManagedBean
@SessionScoped
public class MbLoginController implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	UsuarioBeanDao dao;
	private Usuario usuarioLogado;
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
			e.printStackTrace();
		}
		return usuarioLogado;
	}

	private void verificaTipoUsuario(Usuario usuarioLogado) {
		if(usuarioLogado instanceof Usuario){
			setAluno(true);
			setProfessor(true);
			setAdmin(true);
		}
		if(usuarioLogado instanceof Professor){
			setProfessor(true);
			setAdmin(false);
			setAluno(false);
		}
		if(usuarioLogado instanceof Aluno){
			setProfessor(false);
			setAdmin(false);
			setAluno(true);
		}
		
	}

	public void efetuarLogout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(PATH_APLICACAO + CAMINHO_HOME);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
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
	
}
