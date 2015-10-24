package com.tc.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import com.tc.data.InstituicaoBeanDao;
import com.tc.model.Instituicao;

@ManagedBean
@SessionScoped
public class MbCadastroInstiuicao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private InstituicaoBeanDao dao;
	
	private Instituicao instituicao = new Instituicao();
	private List<Instituicao> instituicoes;
	private String caminhoOrigem;
	
	public MbCadastroInstiuicao() {
	}
	
	public void setaCaminhoOrigem(String caminhoOrigem) {
 		this.caminhoOrigem = caminhoOrigem;
	}

	public String goBack(){
		if(StringUtils.isBlank(caminhoOrigem)){
			return "/restrito/home.jsf";
		}
		return caminhoOrigem;
	}
	
	 	public String editInstituicao() {
		 	return "professor/manutencaoinstituicao.jsf";
	    }
	 
	    public String limpInstituicao() {
	        instituicao = new Instituicao();
	        return editInstituicao();
	    }


	    public String addCidade() {
	        if (instituicao.getIdInstituicao() == null || instituicao.getIdInstituicao() == 0) {
	            insertInstituicao();
	        } else {
	            updateInstituicao();
	        }
	        limpInstituicao();
	        return null;
	    }

	    private void insertInstituicao() {
	        dao.create(instituicao);
	        FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
	    }

	    private void updateInstituicao() {
	        dao.update(instituicao);
	        FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
	    }
	    
	    public void deleteInstituicao(){
	       dao.remove(instituicao);
	       editInstituicao();
	       FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso", ""));
	    }

		public Instituicao getInstituicao() {
			return instituicao;
		}

		public void setInstituicao(Instituicao instituicao) {
			this.instituicao = instituicao;
		}

		public List<Instituicao> getInstituicoes() {
			instituicoes = dao.listarInstituicao();
			return instituicoes;
		}

		public void setInstituicoes(List<Instituicao> instituicoes) {
			this.instituicoes = instituicoes;
		}
	    
	    
	
	
	
	

}
