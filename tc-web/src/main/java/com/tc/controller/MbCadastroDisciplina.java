package com.tc.controller;

import static com.tc.util.IavaliarGlobal.PAGINA_CADASTRO_DISCIPLINA;
import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tc.data.DisciplinaBeanDao;
import com.tc.model.Disciplina;

@SessionScoped
@ManagedBean
public class MbCadastroDisciplina implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	DisciplinaBeanDao dao;
	
	private Disciplina disciplina = new Disciplina();
	
	private String caminhoOrigem;
	
	public MbCadastroDisciplina() {
	}

	public String novaDisciplina() {
		setDisciplina(new Disciplina());
		return PAGINA_CADASTRO_DISCIPLINA;
	}

	public String encerraCadastro() {
		return PAGINA_HOME;
	}

	public void addDisciplina() {
		if (disciplina.getIdDisciplina() == null || disciplina.getIdDisciplina() == 0) {
			salvar();
		} else {
			atualizar();
		}
	}

	/**
	 * Método para incluir instância de disciplina na base de dados.
	 */
	private void salvar() {
		dao.create(disciplina);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
		novaDisciplina();
	}
	
	public String goBack(){
		if(isBlank(getCaminhoOrigem())){
			return PAGINA_HOME;
		}else{
			return caminhoOrigem;
		}
	}
	
	private void atualizar() {
		dao.update(disciplina);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(SEVERITY_INFO, "Operação realizada com sucesso", ""));
		novaDisciplina();
	}

	public void deletar() {
		dao.remove(disciplina);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(SEVERITY_INFO, "Operação realizada com sucesso", ""));
		novaDisciplina();
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Disciplina> getDisciplinas() {
		return dao.listarDisciplina();
	}

	public String getCaminhoOrigem() {
		return caminhoOrigem;
	}

	public void setaCaminhoOrigem(String caminhoOrigem) {
		this.caminhoOrigem = caminhoOrigem;
	}
}
