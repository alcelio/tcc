package com.tc.suport;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import com.tc.data.TopicoEstudoBeanDao;
import com.tc.model.Disciplina;
import com.tc.model.TopicoEstudo;

@ManagedBean
@RequestScoped
@ViewScoped
public class BbTopicoEstudo {
	
	@EJB
	private TopicoEstudoBeanDao dao;
	
	public BbTopicoEstudo() {
	}
	
	public List<TopicoEstudo> getTopicoEstudo(){
		return dao.listarTopicoEstudo();
	}
	
	public List<TopicoEstudo> getListTopicosPorDisciplina(Disciplina disciplina){
		try {
			return dao.listarTopicoEstudoDisciplina(disciplina);
		} catch (Exception e) {
			return null;
		}
	}
}
