package com.tc.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.tc.data.UsuarioBeanDao;
import com.tc.model.Usuario;
import com.tc.suport.BeanUsuarioLogado;

@ManagedBean
@SessionScoped
public class MbLoginController implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	UsuarioBeanDao dao;
	private Usuario usuarioLogado;


	public MbLoginController() {

	}

	public Usuario procuraUsuario(String login) {
		usuarioLogado = new Usuario();
		try {
			usuarioLogado = dao.buscaUsuarioPorLogin(login);
			BeanUsuarioLogado.usualioLogado = usuarioLogado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarioLogado;
	}
	
	public String efetuarLogout(){ 
		FacesContext fc = FacesContext.getCurrentInstance(); 
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); 
		session.invalidate(); 
		
		return "restrito/home.jsf"; 
		}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	} 
	
		

}
