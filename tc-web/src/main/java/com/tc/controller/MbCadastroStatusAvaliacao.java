package com.tc.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.tc.data.StatuAvaliacaoDao;
import com.tc.model.StatusAvaliacao;

@ManagedBean
@ViewScoped
public class MbCadastroStatusAvaliacao {

	@EJB
	private StatuAvaliacaoDao dao;
	
	private StatusAvaliacao statusAvaliacao = new StatusAvaliacao();
	private List<StatusAvaliacao> listaStatusAvaliacao;
	
	public MbCadastroStatusAvaliacao() {
	}

	
	 public String limpStatusAvaliacao() {
	        statusAvaliacao = new StatusAvaliacao();
	        return editStatusAvaliacao();
	    }

	    public String editStatusAvaliacao() {
	        return "resources/restrict/admin/cadastrostatusavaliacao.jsf";
	    }
	    
	    public String limpstatusAvaliacao() {
	        statusAvaliacao = new StatusAvaliacao();
	        return editStatusAvaliacao();
	    }

	    public String addStatusAvaliacao() {
	        if (statusAvaliacao.getIdStatusAvaliacao() == null || statusAvaliacao.getIdStatusAvaliacao() == 0) {
	            insertStatusAvaliacao();
	        } else {
	            updateStatusAvaliacao();
	        }
	        limpStatusAvaliacao();
	        return null;
	    }

	    private void insertStatusAvaliacao() {
	        dao.create(statusAvaliacao);
	        FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
	    }

	    private void updateStatusAvaliacao() {
	        dao.update(statusAvaliacao);
	        FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
	    }
	    
	    public void deleteStatusAvaliacao(){
	       dao.remove(statusAvaliacao);
	       editStatusAvaliacao();
	       FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluido com sucesso", ""));
	    }
	
	
	public StatusAvaliacao getStatusAvaliacao() {
		return statusAvaliacao;
	}

	public void setStatusAvaliacao(StatusAvaliacao statusAvaliacao) {
		this.statusAvaliacao = statusAvaliacao;
	}

	public List<StatusAvaliacao> getListaStatusAvalicao() {
		listaStatusAvaliacao = dao.listarStatusAvaliacoes();
		return listaStatusAvaliacao;
	}

	public void setListaStatusAvalicao(List<StatusAvaliacao> listaStatusAvalicao) {
		this.listaStatusAvaliacao = listaStatusAvalicao;
	}
	
	
	
	
	
	
	

}
