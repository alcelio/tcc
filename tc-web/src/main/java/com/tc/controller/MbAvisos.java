package com.tc.controller;

import static com.tc.controller.MbLoginController.getUsuarioLogado;
import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.tc.data.AvisosBeanDao;
import com.tc.model.Avisos;

@SessionScoped
@ManagedBean
public class MbAvisos implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	AvisosBeanDao dao;

	private Avisos aviso;
	private String caminhoOrigem;
	private List<Avisos> avisos;

	@PostConstruct
	public void init() {
		try {
			setAvisos(dao.listarAvisosPendenteUsuario(getUsuarioLogado())); 
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método que orienta qual pagina deverá ser retornada
	 * 
	 * @return
	 */
	public String goBack() {
		if (isBlank(getCaminhoOrigem())) {
			return PAGINA_HOME;
		}
		return getCaminhoOrigem();
	}
	
	public void deletar(){
		dao.remove(getAviso());
	}

	
	public String visitarAviso(){
		getAviso().setAtivo(false);
		dao.update(getAviso());
		return getAviso().getLink();
	}
	public void atualizar() {
		
	}


	public Avisos getAviso() {
		return aviso;
	}

	public void setAviso(Avisos aviso) {
		this.aviso = aviso;
	}

	public List<Avisos> getAvisos() {
		try {
			setAvisos(dao.listarAvisosPendenteUsuario(getUsuarioLogado())); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return avisos;
	}

	public void setAvisos(List<Avisos> avisos) {
		this.avisos = avisos;
	}

	public String getCaminhoOrigem() {
		return caminhoOrigem;
	}

	public void setCaminhoOrigem(String caminhoOrigem) {
		this.caminhoOrigem = caminhoOrigem;
	}

}
