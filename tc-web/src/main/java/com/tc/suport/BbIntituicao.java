package com.tc.suport;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.tc.data.InstituicaoBeanDao;
import com.tc.model.Instituicao;

@ManagedBean(name = "bbInstituicao")
@RequestScoped
public class BbIntituicao implements Serializable{
	private static final long serialVersionUID = 1L;

	@EJB
	InstituicaoBeanDao dao;
	
	public BbIntituicao() {
	}
		
	public List<Instituicao> getInstituicoes() {
		return dao.listarInstituicao();
	}

}
