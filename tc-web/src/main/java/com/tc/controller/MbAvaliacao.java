package com.tc.controller;

import static com.tc.util.IavaliarGlobal.PAGINA_CRIAR_AVALIACAO;
import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static com.tc.util.IntegerUtil.ONE;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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

	private List<Questao> questoesAvaliacao;

	private String caminhoOrigem;

	/**
	 * Método resposável por retornar a página que deve ser exibida ao
	 * selecionar valtar.
	 * 
	 * @return
	 */
	public String goBack() {
		if (isBlank(getCaminhoOrigem())) {
			return PAGINA_HOME;
		} else {
			return caminhoOrigem;
		}
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
		if (questoesAvaliacao == null) {
			questoesAvaliacao = new ArrayList<Questao>();
		}
		// Somente adiciona se questão não estiver presente na lista
		if (!questoesAvaliacao.contains(questao)) {
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
		return PAGINA_CRIAR_AVALIACAO;
	}

	public String encerraCadastro() {
		return PAGINA_HOME;
	}

	public void addAvaliacao() {
		if(avaliacao == null){
			return;
		}
		
		if (avaliacao.getIdAvaliacao() == null || avaliacao.getIdAvaliacao() == 0) {
			// Adicionar datas
			avaliacao.setDataInclusao(new Date());
			// Verificar datas
			if (avaliacao.getDataAvaliacao().after(avaliacao.getDataFimAvaliacao())) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Data de encerramento da publicação da avaliação deve ser maior que a data de início.", ""));
				return;
			}
			//Valida turma
			if(avaliacao.getTurma()!= null && avaliacao.getTurma().getIdTurma() == null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_INFO,
						"O campo [Turma] é obrigatório.", ""));
				return;
			}
			//Valida disciplina
			if(avaliacao.getDisciplina() != null && avaliacao.getDisciplina().getIdDisciplina() == null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_INFO,
						"O campo [Disciplina] é obrigatório.", ""));
				return;
			}
			//Valida o campo título da avaliação.
			if(isBlank(avaliacao.getTituloAvaliacao())){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_INFO,
						"O campo [Título] é obrigatório.", ""));
				return;
			}
			
			//Valida o campo Orientações da avaliação.
			if(isBlank(avaliacao.getOrientacoes())){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_INFO,
						"O campo [Orientações] é obrigatório.", ""));
				return;
			}
			
			//Valida se exsiste alguma questão
			if( questoesAvaliacao != null && questoesAvaliacao.size() < ONE){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_INFO,
						"Adicione uma questão para prosseguir.", ""));
				return;
			}
			
			salvar();
		}else{
			atualizar();
		}
		
	}

	private void atualizar(){
		dao.update(avaliacao);
	}
	
	private void salvar() {
		try {
			avaliacao.setStatusAvaliacao(daoStatus.buscaStatusAvaliacaoPorId(3));
			avaliacao.setRespondida(false);
			dao.create(avaliacao);
		} catch (Exception e1) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR,
					"Erro ao persistir a avaliação no banco de dados. Ação cancelada!", e1.getMessage()));
		}
		try {
			for (Questao questao : questoesAvaliacao) {
				QuestoesAvaliacaoPK id = new QuestoesAvaliacaoPK();
				QuestoesAvaliacao q = new QuestoesAvaliacao();
				id.setIdAvaliacao(avaliacao.getIdAvaliacao());
				id.setIdQuestao(questao.getIdQuestao());
				q.setId(id);
				try {
					daoQuestoesAvaliacao.create(q);
				} catch (Exception e) {
					dao.remove(avaliacao);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR,
							"Erro ao gravar questões da avaliação. Ação cancelada", e.getMessage()));
				}
			}
			novaAvalicao();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_INFO, "Gravação efetuada com sucesso", ""));

		} catch (Exception e) {
			dao.remove(avaliacao);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR,
					"Erro ao gravar avaliação no banco de dados. Ação cancelada!", e.getMessage()));
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

	public String getCaminhoOrigem() {
		return caminhoOrigem;
	}

	public void setCaminhoOrigem(String caminhoOrigem) {
		this.caminhoOrigem = caminhoOrigem;
	}

}
