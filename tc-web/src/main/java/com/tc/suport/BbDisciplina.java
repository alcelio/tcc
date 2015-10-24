package com.tc.suport;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import com.tc.data.DisciplinaBeanDao;
import com.tc.model.Disciplina;

@ManagedBean
@RequestScoped
@ViewScoped
public class BbDisciplina {
	
	@EJB
	private DisciplinaBeanDao dao;
	
	public BbDisciplina() {
	}
	
	public List<Disciplina> getDisciplinas(){
		return dao.listarDisciplina();
	}
}
