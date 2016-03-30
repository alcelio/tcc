package com.tc.controller;

import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static com.tc.util.IavaliarGlobal.PAGINA_INCLUIR_TURNOS;
import static org.apache.commons.lang3.StringUtils.isBlank;

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

	
	public String novoTurno() {
        turno = new Turno();
        return PAGINA_INCLUIR_TURNOS;
    }

	public String encerraCadastro(){ 
		setTurno(new Turno());
		return PAGINA_HOME;
	}

	/**
	 * Método que orienta qual pagina deverá ser retornada
	 * @return
	 */
	public String goBack(){
		if(isBlank(getCaminhoOrigem())){
			return PAGINA_HOME;
		}
		return getCaminhoOrigem();
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
        novoTurno();
    }

    private void updateTurno() {
        dao.update(turno);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
        novoTurno();
    }
    
    public void deletar(){
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


	public void setaCaminhoOrigem(String caminhoOrigem) {
		this.caminhoOrigem = caminhoOrigem;
	}
	


}
