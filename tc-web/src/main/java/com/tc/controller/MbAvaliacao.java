package com.tc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tc.beans.BeanTopicoEstudo;
import com.tc.data.AvaliacaoBeanDao;
import com.tc.data.QuestoesAvaliacaoBeanDao;
import com.tc.data.StatuAvaliacaoDao;
import com.tc.data.UsuarioBeanDao;
import com.tc.model.Avaliacao;
import com.tc.model.Questao;
import com.tc.model.QuestoesAvaliacao;
import com.tc.model.Usuario;
import com.tc.model.PK.QuestoesAvaliacaoPK;

@SessionScoped
@ManagedBean
public class MbAvaliacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	AvaliacaoBeanDao dao;
	@EJB
	UsuarioBeanDao daoUsuario;
	@EJB
	QuestoesAvaliacaoBeanDao daoQuestoesAvaliacao;
	@EJB
	StatuAvaliacaoDao daoStatus;

	private Avaliacao avaliacao = new Avaliacao();

	private Questao questao;
	public BeanTopicoEstudo beanTopico;
	private List<Questao> questoesAvaliacao;

	public MbAvaliacao() {
	}

	public void setaProfessorAvalicao(String login) {
		Usuario usuario = new Usuario();
		try {
			usuario = daoUsuario.buscaUsuarioPorLogin(login);
			avaliacao.setProfessor(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void adicionaQuestaoListaAvaliacao() {
		if(questoesAvaliacao == null){
			questoesAvaliacao =  new ArrayList<Questao>();
		}
		//Somente adiciona se questão não estiver presente na lista
		if(!questoesAvaliacao.contains(questao)){
			questoesAvaliacao.add(questao);	
		}
			
	}

	public void excluiQuestaoListaAvaliacao() {
		if (questoesAvaliacao.size() > 0) {
			questoesAvaliacao.remove(questao);
		}
	}
	
	public void setaUsuarioQuestao(String login) {
		Usuario usuario = new Usuario();
		try {
			usuario = daoUsuario.buscaUsuarioPorLogin(login);
			avaliacao.setProfessor(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String novaAvalicao() {
		avaliacao = new Avaliacao();
		questoesAvaliacao = new ArrayList<Questao>();
		return "professor/criaravaliacao.jsf";
	}

	public String encerraCadastro() {
		return "restrito/home.jsf";
	}

	public void addAvaliacao() {
		try {
			if (avaliacao.getIdAvaliacao() == null || avaliacao.getIdAvaliacao() == 0) {
				// Adicionar datas
				avaliacao.setDataInclusao(new Date());
				// Verificar datas
				if (avaliacao.getDataAvaliacao().after(avaliacao.getDataFimAvaliacao())) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Data de encerramento da publicação da avaliação deve ser maior que a data de início.",""));
					return;
				}
				// Adiciona um status inicial para a avaliação
				avaliacao.setStatusAvaliacao(daoStatus.buscaStatusAvaliacaoPorId(3));
				salvar();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro durante a gravação da avaliação, tente novamente.", ""));
		}
	}

	private void salvar() {
		dao.create(avaliacao);
		try{
			for (Questao questao : questoesAvaliacao) {
				QuestoesAvaliacaoPK id = new QuestoesAvaliacaoPK();
				QuestoesAvaliacao q = new QuestoesAvaliacao();
				id.setIdAvaliacao(avaliacao.getIdAvaliacao());
				id.setIdQuestao(questao.getIdQuestao());
				q.setId(id);
				try{
					daoQuestoesAvaliacao.create(q);
				}
				catch (Exception e){
					dao.remove(avaliacao);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao realizar operação. Ação cancelada", ""));
					return;
				}
			}
			novaAvalicao();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
			
		}catch ( Exception e){
			dao.remove(avaliacao);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao realizar operação. Ação cancelada", ""));
		}
		
		
	}

	public void deletar() {
		dao.remove(avaliacao);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public List<Questao> getQuestoesAvaliacao() {
		return questoesAvaliacao;
	}

	public void setQuestoesAvaliacao(List<Questao> questoesAvaliacao) {
		this.questoesAvaliacao = questoesAvaliacao;
	}

}
