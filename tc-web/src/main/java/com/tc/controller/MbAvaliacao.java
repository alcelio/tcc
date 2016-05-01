package com.tc.controller;

import static com.tc.controller.MbLoginController.getUsuarioLogado;
import static com.tc.util.IavaliarGlobal.PAGINA_CRIAR_AVALIACAO;
import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static com.tc.util.IntegerUtil.ONE;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.Serializable;
import java.util.Date;

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

	private QuestoesAvaliacao questaoAvaliacao;

	private Questao questao;

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

	public void adicionaQuestaoListaAvaliacao() {
		// Cria a nova questaoAvaliacao
		if (getQuestao() != null && getQuestao().getIdQuestao() != null) {
			QuestoesAvaliacao q = new QuestoesAvaliacao();
			q.setAvaliacao(getAvaliacao());
			q.setQuestao(getQuestao());

			setQuestaoAvaliacao(q);

		}

		// Somente adiciona se questão não estiver presente na lista
		if (!getAvaliacao().getQuestoesAvaliacao().contains(getQuestaoAvaliacao())) {
			getAvaliacao().getQuestoesAvaliacao().add(getQuestaoAvaliacao());
		}

	}

	public void excluiQuestaoListaAvaliacao() {
		if (getAvaliacao().getQuestoesAvaliacao().size() > 0) {
			getAvaliacao().getQuestoesAvaliacao().remove(getQuestaoAvaliacao());
		}
	}

	public String novaAvalicao() {
		setAvaliacao(new Avaliacao());
		return PAGINA_CRIAR_AVALIACAO;
	}

	public String encerraCadastro() {
		return PAGINA_HOME;
	}

	public void addAvaliacao() {
		if (getAvaliacao() == null) {
			return;
		}

		if (getAvaliacao().getIdAvaliacao() == null || getAvaliacao().getIdAvaliacao() == 0) {
			// Adicionar datas
			getAvaliacao().setDataInclusao(new Date());
			// Verificar datas
			if (getAvaliacao().getDataAvaliacao().after(getAvaliacao().getDataFimAvaliacao())) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Data de encerramento da publicação da avaliação deve ser maior que a data de início.", ""));
				return;
			}
			// Valida turma
			if (getAvaliacao().getTurma() != null && getAvaliacao().getTurma().getIdTurma() == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(SEVERITY_INFO, "O campo [Turma] é obrigatório.", ""));
				return;
			}
			// Valida disciplina
			if (getAvaliacao().getDisciplina() != null && getAvaliacao().getDisciplina().getIdDisciplina() == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(SEVERITY_INFO, "O campo [Disciplina] é obrigatório.", ""));
				return;
			}
			// Valida o campo título da avaliação.
			if (isBlank(getAvaliacao().getTituloAvaliacao())) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(SEVERITY_INFO, "O campo [Título] é obrigatório.", ""));
				return;
			}

			// Valida o campo Orientações da avaliação.
			if (isBlank(getAvaliacao().getOrientacoes())) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(SEVERITY_INFO, "O campo [Orientações] é obrigatório.", ""));
				return;
			}

			// Valida se exsiste alguma questão
			if (getAvaliacao().getQuestoesAvaliacao() != null && getAvaliacao().getQuestoesAvaliacao().size() < ONE) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(SEVERITY_INFO, "Adicione uma questão para prosseguir.", ""));
				return;
			}

			salvar();
		} else {
			atualizar();
		}

	}

	private void atualizar() {
		dao.update(getAvaliacao());
	}

	private void salvar() {
		try {
			getAvaliacao().setRespondida(false);
			getAvaliacao().setProfessor(MbLoginController.getUsuarioLogado());
			dao.create(getUsuarioLogado(),getAvaliacao());

			novaAvalicao();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
		} catch (Exception e1) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR,
					"Erro ao persistir a avaliação no banco de dados. Ação cancelada!", e1.getMessage()));
		}

	}

	public void deletar() {
		dao.remove(getAvaliacao());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public QuestoesAvaliacao getQuestaoAvaliacao() {
		return questaoAvaliacao;
	}

	public void setQuestaoAvaliacao(QuestoesAvaliacao questaoAvaliacao) {
		this.questaoAvaliacao = questaoAvaliacao;
	}

	public String getCaminhoOrigem() {
		return caminhoOrigem;
	}

	public void setCaminhoOrigem(String caminhoOrigem) {
		this.caminhoOrigem = caminhoOrigem;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

}
