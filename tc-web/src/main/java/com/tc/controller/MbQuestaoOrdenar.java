package com.tc.controller;

import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static com.tc.util.IavaliarGlobal.PAGINA_INCLUI_QUESTAO_ORDENAR;
import static com.tc.util.IavaliarGlobal.QUESTAO_DISSERTATIVA;
import static com.tc.util.IavaliarGlobal.QUESTAO_ORDENACAO;
import static com.tc.util.WebGlobals.PAGINA_INCLUIR_DISCIPLINA;
import static com.tc.util.WebGlobals.PAGINA_INCLUIR_TOPICOS_ESTUDOS;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import com.tc.beans.BeanCabecalhoQuestoes;
import com.tc.data.DisciplinaBeanDao;
import com.tc.data.QuestaoBeanDao;
import com.tc.data.TopicoEstudoBeanDao;
import com.tc.data.UsuarioBeanDao;
import com.tc.model.QuestaoOrdenar;
import com.tc.model.TopicoEstudo;

/**
 * @author alcelio
 *
 */
@SessionScoped
@ManagedBean
public class MbQuestaoOrdenar implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	QuestaoBeanDao dao;
	@EJB
	TopicoEstudoBeanDao daoTopicoEstudo;
	@EJB
	UsuarioBeanDao daoUsuario;
	@EJB
	DisciplinaBeanDao daoDisciplina;

	private QuestaoOrdenar questao = new QuestaoOrdenar();

	private BeanCabecalhoQuestoes beanCabecalhoQuestao = new BeanCabecalhoQuestoes();

	private TopicoEstudo topico = new TopicoEstudo();

	private List<TopicoEstudo> topicosEstudo;
	
	private String caminhoOrigem;

	public MbQuestaoOrdenar() {
	}

	/**
	 * Método que indica para que página deve seguir o programa
	 * @return
	 */
	public String goBack(){
		if(isBlank(getCaminhoOrigem())){
			return PAGINA_HOME;
		}else{
			return getCaminhoOrigem();
		}
	}
	/**
	 * Método que recebe o tipo de qustão e atualiza o objeto questão com a
	 * informação
	 * 
	 * @param tipoQuestao
	 */
	public void informaTipoQuestao(String tipoQuestao) {
		getQuestao().setTipoQuestao(tipoQuestao);
	}

	/**
	 * Método que recebe uma string e compara se esta é um tipo de questão e
	 * retorna se este tipo é igual ao informado na questão
	 * 
	 * @param tipoQuestao
	 */
	public boolean qualTipoQuestao(String tipoQuestao) {
		boolean tem = false;
		if (getQuestao().getTipoQuestao() != null && getQuestao().getTipoQuestao() == (tipoQuestao)) {
			tem = true;
		}
		return tem;
	}

	/**
	 * Método que retorna somente um grau de dificuldade para a questao.
	 * 
	 * @param grauDificuldade
	 */
	public boolean testaGrauDificuldade(String grauDificuldade) {
		boolean ret = false;
		if (getBeanCabecalhoQuestao().getGrauDificuldade() != null
				&& getBeanCabecalhoQuestao().getGrauDificuldade() == (grauDificuldade)
				&& !StringUtils.isEmpty(grauDificuldade)) {
			ret = true;
		}
		return ret;
	}

	/**
	 * Metodo para inclusão de uma nova disciplina
	 */
	public String incluiNovaDisciplina() {
		return PAGINA_INCLUIR_DISCIPLINA;
	}

	/**
	 * Método para inclusão de um novo tópico de estudo
	 * 
	 * @return
	 */
	public String incluiTopicoDeEstudo() {
		// Somente permite a inclusão se houver uma disciplina informada
		if (getBeanCabecalhoQuestao() != null && getBeanCabecalhoQuestao().getDisciplina() != null
				&& getBeanCabecalhoQuestao().getDisciplina().getIdDisciplina() != null) {
			return PAGINA_INCLUIR_TOPICOS_ESTUDOS;
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_INFO, "Selecione uma disciplina para prosseguir!", ""));
			return null;
		}
	}

	/**
	 * Método que controla a visibilidade do formulário do grau de dificuldade
	 * de uma questão
	 * 
	 * @return
	 */
	public boolean exibirFormGrauDificuldade() {
		return true;
	}

	/**
	 * Método que seta o grau de dificuldade de uma questão
	 * 
	 * @param grauDificuldade
	 */
	public void setGrauDificuldade(String grauDificuldadade) {
		this.beanCabecalhoQuestao.setGrauDificuldade(grauDificuldadade);
	}

	/**
	 * Método que cria uma nova página em branco para inclusão de uma nova
	 * questão
	 */
	public String novaQuestao() {
		setQuestao(new QuestaoOrdenar());
		setTopico(new TopicoEstudo());
		topicosEstudo.clear();
		return PAGINA_INCLUI_QUESTAO_ORDENAR;
	}

	/**
	 * Método encerra a edição ou visualização da questão e abre a página
	 * inicial do sistema.
	 */
	public String encerraCadastro() {
		this.questao = null;
		return PAGINA_HOME;
	}

	/**
	 * Método que faz verificações e envia dados para o salvamento
	 */
	public void addQuestao() {
		if (getQuestao().getIdQuestao() == null || questao.getIdQuestao() == 0) {
			// Exige que seja informada uma disciplina
			if (getBeanCabecalhoQuestao() == null || getBeanCabecalhoQuestao().getDisciplina() == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(SEVERITY_INFO, "É obrigatório informar o campo [Discplina].", ""));
				return;
			}
			// Exige que seja informado um tópico de estudo
			if (getBeanCabecalhoQuestao() == null || getBeanCabecalhoQuestao().getTopicoEstudo() == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(SEVERITY_INFO, "É obrigatório informar o  campo [Tópico de Estudo].", ""));
				return;
			}

			// Exige que seja informado um grau de dificuldade para a questão
			if (isBlank(getBeanCabecalhoQuestao().getGrauDificuldade())) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_INFO,
						"É obrigatório informar o [Grau de Deficuldade] da questão.", ""));
				return;
			}
			//Não permite ordem repetida
			if(isOrdemRepetida()){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_INFO,
						"Existem opções marcadas com a mesma ordem de sequência.", ""));
				return;
			}

			salvar();
		} else {
			atualizar();
		}

	}

	private boolean isOrdemRepetida() {
		//Garante que não exista soente uma questão com ordem 1
		if (getQuestao().getRespOrPrimeira() == (getQuestao().getRespOrSegunda())
				|| getQuestao().getRespOrPrimeira() == (getQuestao().getRespOrTerceira())
				|| getQuestao().getRespOrPrimeira() == (getQuestao().getRespOrQuarta())
				|| getQuestao().getRespOrPrimeira() == (getQuestao().getRespOrQuinta())) {
			return true;
		}
		//Garante que não exista somente uma questão com ordem 2
		if (getQuestao().getRespOrSegunda() == (getQuestao().getRespOrPrimeira())
				|| getQuestao().getRespOrSegunda() == (getQuestao().getRespOrTerceira())
				|| getQuestao().getRespOrSegunda() == (getQuestao().getRespOrQuarta())
				|| getQuestao().getRespOrSegunda() == (getQuestao().getRespOrQuinta())) {
			return true;
		}
		
		//Garante que não exista somente uma questão com ordem 3
		if (getQuestao().getRespOrTerceira()== (getQuestao().getRespOrSegunda())
				|| getQuestao().getRespOrTerceira() == (getQuestao().getRespOrPrimeira())
				|| getQuestao().getRespOrTerceira()== (getQuestao().getRespOrQuarta())
				|| getQuestao().getRespOrTerceira() == (getQuestao().getRespOrQuinta())) {
			return true;
		}
		//Garante que não exista somente uma questão com ordem 4
		if (getQuestao().getRespOrQuarta() == (getQuestao().getRespOrSegunda())
				|| getQuestao().getRespOrQuarta() == (getQuestao().getRespOrTerceira())
				|| getQuestao().getRespOrQuarta() == (getQuestao().getRespOrPrimeira())
				|| getQuestao().getRespOrQuarta() == (getQuestao().getRespOrQuinta())) {
			return true;
		}
		
		//Garante que não exista somente uma questão com ordem 5
		if (getQuestao().getRespOrQuinta() == (getQuestao().getRespOrSegunda())
				|| getQuestao().getRespOrQuinta() == (getQuestao().getRespOrTerceira())
				|| getQuestao().getRespOrQuinta() == (getQuestao().getRespOrQuarta())
				|| getQuestao().getRespOrQuinta() == (getQuestao().getRespOrPrimeira())) {
			return true;
		}
		

		return false;
	}

	/**
	 * Método que atualiza os tópicos de estudos de uma disciplina previamente
	 * cadastrada
	 */
	public void carregaDadosTopicoEstudo() {
		if (this.beanCabecalhoQuestao.getDisciplina() != null) {
			try {
				topicosEstudo = daoTopicoEstudo.listarTopicoEstudoDisciplina(beanCabecalhoQuestao.getDisciplina());
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR,
						"Erro ao gerar listagem de tópicos de estudo para a disciplina.", e.getMessage()));
			}
		}
	}

	/**
	 * Método que controla se uma questão será pública
	 * 
	 * @param isPublica
	 */
	public void setIsQuestaoPublica(boolean isPublica) {
		questao.setPublica(isPublica);
	}

	/**
	 * Método usado para controlar o grau de dificuldade de uma questão(Gerencia
	 * de acordo com o botão pressionado na tela)
	 * 
	 * @param grauDificuldade
	 */
	public void setaGrauDificuldade(String grauDificuldade) {
		getBeanCabecalhoQuestao().setGrauDificuldade(grauDificuldade);
	}

	/**
	 * Método busca uma instancia e atualiza o tópico de estudos
	 * 
	 * @throws Exception
	 */
	public void selecionaTopicoEstudo() throws Exception {
		getBeanCabecalhoQuestao().setTopicoEstudo(daoTopicoEstudo.buscaPorId(getTopico().getIdTopicoEstudo()));
	}

	/**
	 * Método que persiste os dados da questão
	 */
	private void salvar() {
		getBeanCabecalhoQuestao().setTopicoEstudo(getTopico());
		try {
			getQuestao().setDataInclusao(new Date());
			getQuestao().setDisciplina(getBeanCabecalhoQuestao().getDisciplina());
			getQuestao().setTopicoEstudo(getBeanCabecalhoQuestao().getTopicoEstudo());
			getQuestao().setTipoQuestao(getBeanCabecalhoQuestao().getTipoQuestao());
			getQuestao().setGrauDificuldade(getBeanCabecalhoQuestao().getGrauDificuldade());
			getQuestao().setTipoQuestao(QUESTAO_ORDENACAO);
			getQuestao().setProfessor(MbLoginController.getUsuarioLogado());
			dao.create(getQuestao());
			setBeanCabecalhoQuestao(new BeanCabecalhoQuestoes());
			novaQuestao();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_ERROR, "Erro durante gravação das informações.", e.getMessage()));
		}

	}

	/**
	 * Método que efetua edição dos dados de uma questão no banco de dados
	 */
	private void atualizar() {
		getBeanCabecalhoQuestao().setTopicoEstudo(getTopico());
		try {
			getQuestao().setDisciplina(getBeanCabecalhoQuestao().getDisciplina());
			getQuestao().setTopicoEstudo(getBeanCabecalhoQuestao().getTopicoEstudo());
			getQuestao().setTipoQuestao(getBeanCabecalhoQuestao().getTipoQuestao());
			getQuestao().setGrauDificuldade(getBeanCabecalhoQuestao().getGrauDificuldade());
			getQuestao().setTipoQuestao(QUESTAO_DISSERTATIVA);
			dao.update(getQuestao());
			novaQuestao();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_ERROR, "Erro durante gravação das informações.", e.getMessage()));
		}
	}

	/**
	 * Método que exclui a questão em uso do banco de dados
	 */
	public void deletar() {
		try {
			dao.remove(questao);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SEVERITY_ERROR, "Erro durante exclusão das inforamações.", e.getMessage()));
		}
	}

	// ****************GETTERS E SETTERS *********************************
	public QuestaoOrdenar getQuestao() {
		return questao;
	}

	public void setQuestao(QuestaoOrdenar questao) {
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
	

}
