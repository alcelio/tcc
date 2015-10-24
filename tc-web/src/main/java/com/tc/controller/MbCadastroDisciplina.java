package com.tc.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tc.data.DisciplinaBeanDao;
import com.tc.model.Disciplina;

@SessionScoped
@ManagedBean
public class MbCadastroDisciplina implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	DisciplinaBeanDao dao;

	private Disciplina disciplina = new Disciplina();
	
	private String caminhoOrigem;

	public MbCadastroDisciplina() {
	}

	public void novaDisciplina() {
		disciplina = new Disciplina();
	}

	public String encerraCadastro() {
		return "publico/login.jsf";
	}

	public void addDisciplina() {
		if (disciplina.getIdDisciplina() == null || disciplina.getIdDisciplina() == 0) {
			salvar();
		} else {
			atualizar();
		}
	}

	/**
	 * Método para incluir instancia de usuario corrente na base de dados.
	 */
	private void salvar() {
		dao.create(disciplina);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
		novaDisciplina();
	}
	
	public String goBack(){
		return caminhoOrigem;
	}
	
	public void setaCaminhoOrigem(String caminhoOrigem) {
		this.caminhoOrigem = caminhoOrigem;
	}
	
	private void atualizar() {
		dao.update(disciplina);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}

	public void deletar() {
		dao.remove(disciplina);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Disciplina> getDisciplinas() {
		return dao.listarDisciplina();
	}

//	public void setDisciplinas(List<Disciplina> disciplinas) {
//		this.disciplinas = disciplinas;
//	}

	

}
