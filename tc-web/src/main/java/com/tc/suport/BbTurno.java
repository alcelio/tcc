package com.tc.suport;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.tc.data.TurnoBeanDao;
import com.tc.model.Turno;

@ManagedBean(name = "bbTurno")
@RequestScoped
public class BbTurno implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EJB
	TurnoBeanDao dao;
	
	public BbTurno() {
	}
	
	public List<Turno> getTurnos() {
		return dao.listarTurnos();
	}

	
}
