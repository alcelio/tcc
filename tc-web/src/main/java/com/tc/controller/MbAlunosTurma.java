package com.tc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tc.beans.BeanUsuarioLogado;
import com.tc.data.AlunosTurmaBeanDao;
import com.tc.data.TurmaBeanDao;
import com.tc.data.UsuarioBeanDao;
import com.tc.model.AlunosTurma;
import com.tc.model.Turma;
import com.tc.model.PK.AlunosTurmaPK;

@SessionScoped
@ManagedBean
public class MbAlunosTurma implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private AlunosTurmaBeanDao dao;
	@EJB
	private UsuarioBeanDao daoUsuario;
	@EJB
	private TurmaBeanDao daoTurma;

	private List<AlunosTurma> turmasAluno;
	private Turma turma;
	
	@PostConstruct
	public void init() {
		if (turma == null) {
			turma = new Turma();
		}
		if (turmasAluno == null) {
			turmasAluno = new ArrayList<AlunosTurma>();
		}

		try {
			turmasAluno = dao.listarTurmasPorAluno(BeanUsuarioLogado.usualioLogado);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro ao obter turmas do aluno, reinicie o processo.", ""));
		}
	}

	public void addAlunoTurma() throws Exception {
		AlunosTurma ta = new AlunosTurma();
		AlunosTurmaPK pk = new AlunosTurmaPK();
		pk.setIdTurma(turma.getIdTurma());
		pk.setIdUsuario(BeanUsuarioLogado.usualioLogado.getIdUsuario());
		if(dao.isExistePk(pk)){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Aluno já está cadastrado nesta turma.", ""));
			return;
		}
		
		ta.setId(pk);
		ta.setAluno(BeanUsuarioLogado.usualioLogado);
		ta.setTurma(turma);

		dao.create(ta);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
	}


	// ****************************************** GETTERS E SETTERS
	// **************************************************


	public List<AlunosTurma> getTurmasAluno() {
		try {
			turmasAluno = dao.listarTurmasPorAluno(BeanUsuarioLogado.usualioLogado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return turmasAluno;
	}
	public void setTurmasAluno(List<AlunosTurma> turmasAluno) {
		this.turmasAluno = turmasAluno;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
    
}
