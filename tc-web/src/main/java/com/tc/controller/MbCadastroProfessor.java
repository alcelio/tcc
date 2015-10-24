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
import com.tc.model.Professor;
import com.tc.util.BuscaCEP;
import com.tc.util.GeraPermissoes;

@SessionScoped
@ManagedBean
public class MbCadastroProfessor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	UsuarioBeanDao dao;

	private Professor professor = new Professor();
	private String confirmaSenha;
	private String cep;
	BuscaCEP buscaCep = new BuscaCEP();

	public MbCadastroProfessor() {
		// TODO Auto-generated constructor stub
	}

	public String novoProfessor() {
		professor = new Professor();
		return "publico/cadstroprofessor.jsf";
	}

	public String encerraCadastro() {
		return "publico/login.jsf";
	}

	public void addUsuario() {
		if (professor.getIdUsuario() == null || professor.getIdUsuario() == 0) {
			salvar();
		} else {
			atualizar();
		}

	}

	/**
	 * Método para incluir instancia de usuario corrente na base de dados.
	 */
	private void salvar() {
		//professor.setSenha(ConverterSHA1.cipher(professor.getSenha()));
		professor.setDataCadastro(new Date());
		professor.setPermissoes(GeraPermissoes.getPermissaoProfessor());

		if (professor.getSenha() == null ? confirmaSenha == null
				: professor.getSenha().equals(confirmaSenha)) {

			try {

				if (!buscaCep.getBairro(cep).equals(cep)) {
					carregaCep();
					dao.create(professor);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));

					novoProfessor();
					encerraCadastro();
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "CEP inexistente, informe corretamente.", ""));

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "As senhas não conferem.", ""));
		}
	}

	private void atualizar() {
		dao.update(professor);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}

	public void deletar() {
		dao.remove(professor);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}

	public void carregaCep() {

		try {

			if (buscaCep.getBairro(cep).equals("-")) {
				professor.getEndereco().setDsCidade(buscaCep.getCidade(cep));
				professor.getEndereco().setDsEstado(buscaCep.getUF(cep));
				professor.getEndereco().setDsCEP(cep);
			} else {
				professor.getEndereco().setDsBairro(buscaCep.getBairro(cep));
				professor.getEndereco().setDsCidade(buscaCep.getCidade(cep));
				professor.getEndereco().setDsEndereco(buscaCep.getEndereco(cep));
				professor.getEndereco().setDsCidade(buscaCep.getCidade(cep));
				professor.getEndereco().setDsEstado(buscaCep.getUF(cep));
				professor.getEndereco().setDsCEP(cep);
			}

			try {
				String[] values = buscaCep.getLatLong(cep).split(",");
				professor.getEndereco().setLatitude(values[0]);
				professor.getEndereco().setLongitude(values[1]);
			} catch (ParseException e) {

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Professor getProfessor() {
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
