package com.tc.controller;

import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static com.tc.util.IavaliarGlobal.PAGINA_INCLUI_QUESTAO_DISSERTATIVA;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tc.beans.BeanCabecalhoQuestoes;
import com.tc.data.DisciplinaBeanDao;
import com.tc.data.QuestaoBeanDao;
import com.tc.data.TopicoEstudoBeanDao;
import com.tc.data.UsuarioBeanDao;
import com.tc.model.QuestaoDissertativa;
import com.tc.model.TopicoEstudo;
import com.tc.model.Usuario;

@SessionScoped
@ManagedBean
public class MbQuestaoDissertativa implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	QuestaoBeanDao dao;
	@EJB
	TopicoEstudoBeanDao daoTopicoEstudo;
	@EJB
	UsuarioBeanDao daoUsuario;
	@EJB
	DisciplinaBeanDao daoDisciplina;

	private QuestaoDissertativa questao = new QuestaoDissertativa();

	private List<TopicoEstudo> topicosEstudo;

	private BeanCabecalhoQuestoes beanCabecalhoQuestao = new BeanCabecalhoQuestoes();

	public MbQuestaoDissertativa() {
	}

	public void informaTipoQuestao(String tipoQuestao) {
		getQuestao().setTipoQuestao(tipoQuestao);
	}

	public boolean qualTipoQuestao(String tipoQuestao) {
		boolean tem = false;
		if (getQuestao().getTipoQuestao() != null && getQuestao().getTipoQuestao().equals(tipoQuestao)) {
			tem = true;
		}
		return tem;
	}

	public void setaUsuarioQuestao(String login) {
		Usuario usuario = new Usuario();
		try {
			usuario = daoUsuario.buscaUsuarioPorLogin(login);
			getQuestao().setProfessor(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean testaGrauDificuldade(String grauDificuldade) {
		boolean is = false;
		if (getBeanCabecalhoQuestao() != null && getBeanCabecalhoQuestao().getGrauDificuldade().equals(grauDificuldade)) {
			is = true;
		}
		return is;
	}

	public boolean exibirFormGrauDificuldade() {
		return true;
	}

	public String novaQuestao() {
		setQuestao(new QuestaoDissertativa());
		return PAGINA_INCLUI_QUESTAO_DISSERTATIVA;
	}

	public String encerraCadastro() {
		return PAGINA_HOME;
	}

	public void addQuestao() {
		if (getQuestao().getIdQuestao() == null || questao.getIdQuestao() == 0) {
			salvar();
		} else {
			atualizar();
		}

	}

	public void setGrauDificuldade(String grauDificuldadade) {
		this.beanCabecalhoQuestao.setGrauDificuldade(grauDificuldadade);
	}
	
	public void carregaDadosTopicoEstudo() {

		if (this.beanCabecalhoQuestao.getDisciplina() != null) {
			try {
				topicosEstudo = daoTopicoEstudo.listarTopicoEstudoDisciplina(beanCabecalhoQuestao.getDisciplina());
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Erro ao gerar listagens de tópicos de estudo desta discsiplina", ""));
			}
		}

	}

	public void setIsQuestaoPublica(boolean isPublica) {
		getQuestao().setPublica(isPublica);
	}

	private void salvar() {

		if (getBeanCabecalhoQuestao() == null || getBeanCabecalhoQuestao().getDisciplina() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "É obrigatório informar o campo [Discplina].", ""));
			return;
		}
		if (getBeanCabecalhoQuestao() == null || getBeanCabecalhoQuestao().getTopicoEstudo() == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"É obrigatório informar o  campo [Tópco de Estudo].", ""));
			return;
		}

		if (isBlank(getBeanCabecalhoQuestao().getGrauDificuldade())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"É obrigatório informar o [Grau de Deficuldade] da questão.", ""));
			return;
		}

		getQuestao().setDataInclusao(new Date());

		try {
			getQuestao().setDisciplina(getBeanCabecalhoQuestao().getDisciplina());
			getQuestao().setTopicoEstudo(getBeanCabecalhoQuestao().getTopicoEstudo());
			getQuestao().setTipoQuestao(getBeanCabecalhoQuestao().getTipoQuestao());
			getQuestao().setGrauDificuldade(getBeanCabecalhoQuestao().getGrauDificuldade());
		} catch (Exception e) {
			e.printStackTrace();
		}

		dao.create(getQuestao());
		setBeanCabecalhoQuestao(new BeanCabecalhoQuestoes());
		setQuestao(new QuestaoDissertativa());

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));

	}

	private void atualizar() {
		dao.update(getQuestao());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}

	public void deletar() {
		dao.remove(getQuestao());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}

	// ****************GETTERS E SETTERS *********************************

	public QuestaoDissertativa getQuestao() {
		return questao;
	}

	public void setQuestao(QuestaoDissertativa questao) {
		this.questao = questao;
	}

	public List<TopicoEstudo> getTopicosEstudo() {
		return topicosEstudo;
	}

	public void setTopicosEstudo(List<TopicoEstudo> topicosEstudo) {
		this.topicosEstudo = topicosEstudo;
	}

	public BeanCabecalhoQuestoes getBeanCabecalhoQuestao() {
		return beanCabecalhoQuestao;
	}

	public void setBeanCabecalhoQuestao(BeanCabecalhoQuestoes beanCabecalhoQuestao) {
		this.beanCabecalhoQuestao = beanCabecalhoQuestao;
	}
	

}
