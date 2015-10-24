package com.tc.suport;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import com.tc.data.TurmaBeanDao;
import com.tc.model.Turma;

@ManagedBean
@RequestScoped
@ViewScoped
public class BbTurmas {
	
	@EJB
	private TurmaBeanDao dao;
	
	public BbTurmas() {
	}
	
	public List<Turma> getTurmas(){
		return dao.listarTurmas();
	}
}
