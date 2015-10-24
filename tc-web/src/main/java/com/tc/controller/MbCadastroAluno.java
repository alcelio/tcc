package com.tc.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.tc.data.UsuarioBeanDao;
import com.tc.model.Aluno;
import com.tc.model.Usuario;
import com.tc.util.BuscaCEP;
import com.tc.util.GeraPermissoes;

@SessionScoped
@ManagedBean
public class MbCadastroAluno implements Serializable {
	private static final long serialVersionUID = 1L;


	@EJB
	UsuarioBeanDao dao;

	private Aluno aluno = new Aluno();
	private String confirmaSenha;
	private String cep;
	BuscaCEP buscaCep = new BuscaCEP();

	public MbCadastroAluno() {

	}
	public String novoAluno(){
		aluno = new Aluno();
		return "publico/cadastroaluno.jsf";
	}

	public String encerraCadastro(){
		return "/restrito/home.jsf";
	}
	
	public void addUsuario() {
		if (aluno.getIdUsuario() == null || aluno.getIdUsuario() == 0) {
			salvar();
		} else {
			atualizar();
		}
	}
	
	/**
	 * Método para incluir instancia de usuario corrente na base de dados.
	 */
	private void salvar() {
		aluno.setDataCadastro(new Date());
		aluno.setPermissoes(GeraPermissoes.getPermissaoAluno());

		if (aluno.getSenha() == null ? confirmaSenha == null
				: aluno.getSenha().equals(confirmaSenha)) {
			try {
				if(dao.isExisteLogin(aluno.getLogin())){
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Usa", ""));
					
					
				}
//				if (!buscaCep.getBairro(cep).equals(cep)) {
					//carregaCep();
					dao.create(aluno);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
					encerraCadastro();
//				} else {
//					setCep("");
//					FacesContext.getCurrentInstance().addMessage(null,
//							new FacesMessage(FacesMessage.SEVERITY_INFO, "CEP inexistente, informe corretamente.", ""));
//
//				}
//
			} catch (Exception e) {

			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "As senhas não conferem.", ""));
		}
	}

	private void atualizar() {
		dao.update(aluno);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}

	public void deletar() {
		dao.remove(aluno);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}

	public void carregaCep() {
		try {
			if (buscaCep.getBairro(cep).equals("-")) {
				aluno.getEndereco().setDsCidade(buscaCep.getCidade(cep));
				aluno.getEndereco().setDsEstado(buscaCep.getUF(cep));
				aluno.getEndereco().setDsCEP(cep);
			} else {
				aluno.getEndereco().setDsBairro(buscaCep.getBairro(cep));
				aluno.getEndereco().setDsCidade(buscaCep.getCidade(cep));
				aluno.getEndereco().setDsEndereco(buscaCep.getEndereco(cep));
				aluno.getEndereco().setDsCidade(buscaCep.getCidade(cep));
				aluno.getEndereco().setDsEstado(buscaCep.getUF(cep));
				aluno.getEndereco().setDsCEP(cep);
			}
			try {
				String[] values = buscaCep.getLatLong(cep).split(",");
				aluno.getEndereco().setLatitude(values[0]);
				aluno.getEndereco().setLongitude(values[1]);
			} catch (ParseException e) {
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public Usuario getAluno() {
		return aluno;
	}
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	
	

}
