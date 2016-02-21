package com.tc.controller;

import static com.tc.util.WebGlobals.PAGINA_HOME;
import static com.tc.util.WebGlobals.PATH_APLICACAO;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import com.tc.data.UsuarioBeanDao;
import com.tc.model.Professor;
import com.tc.util.GeraPermissoes;

@SessionScoped
@ManagedBean
public class MbCadastroProfessor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	UsuarioBeanDao dao;

	private Professor professor;
	private String confirmaSenha;
	private String caminhoOrigem;
	
	public Professor getProfessor(){
		if(professor == null){
			professor = new Professor();
		}
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	public String getCaminhoOrigem() {
		return caminhoOrigem;
	}
	public void setCaminhoOrigem(String vaminhoOrigem) {
		this.caminhoOrigem = vaminhoOrigem;
	}
	
	public String goBack() {
		if(StringUtils.isBlank(caminhoOrigem)){
			return PAGINA_HOME;
		}else{
			return getCaminhoOrigem();
		}
	}

	public void addUsuario() {
		if (getProfessor().getSenha() == null ? getConfirmaSenha() == null : getProfessor().getSenha().equals(getConfirmaSenha())) {

			try {
				getProfessor().setDataCadastro(new Date());
				getProfessor().setPermissoes(GeraPermissoes.getPermissaoProfessor());
				getProfessor().setAtivo(true);
				getProfessor().setPrimeiroAcesso(true);

				if (dao.isExisteLogin(getProfessor().getLogin())) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Este login já esta sendo utilizado, informe outro!", ""));
				}
				dao.create(professor);
				FacesContext.getCurrentInstance().getExternalContext().redirect(PATH_APLICACAO + PAGINA_HOME);

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Erro de gravação no banco de dados, tente novamente", ""));
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "As senhas não conferem.", ""));
		}
	}


}
