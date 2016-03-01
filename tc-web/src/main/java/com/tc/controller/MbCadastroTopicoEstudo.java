package com.tc.controller;

import static com.tc.util.IavaliarGlobal.PAGINA_CADASTRO_TOPICOS_ESTUDOS;
import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static com.tc.util.IavaliarGlobal.PAGINA_INCLUI_QUESTAO_DISSERTATIVA;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tc.beans.BeanTopicoEstudo;
import com.tc.data.DisciplinaBeanDao;
import com.tc.data.TopicoEstudoBeanDao;
import com.tc.model.Disciplina;
import com.tc.model.TopicoEstudo;

@SessionScoped
@ManagedBean
public class MbCadastroTopicoEstudo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	TopicoEstudoBeanDao dao;
	@EJB
	DisciplinaBeanDao daoDsiciplina;
	
	private TopicoEstudo topico = new TopicoEstudo();
	private BeanTopicoEstudo beanTopico;
	private String caminhoOrigem;
	private Disciplina disciplina;
	
	
	public MbCadastroTopicoEstudo() {
	}

	public String novoTopico() {
		topico = new TopicoEstudo();
		return PAGINA_CADASTRO_TOPICOS_ESTUDOS;
	}

	public String encerraCadastro() {
		return PAGINA_HOME;
	}

	public void addTopico() {
		
		if(topico.getDisciplina().getIdDisciplina() == null){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo selecionar disciplina é obrigatório!", ""));
			return;
		}
		
		if (topico.getIdTopicoEstudo() == null || topico.getIdTopicoEstudo() == 0) {
			
			salvar();
		} else {
			atualizar();
		}
	}

	/**
	 * Método para incluir instancia de usuario corrente na base de dados.
	 */
	private void salvar() {
		dao.create(topico);
		if(getCaminhoOrigem().equals(PAGINA_INCLUI_QUESTAO_DISSERTATIVA)){
			beanTopico = new BeanTopicoEstudo();
			beanTopico.setDisciplina(topico.getDisciplina());
			beanTopico.setTopicoEstudo(topico);
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
		novoTopico();
	}
	
	public String goBack(){
		return caminhoOrigem;
	}
	
	public void setaCaminhoOrigem(String caminhoOrigem) {
		this.caminhoOrigem = caminhoOrigem;
	}
	
	private void atualizar() {
		dao.update(topico);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}

	public void deletar() {
		dao.remove(topico);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}
	public List<TopicoEstudo> getTopicoEstudo() {
		return dao.listarTopicoEstudo();
	}

	public TopicoEstudo getTopico() {
		return topico;
	}

	public void setTopico(TopicoEstudo topico) {
		this.topico = topico;
	}

	public String getCaminhoOrigem() {
		return caminhoOrigem;
	}

	public void setCaminhoOrigem(String caminhoOrigem) {
		this.caminhoOrigem = caminhoOrigem;
	}

	public BeanTopicoEstudo getBeanTopico() {
		return beanTopico;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	
	

}
