package com.tc.suport;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.tc.data.SexoBeanDao;
import com.tc.model.Sexo;

@ManagedBean(name = "bbSexo")
@RequestScoped
public class BbSexo implements Serializable {

	private static final long serialVersionUID = 1L;
//
//	List<Sexo> sexos = new ArrayList<Sexo>();
	@EJB 
	SexoBeanDao dao = new SexoBeanDao();
	
	public List<Sexo> getSexos() {
		return dao.listarSexo();
	}

}
