package com.tc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import com.tc.data.TurmaBeanDao;
import com.tc.model.Turma;

@SessionScoped
@ManagedBean
public class MbCadastroTurma implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	TurmaBeanDao dao;
	
	private Turma turma = new Turma();
	private String caminhoOrigem;
	private List<Turma> turmas = new ArrayList<Turma>();

	
	public MbCadastroTurma() {
	}

	public void setaCaminhoOrigem(String caminhoOrigem) {
 		this.caminhoOrigem = caminhoOrigem;
	}
	
	public String goBack(){
		if(StringUtils.isBlank(caminhoOrigem)){
			return "/restrito/home.jsf";
		}
		return caminhoOrigem;
	}
	
	public String novaTurma() {
		turma = new Turma();
		return "cadastroturma.jsf";
	}
	
	public void addTurma() {
		if (turma.getIdTurma() == null || turma.getIdTurma() == 0) {
			salvar();
		} else {
			atualizar();
		}
	}

	private void salvar() {
		dao.create(turma);
		novaTurma();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
	}

	private void atualizar() {
		dao.update(turma);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}

	public void deletar() {
		dao.remove(turma);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}


	// ****************************************** GETTERS E SETTERS
	// **************************************************
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Turma> getTurmas() {
		turmas = dao.listarTurmas();
		return turmas;
	}

}
