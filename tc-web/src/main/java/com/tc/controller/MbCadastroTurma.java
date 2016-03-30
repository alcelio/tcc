package com.tc.controller;

import static com.tc.util.IavaliarGlobal.PAGINA_CADASTRO_TURMA;
import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static com.tc.util.IavaliarGlobal.PAGINA_INCLUIR_INSTITUICAO;
import static com.tc.util.IavaliarGlobal.PAGINA_INCLUIR_TURNOS;
import static com.tc.util.IavaliarGlobal.PAGINA_INCLUI_SERIE;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import com.tc.data.TurmaBeanDao;
import com.tc.model.Turma;

@SessionScoped
@ManagedBean
public class MbCadastroTurma implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	TurmaBeanDao dao;

	private Turma turma = new Turma();
	private String caminhoOrigem;
	private List<Turma> turmas = new ArrayList<Turma>();

	public MbCadastroTurma() {
	}

	/**
	 * Método que retorna a página para adicionar uma série
	 */
	public String adicionarSerie() {
		return PAGINA_INCLUI_SERIE;
	}

	/**
	 * Método que retorna a página para adicionar turnoss
	 */
	public String adicionarTurno() {
		return PAGINA_INCLUIR_TURNOS;
	}

	/**
	 * Método que retorna a página para adicionar instituição
	 */
	public String adicionarInstituicao() {
		return PAGINA_INCLUIR_INSTITUICAO;
	}

	public void setaCaminhoOrigem(String caminhoOrigem) {
		this.caminhoOrigem = caminhoOrigem;
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

	public String novaTurma() {
		turma = new Turma();
		return PAGINA_CADASTRO_TURMA;
	}

	public String encerraCadastro() {
		turma = new Turma();
		return PAGINA_HOME;
	}

	public void addTurma() {
		if(turma == null){
			return;
		}

		if (turma.getIdTurma() == null || turma.getIdTurma() == 0) {
			if(!isCamposValidos()){
				return;
			}
			salvar();
		} else {
			if(!isCamposValidos()){
				return;
			}
			atualizar();
		}
	}
	
	private boolean isCamposValidos(){
	
			//Valida nome da turma
			if (StringUtils.isBlank(getTurma().getDsTurma())) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(SEVERITY_INFO, "Campo [Nome da Turma] é obrigatório.", ""));
				return false;
			}
			//Valida Série
			if (getTurma().getSerie() != null && getTurma().getSerie().getIdSerie() == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(SEVERITY_INFO, "Campo [Série/Ano/Curso/Módulo] é obrigatório.", ""));
				return false;
			}
			//Valida Turno
			if (getTurma().getTurno() != null && getTurma().getTurno().getIdTurno() == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(SEVERITY_INFO, "Campo [Turno] é obrigatório.", ""));
				return false;
			}
			//Valida o campo ano letivo
			if (getTurma().getAnoLetivo() == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(SEVERITY_INFO, "Campo [Ano Letivo] é obrigatório.", ""));
				return false;
			}
	
			return true;
	}

	private void salvar() {
		dao.create(turma);
		novaTurma();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
	}

	private void atualizar() {
		dao.update(turma);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
		novaTurma();
	}

	public void deletar() {
		dao.remove(turma);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}

	// ****************************************** GETTERS E SETTERS
	// **************************************************
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Turma> getTurmas() {
		turmas = dao.listarTurmas();
		return turmas;
	}

	public String getCaminhoOrigem() {
		return caminhoOrigem;
	}

}
