package com.tc.controller;

import static com.tc.util.IavaliarGlobal.PAGINA_CADASTRO_TOPICOS_ESTUDOS;
import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static com.tc.util.IavaliarGlobal.PAGINA_INCLUIR_DISCIPLINA;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import com.tc.beans.BeanCabecalhoQuestoes;
import com.tc.data.DisciplinaBeanDao;
import com.tc.data.TopicoEstudoBeanDao;
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
	
	private String caminhoOrigem;
	
	private BeanCabecalhoQuestoes beanCabecalho =  new BeanCabecalhoQuestoes();
	
	 public MbCadastroTopicoEstudo() {
	}
	 
	/**
	 * Método que abre a págiande inclusão de uma nova disciplina 
	 * @return
	 */
	public String incluiNovaDisciplina(){
		return PAGINA_INCLUIR_DISCIPLINA;
	}
	 
	/**
	 * Método para criar um novo tópico de estudo. 
	 * @return
	 */
	public String novoTopico() {
		topico = new TopicoEstudo();
		//Carrega os tópicos por disciplina ou geral.
		if(getBeanCabecalho() != null && getBeanCabecalho().getDisciplina().getIdDisciplina() != null){
			try {
				beanCabecalho.setDisciplina(daoDsiciplina.buscaPorId(beanCabecalho.getDisciplina().getIdDisciplina()));
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(SEVERITY_ERROR, "Erro ao listar tópicos de estudo.", ""));
			}
		}
		return PAGINA_CADASTRO_TOPICOS_ESTUDOS;
	}

	/**
	 * Método que retorna a pagina inicial da aplicação 
	 * @return
	 */
	public String encerraCadastro() {
		return PAGINA_HOME;
	}
	/**
	 * Método para adicionar um novo tópico ao banco de dados
	 */
	public void addTopico() {
		if(topico.getDisciplina().getIdDisciplina() == null && StringUtils.isEmpty(getCaminhoOrigem())){
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
	 * Método para incluir instância de usuario corrente na base de dados.
	 */
	private void salvar() {
		try{
			//Quanto solicitado cadastro via outra página busca a disciplina informada
			if(!isEmpty(getCaminhoOrigem())){
				getTopico().setDisciplina(daoDsiciplina.buscaPorId(beanCabecalho.getDisciplina().getIdDisciplina()));
				getBeanCabecalho().setTopicoEstudo(topico);
			}
			dao.create(topico);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_INFO, "Gravação efetuada com sucesso.", ""));
			novoTopico();
		}catch (Exception e){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_ERROR, "Erro grave durante a gravação das informações", ""));
			
		}
		
	}
	public String goBack(){
		if(isBlank(getCaminhoOrigem())){
			return PAGINA_HOME;
		}else{
			return caminhoOrigem;
		}
	}
	/**
	 * Método para informar o caminho para onde deverá retornar a aplicação ao selecionar o botao voltar.
	 * @param caminhoOrigem
	 */
	public void setaCaminhoOrigem(String caminhoOrigem) {
		this.caminhoOrigem = caminhoOrigem;
	}
	/**
	 * Método para atualizar um tópico de estudo no banco de dados.
	 */
	private void atualizar() {
		dao.update(topico);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
		novoTopico();
	}

	/**
	 * Método para deletar o tópico de estudo selecionado.
	 */
	public void deletar() {
		dao.remove(topico);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
		novoTopico();
	}
	/**
	 * Método que retorna uma lista com todos os tópicos de estudos cadastrados para uma disciplina.
	 * @return
	 * @throws Exception 
	 */
	public List<TopicoEstudo> getTopicosEstudo() throws Exception {
		return dao.listarTopicoEstudoDisciplina(beanCabecalho.getDisciplina());
	}
	
	/**
	 * Método que retorna uma lista com todos os tópicos de estudos cadastrados para uma disciplina.
	 * @return
	 * @throws Exception 
	 */
	public List<TopicoEstudo> getTopicosEstudoGerais() throws Exception {
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
	public BeanCabecalhoQuestoes getBeanCabecalho() {
		return beanCabecalho;
	}

	public void setBeanCabecalho(BeanCabecalhoQuestoes beanCabecalho) {
		this.beanCabecalho = beanCabecalho;
	}
	
	}
