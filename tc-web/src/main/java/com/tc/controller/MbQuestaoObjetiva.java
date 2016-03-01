package com.tc.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tc.beans.BeanTopicoEstudo;
import com.tc.data.DisciplinaBeanDao;
import com.tc.data.QuestaoBeanDao;
import com.tc.data.TopicoEstudoBeanDao;
import com.tc.data.UsuarioBeanDao;
import com.tc.model.Disciplina;
import com.tc.model.QuestaoObjetiva;
import com.tc.model.TopicoEstudo;
import com.tc.model.Usuario;

@SessionScoped
@ManagedBean
public class MbQuestaoObjetiva implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	QuestaoBeanDao dao;
	@EJB
	TopicoEstudoBeanDao daoTopicoEstudo;
	@EJB
	UsuarioBeanDao daoUsuario;
	@EJB
	DisciplinaBeanDao daoDisciplina;

	private QuestaoObjetiva questao = new QuestaoObjetiva();

	private List<TopicoEstudo> topicosEstudo;

	private String grauDificuldade ="";
	private TopicoEstudo topicoEstudo = new TopicoEstudo();
	private Disciplina disciplina = new Disciplina();
	private Integer codTopicoEstudo;
	private Integer codDisciplina;

	public BeanTopicoEstudo beanTopico;
	
	public MbQuestaoObjetiva() {
	}

	public void trocaValorRespostaObjetiva(String opcao){
			switch (opcao) {
		case "A":{
			if(questao.isRespObjOpcaoA()){
				questao.setRespObjOpcaoA(false);
			}else{
				questao.setRespObjOpcaoA(true);
			}
			break;
		}
		case "B":{
			if(questao.isRespObjOpcaoB()){
				questao.setRespObjOpcaoB(false);
			}else{
				questao.setRespObjOpcaoB(true);
			}
			break;
		}	
		case "C":{
			if(questao.isRespObjOpcaoC()){
				questao.setRespObjOpcaoC(false);
			}else{
				questao.setRespObjOpcaoC(true);
			}
			break;
		}
		case "D":{
			if(questao.isRespObjOpcaoD()){
				questao.setRespObjOpcaoD(false);
			}else{
				questao.setRespObjOpcaoD(true);
			}
			break;
		}
		case "E":{
			if(questao.isRespObjOpcaoE()){
				questao.setRespObjOpcaoE(false);
			}else{
				questao.setRespObjOpcaoE(true);
			}
			break;
		}

		default:
			break;
		}
	}
	
	public boolean testaRespostaIsTrue(String opcao){
		if(questao == null){
			return false;
		}
		switch (opcao) {
		case "A": return questao.isRespObjOpcaoA();
		case "B": return questao.isRespObjOpcaoB();
		case "C": return questao.isRespObjOpcaoC();
		case "D": return questao.isRespObjOpcaoD();
		case "E": return questao.isRespObjOpcaoE();
		default:return false;
		}
	}
	
	public void setaBeanTopicoEstudo(BeanTopicoEstudo bean){
		beanTopico = new BeanTopicoEstudo();
		if(bean == null){
			return;
		}
		this.codDisciplina = bean.getDisciplina().getIdDisciplina();
		this.codTopicoEstudo = bean.getTopicoEstudo().getIdTopicoEstudo();
		
	}
	public void informaTipoQuestao(String tipoQuestao) {
		questao.setTipoQuestao(tipoQuestao);
	}

	public boolean qualTipoQuestao(String tipoQuestao) {
		boolean tem = false;
		if (questao.getTipoQuestao() != null && questao.getTipoQuestao().equals(tipoQuestao)) {
			tem = true;
		}
		return tem;
	}

	public void setaUsuarioQuestao(String login) {
		Usuario usuario = new Usuario();
		try {
			usuario = daoUsuario.buscaUsuarioPorLogin(login);
			questao.setProfessor(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setaGrauDificuldade(String grauDificuldade) {
		this.setGrauDificuldade(grauDificuldade);
	}

	public boolean testaGrauDificuldade(String grauDificuldade) {
		boolean is = false;
		if (this.getGrauDificuldade().equals(grauDificuldade)) {
			is = true;
		}
		return is;
	}

	public boolean exibirFormGrauDificuldade() {
		return true;
	}

	public String novaQuestao() {
		questao = new QuestaoObjetiva();
		return "professor/incluirquestaodissertativa.jsf";
	}

	public String encerraCadastro() {
		return "restrito/home.jsf";
	}

	public void addQuestao() {
		
		if (questao.getIdQuestao() == null || questao.getIdQuestao() == 0) {
			salvar();
		} else {
			atualizar();
		}

	}

	public void carregaDadosTopicoEstudo() {

		if (this.getDisciplina().getIdDisciplina() != null) {
			try {
				topicoEstudo = new TopicoEstudo();
				topicosEstudo = daoTopicoEstudo.listarTopicoEstudoDisciplina(disciplina);
				codDisciplina = disciplina.getIdDisciplina();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Erro ao gerar listagens de tópicos de estudo desta discsiplina", ""));
			}
		}

	}

	public void setaCodTopicoEstudo() {
		this.codTopicoEstudo = topicoEstudo.getIdTopicoEstudo();
	}

	public void setIsQuestaoPublica(boolean isPublica) {
		questao.setPublica(isPublica);
	}

	private void salvar() {

		if (getCodDisciplina() == null || getCodDisciplina() == 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "É obrigatório informar o campo [Discplina].", ""));
			return;
		}
		if (getCodTopicoEstudo() == null || getCodTopicoEstudo() == 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"É obrigatório informar o  campo [Tópco de Estudo].", ""));
			return;
		}

		if (getGrauDificuldade() == "" || getGrauDificuldade().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"É obrigatório informar o [Grau de Deficuldade] da questão.", ""));
			return;
		}
		
		if(!questao.isRespObjOpcaoA() && !questao.isRespObjOpcaoB() && !questao.isRespObjOpcaoC() && !questao.isRespObjOpcaoD() && !questao.isRespObjOpcaoE() ){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"É obrigatório informar ao menos uma opção de resposta correta.", ""));
			return;
		}

		questao.setDataInclusao(new Date());

		try {
			questao.setDisciplina(daoDisciplina.buscaPorId(getCodDisciplina()));
			questao.setTopicoEstudo(daoTopicoEstudo.buscaPorId(getCodTopicoEstudo()));
			questao.setTipoQuestao("OBJETIVA");
			questao.setGrauDificuldade(getGrauDificuldade());
		} catch (Exception e) {
			e.printStackTrace();
		}

		dao.create(questao);
		
		topicoEstudo = new TopicoEstudo();
		disciplina = new Disciplina();
		grauDificuldade = "";
		codDisciplina = 0;
		codTopicoEstudo = 0;
		
		questao = new QuestaoObjetiva();

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));

	}

	private void atualizar() {
		dao.update(questao);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}

	public void deletar() {
		dao.remove(questao);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}

	// ****************************************** GETTERS E SETTERS
	// **************************************************

	public QuestaoObjetiva getQuestao() {
		return questao;
	}

	public void setQuestao(QuestaoObjetiva questao) {
		this.questao = questao;
	}

	public List<TopicoEstudo> getTopicosEstudo() {
		return topicosEstudo;
	}

	public void setTopicosEstudo(List<TopicoEstudo> topicosEstudo) {
		this.topicosEstudo = topicosEstudo;
	}

	public String getGrauDificuldade() {
		return grauDificuldade;
	}

	public void setGrauDificuldade(String grauDificuldadade) {
		this.grauDificuldade = grauDificuldadade;
	}

	public TopicoEstudo getTopicoEstudo() {
		return topicoEstudo;
	}

	public void setTopicoEstudo(TopicoEstudo topicoEstudo) {
		this.topicoEstudo = topicoEstudo;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Integer getCodTopicoEstudo() {
		return codTopicoEstudo;
	}

	public void setCodTopicoEstudo(Integer codTopicoEstudo) {
		this.codTopicoEstudo = codTopicoEstudo;
	}

	public Integer getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(Integer codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

}
