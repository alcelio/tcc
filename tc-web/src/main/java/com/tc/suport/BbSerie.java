package com.tc.suport;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.tc.data.SerieBeanDao;
import com.tc.model.Serie;

@ManagedBean(name = "bbSerie")
@RequestScoped
public class BbSerie implements Serializable{
	private static final long serialVersionUID = 1L;

	@EJB
	SerieBeanDao dao;
	
	public BbSerie() {
	}
		
	public List<Serie> getSerie() {
		return dao.listarSeries();
	}

}
