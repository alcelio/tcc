package com.tc.controller;

import static com.tc.util.WebGlobals.PAGINA_HOME;
import static com.tc.util.WebGlobals.PATH_APLICACAO;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.tc.data.UsuarioBeanDao;
import com.tc.model.Aluno;
import com.tc.util.GeraPermissoes;

@SessionScoped
@ManagedBean
public class MbCadastroAluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	UsuarioBeanDao dao;

	private Aluno aluno;
	private String confirmaSenha;
	private String caminhoOrigem;
	
	
	public String goBack() {
		if(isBlank(caminhoOrigem)){
			return PAGINA_HOME;
		}else{
			return getCaminhoOrigem();
		}
	}

	public void addUsuario() {
		if (getAluno().getSenha() == null ? getConfirmaSenha() == null : getAluno().getSenha().equals(getConfirmaSenha())) {

			try {
				getAluno().setDataCadastro(new Date());
				getAluno().setPermissoes(GeraPermissoes.getPermissaoAluno());
				getAluno().setAtivo(true);
				getAluno().setPrimeiroAcesso(true);

				if (dao.isExisteLogin(getAluno().getLogin())) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Este login já esta sendo utilizado, informe outro!", ""));
				}
				dao.create(aluno);
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
	
	public Aluno getAluno(){
		if(aluno == null){
			aluno = new Aluno();
		}
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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
}
