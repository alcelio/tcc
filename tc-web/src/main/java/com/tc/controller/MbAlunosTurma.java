package com.tc.controller;

import static com.tc.controller.MbLoginController.getUsuarioLogado;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tc.data.AlunosTurmaBeanDao;
import com.tc.data.AvaliacaoBeanDao;
import com.tc.data.AvaliacoesBeanDao;
import com.tc.data.RespostasBeanDao;
import com.tc.data.TurmaBeanDao;
import com.tc.data.UsuarioBeanDao;
import com.tc.model.AlunosTurma;
import com.tc.model.Avaliacao;
import com.tc.model.Turma;
import com.tc.model.PK.AlunosTurmaPK;

@SessionScoped
@ManagedBean
public class MbAlunosTurma implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private AlunosTurmaBeanDao dao;
	@EJB
	private UsuarioBeanDao daoUsuario;
	@EJB
	private TurmaBeanDao daoTurma;
	@EJB
	private AvaliacaoBeanDao daoAvaliacao;
	@EJB
	private AvaliacoesBeanDao daoAvaliacoes;
	@EJB
	private RespostasBeanDao daoRespostas;

	private List<AlunosTurma> turmasAluno;
	private Turma turma;

	@PostConstruct
	public void init() {

		if (turma == null) {
			turma = new Turma();
		}
		if (turmasAluno == null) {
			turmasAluno = new ArrayList<AlunosTurma>();
		}

		try {
			turmasAluno = dao.listarAlunosTurmaPorAluno(MbLoginController.getUsuarioLogado());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro ao obter turmas do aluno, reinicie o processo.", ""));
		}
	}

	public void addAlunoTurma() throws Exception {

		AlunosTurma ta = new AlunosTurma();
		AlunosTurmaPK pk = new AlunosTurmaPK();
		pk.setIdTurma(turma.getIdTurma());
		pk.setIdUsuario(MbLoginController.getUsuarioLogado().getIdUsuario());

		if (dao.isExistePk(pk)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Aluno já está cadastrado nesta turma.", ""));
			return;
		}

		// busca todas as avaliações existentes para esta turma;
		List<Avaliacao> avalicoes = daoAvaliacao.listarAvaliacoesPorTurma(getTurma());
		if (avalicoes != null && avalicoes.size() > 0) {
			for (Avaliacao avaliacao : avalicoes) {
				if (avaliacao.getDataFimAvaliacao().after(new Date())) {
					daoAvaliacoes.incluiAvaliacaoParaAluno(getUsuarioLogado(), avaliacao);
				}
			}
		}

		ta.setId(pk);
		ta.setAluno(MbLoginController.getUsuarioLogado());
		ta.setTurma(turma);

		dao.create(ta);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
	}

	public List<AlunosTurma> getTurmasAluno() {
		try {
			turmasAluno = dao.listarAlunosTurmaPorAluno(MbLoginController.getUsuarioLogado());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return turmasAluno;
	}

	public void setTurmasAluno(List<AlunosTurma> turmasAluno) {
		this.turmasAluno = turmasAluno;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

}
