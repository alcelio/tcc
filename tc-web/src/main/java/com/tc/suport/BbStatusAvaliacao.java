package com.tc.suport;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import com.tc.data.StatuAvaliacaoDao;
import com.tc.model.StatusAvaliacao;

@ManagedBean
@RequestScoped
public class BbStatusAvaliacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private StatuAvaliacaoDao dao;
	
	public BbStatusAvaliacao() {
	}
	
	public List<StatusAvaliacao> getStatusAvalicoes(){
		return dao.listarStatusAvaliacoes();
	}

}
