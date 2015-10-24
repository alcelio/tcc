package com.tc.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tc.data.TurnoBeanDao;
import com.tc.model.Turno;

@ManagedBean
@SessionScoped
public class MbCadastroTurno implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	TurnoBeanDao dao;
	
	private Turno turno = new Turno();
	private List<Turno> turnos;
	private String caminhoOrigem;
	
	public MbCadastroTurno() {
	}

	
	public String limpTurno() {
        turno = new Turno();
        return "incluirturno.jsf";
    }

	public void setaCaminhoOrigem(String caminhoOrigem) {
		this.caminhoOrigem = caminhoOrigem;
	}
	
	public String goBack(){
		return caminhoOrigem;
	}
	
    public void addTurno() {
        if (turno.getIdTurno() == null || turno.getIdTurno() == 0) {
            salvar();
        } else {
            updateTurno();
        }
    }

    private void salvar() {
        dao.create(turno);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
        limpTurno();
    }

    private void updateTurno() {
        dao.update(turno);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }
    
    public void deleteTurno(){
       dao.remove(turno);
       FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluido com sucesso", ""));
    }
	
//	GETTES AND SETTERS
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public List<Turno> getTurnos() {
		turnos = dao.listarTurnos();
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}


	public String getCaminhoOrigem() {
		return caminhoOrigem;
	}


	public void setCaminhoOrigem(String caminhoOrigem) {
		this.caminhoOrigem = caminhoOrigem;
	}
	
	

}
