package com.tc.data;

import static com.tc.util.IavaliarGlobal.STATUS_AVALIACAO_AGUARDANDO_INICIO;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tc.model.AlunosTurma;
import com.tc.model.Avaliacao;
import com.tc.model.Avaliacoes;
import com.tc.model.Disciplina;
import com.tc.model.Professor;
import com.tc.model.QuestoesAvaliacao;
import com.tc.model.Respostas;
import com.tc.model.Usuario;
import com.tc.model.PK.AvalicoesPK;
import com.tc.model.PK.RespostaPK;
import com.tc.util.CriaCriteria;

/**
 * Session Bean implementation class EnderecoBeanDao
 */

@Stateless
@LocalBean
@Remote
public class AvaliacoesBeanDao implements Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	@EJB
	AvaliacaoBeanDao daoAvaliacao;
	@EJB
	UsuarioBeanDao daoUsuario;
	@EJB
	AlunosTurmaBeanDao daoAlunosTurma;
	@EJB
	RespostasBeanDao daoRespostas;

	public AvaliacoesBeanDao() {
	}

	/**
	 * Método para criar uma nova instancia de avaliações.
	 * 
	 * @param entidade
	 * @throws Exception
	 */
	public void create(Avaliacoes entidade) throws Exception {
		try {
			em.persist(entidade);
		} catch (Exception e) {
			throw new Exception("Erro ao persistir entidade avaliações.");
		}
	}

	/**
	 * Método para atualizar a avaliações já gravados no banco
	 * 
	 * @param entidade
	 * @throws Exception
	 */
	public void update(Avaliacoes entidade) throws Exception {
		try {
			em.merge(entidade);
		} catch (Exception e) {
			throw new Exception("Erro ao persistir entidade avaliações.");
		}
	}

	public Avaliacoes buscarAvaliacoesPorPK(Avaliacoes avaliacoes) throws Exception {
		final Session session = em.unwrap(Session.class);
		try {
			final Criteria crit = CriaCriteria.createCriteria(Avaliacoes.class, session);

			crit.add(Restrictions.eq("id", avaliacoes.getId()));

			return (Avaliacoes) crit.uniqueResult();
		} catch (Exception e) {
			throw new Exception(
					"Erro ao buscar  a entidade avaliações [" + avaliacoes.getId().toString() + "]. " + e.getMessage());
		}
	}

	/**
	 * Método que retorna uma lista de avaliações para alunos.
	 * 
	 * @param usuarioLogado
	 * @param status
	 * @param disciplina
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Avaliacoes> listarAvaliacoesAluno(Usuario usuarioLogado, String status, Disciplina disciplina)
			throws Exception {
		final Session session = em.unwrap(Session.class);
		try {
			final Criteria crit = CriaCriteria.createCriteria(Avaliacoes.class, session);

			if (usuarioLogado != null && usuarioLogado.getIdUsuario() != null)
				crit.add(Restrictions.eq("aluno", usuarioLogado));

			if (!isBlank(status))
				crit.add(Restrictions.eq("statusAvaliacao", status));

			if (disciplina != null && disciplina.getIdDisciplina() != null)
				return buscaAvaliacoesPorDisciplina(crit.list(), disciplina);

			return crit.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao obeter lista de avaliações." + e.getMessage());
		}

	}

	/**
	 * Método que retorna uma lista de avaliacoes que contenham para a
	 * disciplina informada como aprametro
	 * 
	 * @param listaAvaliacoes
	 * @param disciplina
	 * @return
	 */
	private List<Avaliacoes> buscaAvaliacoesPorDisciplina(List<Avaliacoes> listaAvaliacoes, Disciplina disciplina) {
		List<Avaliacoes> listaRetorno = new ArrayList<Avaliacoes>();

		if (listaAvaliacoes != null && listaAvaliacoes.size() > 0) {
			for (Avaliacoes avaliacoes : listaAvaliacoes) {
				if (avaliacoes.getAvaliacao().getDisciplina().getIdDisciplina().equals(disciplina.getIdDisciplina()))
					listaRetorno.add(avaliacoes);
			}
		}
		return listaRetorno;
	}

	@SuppressWarnings("unchecked")
	public List<Avaliacoes> listarAvaliacoesProfessor(Usuario usuarioLogado, String status, Disciplina disciplina)
			throws Exception {
		final Session session = em.unwrap(Session.class);
		try {
			final Criteria crit = CriaCriteria.createCriteria(Avaliacoes.class, session);

			if (usuarioLogado != null && usuarioLogado.getIdUsuario() != null)
				crit.add(Restrictions.eq("professor", usuarioLogado));

			if (!isBlank(status))
				crit.add(Restrictions.eq("statusAvaliacao", status));

			if (disciplina != null && disciplina.getIdDisciplina() != null)
				return buscaAvaliacoesPorDisciplina(crit.list(), disciplina);

			return crit.list();
		} catch (Exception e) {
			throw new Exception("Erro ao obter lista de avaliações." + e.getMessage());
		}

	}

	/**
	 * Método que inclui uma avaliação na lista de avaliaçõe para o usuário
	 * informado
	 * 
	 * @param entidade
	 * @throws Exception
	 */
	public void incluiAvaliacaoParaAluno(Usuario usuario, Avaliacao entidade) throws Exception {

		Avaliacoes avaliacoes = null;

		try {
			AvalicoesPK pk = new AvalicoesPK();
			pk.setIdAluno(usuario.getIdUsuario());
			pk.setIdAvaliacao(entidade.getIdAvaliacao());

			Avaliacao avaliacao = daoAvaliacao.buscaPorId(entidade.getIdAvaliacao());

			avaliacoes = new Avaliacoes();
			avaliacoes.setId(pk);
			avaliacoes.setAluno(daoUsuario.buscaUsuarioPorId(usuario.getIdUsuario()));
			avaliacoes.setProfessor(daoUsuario.buscaUsuarioPorId(avaliacao.getProfessor().getIdUsuario()));
			avaliacoes.setAvaliacao(avaliacao);
			avaliacoes.setStatusAvaliacao(STATUS_AVALIACAO_AGUARDANDO_INICIO);
			create(avaliacoes);
			criaRespostasParaAvaliacao(usuario, avaliacoes);
		} catch (Exception e) {
			em.remove(avaliacoes);
			throw new Exception("Erro incluir avaliação para o aluno [" + usuario.getNome() + "]", e);
		}

	}

	/**
	 * Método que cria as entradas para respostas das questões da avaliação para
	 * cada aluno
	 * 
	 * @param usuario
	 * @param avalicoes
	 * @throws Exception
	 */
	private void criaRespostasParaAvaliacao(Usuario usuario, Avaliacoes avalicoes) throws Exception {
		try {
			List<Respostas> listaRespostas = new ArrayList<Respostas>();
			Respostas resp;
			RespostaPK pk;
			List<QuestoesAvaliacao> listaQuestoes = avalicoes.getAvaliacao().getQuestoesAvaliacao();
			for (QuestoesAvaliacao questaoAvaliacao : listaQuestoes) {
				resp = new Respostas();
				pk = new RespostaPK();
				pk.setIdAluno(usuario.getIdUsuario());
				pk.setIdAvaliacao(questaoAvaliacao.getId().getIdAvaliacao());
				pk.setIdQuestao(questaoAvaliacao.getId().getIdQuestao());
				resp.setId(pk);
				resp.setQuestao(questaoAvaliacao.getQuestao());
				resp.setAvaliacao(questaoAvaliacao.getAvaliacao());
				resp.setAluno(usuario);
//				em.persist(resp);
				listaRespostas.add(resp);
				
			}
			Avaliacao avaliacao = daoAvaliacao.buscaPorId(avalicoes.getAvaliacao().getIdAvaliacao());
			avaliacao.setRespostas(listaRespostas);
			daoAvaliacao.update(avaliacao);
		} catch (Exception e) {
			throw new Exception("Erro ao criar entrada para respospostas da avaliação.", e);
		}
	}

	/**
	 * Método que inclui uma avaliação para cada aluno que participa da turma
	 * para a qual a avliação foi criada
	 * 
	 * @param entidade
	 * @throws Exception
	 */
	public void incluiAvalicoesParaTurma(Usuario usuario, Avaliacao entidade) throws Exception {
		List<AlunosTurma> alunosTurmas;
		Professor professor;
		// Obetem a lista de alunos
		try {
			alunosTurmas = daoAlunosTurma.listarAlunosPorTurma(entidade.getTurma());
		} catch (Exception e) {
			throw new Exception("Erro ao obter lista de alunos da turma");
		}
		// Busca a isntancia de professor;
		try {
			professor = (Professor) daoUsuario.buscaUsuarioPorId(entidade.getProfessor().getIdUsuario());
		} catch (Exception e) {
			throw new Exception("Erro ao obter buscar professor [" + entidade.getProfessor().getIdUsuario() + "]");
		}
		// Grava as avalições no banco conforme lista de alunos que precisarão
		// responder
		try {

			Avaliacoes avaliacoes;

			for (AlunosTurma alunosTurma : alunosTurmas) {
				// Avaliacao avaliacao =
				// daoAvaliacao.buscaPorId(entidade.getIdAvaliacao());
				avaliacoes = new Avaliacoes();
				AvalicoesPK pk = new AvalicoesPK();
				pk.setIdAluno(alunosTurma.getAluno().getIdUsuario());
				pk.setIdAvaliacao(entidade.getIdAvaliacao());
				avaliacoes.setId(pk);
				avaliacoes.setAluno(daoUsuario.buscaUsuarioPorId(alunosTurma.getAluno().getIdUsuario()));
				avaliacoes.setProfessor(professor);
				avaliacoes.setAvaliacao(entidade);
				avaliacoes.setStatusAvaliacao(STATUS_AVALIACAO_AGUARDANDO_INICIO);
				create(avaliacoes);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao salvar as avaliações para os alunos da turma ["
					+ entidade.getTurma().getDsTurma() + "]" + e.getMessage());
		}

	}

}
