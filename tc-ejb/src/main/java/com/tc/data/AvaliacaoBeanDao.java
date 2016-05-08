package com.tc.data;

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
import com.tc.model.QuestoesAvaliacao;
import com.tc.model.Respostas;
import com.tc.model.Turma;
import com.tc.model.Usuario;
import com.tc.model.PK.QuestoesAvaliacaoPK;
import com.tc.model.PK.RespostaPK;
import com.tc.util.CriaCriteria;

/**
 * Session Bean implementation class EnderecoBeanDao
 */

@Stateless
@LocalBean
@Remote
public class AvaliacaoBeanDao implements Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	@EJB
	AlunosTurmaBeanDao daoAlunosTurma;
	@EJB
	AvaliacoesBeanDao daoAvaliacoes;

	public AvaliacaoBeanDao() {
	}

	public void create(Usuario usuario, Avaliacao entidade) throws Exception {
		Avaliacao avaliacao = new Avaliacao();
		avaliacao = entidade;
		List<QuestoesAvaliacao> listaQuestoes = entidade.getQuestoesAvaliacao();
		avaliacao.setQuestoesAvaliacao(null);
		em.persist(avaliacao);

		try {
			QuestoesAvaliacaoPK id;
			for (QuestoesAvaliacao questoesAvaliacao : listaQuestoes) {
				// Cria o id para cada nova questao
				id = new QuestoesAvaliacaoPK();
				id.setIdQuestao(questoesAvaliacao.getQuestao().getIdQuestao());
				id.setIdAvaliacao(avaliacao.getIdAvaliacao());
				questoesAvaliacao.setId(id);
			}
			avaliacao.setQuestoesAvaliacao(listaQuestoes);

			em.merge(avaliacao);
			// inclui uma avaliação cada aluno na turma
			daoAvaliacoes.incluiAvalicoesParaTurma(usuario, entidade);
		} catch (Exception e) {
			em.remove(avaliacao);
			e.printStackTrace();
			throw new Exception("Erro ao persistir dados da avaliação." + e.getMessage());
		}

		try {
			List<Respostas> listaRespostas = new ArrayList<Respostas>();
			List<AlunosTurma> alunosTurmas = daoAlunosTurma.listarAlunosPorTurma(entidade.getTurma());
			Respostas resp;
			RespostaPK pk;
			for (AlunosTurma alunosTurma : alunosTurmas) {
				for (QuestoesAvaliacao questaoAvaliacao : avaliacao.getQuestoesAvaliacao()) {
					resp = new Respostas();
					pk = new RespostaPK();
					pk.setIdAluno(alunosTurma.getAluno().getIdUsuario());
					pk.setIdAvaliacao(questaoAvaliacao.getId().getIdAvaliacao());
					pk.setIdQuestao(questaoAvaliacao.getId().getIdQuestao());
					resp.setId(pk);
					resp.setQuestao(questaoAvaliacao.getQuestao());
					resp.setAvaliacao(questaoAvaliacao.getAvaliacao());
					resp.setAluno(alunosTurma.getAluno());
					listaRespostas.add(resp);
				}
			}
			avaliacao.setRespostas(listaRespostas);
			em.merge(avaliacao);
		} catch (Exception e) {
			em.remove(avaliacao);
			e.printStackTrace();
			throw new Exception("Erro ao persistir dados da avaliação." + e.getMessage());
		}

	}

	public void update(Avaliacao entidade) {
		em.merge(entidade);
	}

	public void remove(Avaliacao entidade) {
		em.remove(em.getReference(Avaliacao.class, entidade.getIdAvaliacao()));
	}

	public List<Avaliacao> listarAvaliacoes() {
		return em.createNamedQuery("Avaliacao.findAll", Avaliacao.class).getResultList();
	}

	// /**
	// * Método que retorna as avaliações para o aluno, turma e disciplinas
	// * informados, parametros nulos serão desconsiderados
	// *
	// * @param usuario
	// * @param statusAvaliacao
	// * @param disciplina
	// * @return {@link List} Avaliacao
	// * @throws Exception
	// */
	// public List<Avaliacao> listarAvaliacoesAluno(Usuario usuario, String
	// statusAvaliacao,
	// Disciplina disciplina) throws Exception {
	//
	// if (usuario != null && usuario.getIdUsuario() != null
	// && (statusAvaliacao == null || statusAvaliacao == null)
	// && (disciplina == null || disciplina.getIdDisciplina() == null))
	// return listarAvaliacoesAluno(usuario);
	//
	// if (usuario != null && usuario.getIdUsuario() != null
	// && (statusAvaliacao == null || statusAvaliacao == null) && disciplina !=
	// null
	// && disciplina.getIdDisciplina() != null)
	// return listarAvaliacoesAlunoPorDisciplina(listarAvaliacoesAluno(usuario),
	// disciplina);
	//
	// if (usuario.getIdUsuario() != null && (disciplina == null ||
	// disciplina.getIdDisciplina() == null)
	// && statusAvaliacao != null && statusAvaliacao != null)
	// return
	// listarAvaliacoesAlunoPorStatusAvalicao(listarAvaliacoesAluno(usuario),
	// statusAvaliacao);
	//
	// if (usuario.getIdUsuario() != null && statusAvaliacao != null &&
	// statusAvaliacao != null
	// && disciplina != null && disciplina.getIdDisciplina() != null)
	// return
	// listarAvaliacoesAlunoPorStatusAvalicaoDisciplina(listarAvaliacoesAluno(usuario),
	// statusAvaliacao,
	// disciplina);
	// return null;
	// }

	// /**
	// * Método que retorna as avaliações da lista informada cuja a discipla e
	// * status de avaliações sejam iguais aos parametros informados para
	// * disciplina e status
	// *
	// * @param avaliacoes
	// * @param status
	// * @return
	// */
	// private List<Avaliacao>
	// listarAvaliacoesAlunoPorStatusAvalicaoDisciplina(List<Avaliacao>
	// avaliacoes,
	// String status, Disciplina disciplina) {
	// List<Avaliacao> retorno = new ArrayList<>();
	//
	// if (avaliacoes != null && status != null) {
	// for (Avaliacao avaliacao : avaliacoes) {
	// if (avaliacao.getStatusAvaliacao().equals(status)
	// &&
	// avaliacao.getDisciplina().getIdDisciplina().equals(disciplina.getIdDisciplina()))
	// retorno.add(avaliacao);
	// }
	// return retorno;
	//
	// }
	// return null;
	// }

	// /**
	// * Método que retorna as avaliações da lista informada cujas a discipla,
	// * status de avaliações e turma sejam iguais aos parametros informados
	// para
	// * disciplina e status e turma
	// *
	// * @param avaliacoes
	// * @param status
	// * @return
	// */
	// private List<Avaliacao>
	// listarAvaliacoesProfessorPorStatusAvalicaoDisciplinaTurma(List<Avaliacao>
	// avaliacoes,
	// String status, Disciplina disciplina, Turma turma) {
	// List<Avaliacao> retorno = new ArrayList<>();
	//
	// if (avaliacoes != null && status != null) {
	// for (Avaliacao avaliacao : avaliacoes) {
	// if (avaliacao.getStatusAvaliacao().equals(status)
	// &&
	// avaliacao.getDisciplina().getIdDisciplina().equals(disciplina.getIdDisciplina())
	// && avaliacao.getTurma().getIdTurma().equals(turma.getIdTurma()))
	// retorno.add(avaliacao);
	// }
	// return retorno;
	//
	// }
	// return null;
	// }

	// /**
	// * Método que retorna as avaliações da lista informada cuja a discipla é
	// * igual a disciplina informa por parametro
	// *
	// * @param avaliacoes
	// * @param status
	// * @return
	// */
	// private List<Avaliacao>
	// listarAvaliacoesAlunoPorStatusAvalicao(List<Avaliacao> avaliacoes, String
	// status) {
	// List<Avaliacao> retorno = new ArrayList<>();
	//
	// if (avaliacoes != null && status != null) {
	// for (Avaliacao avaliacao : avaliacoes) {
	// if (avaliacao.getStatusAvaliacao().equals(status))
	// retorno.add(avaliacao);
	// }
	// return retorno;
	//
	// }
	// return null;
	// }

	// /**
	// * Método que retorna as avaliações da lista informada cuja a discipla é
	// * igual a disciplina informa por parametro
	// *
	// * @param avaliacoes
	// * @param disciplina
	// * @return
	// */
	// private List<Avaliacao>
	// listarAvaliacoesAlunoPorDisciplina(List<Avaliacao> avaliacoes, Disciplina
	// disciplina) {
	// List<Avaliacao> retorno = new ArrayList<>();
	//
	// if (avaliacoes != null && disciplina != null) {
	// for (Avaliacao avaliacao : avaliacoes) {
	// if
	// (avaliacao.getDisciplina().getIdDisciplina().equals(disciplina.getIdDisciplina()))
	// retorno.add(avaliacao);
	// }
	// return retorno;
	//
	// }
	// return null;
	// }

	// /**
	// * Método que retorna as avaliações da lista informada cuja a discipla e
	// status sejam
	// * igual a disciplina e status informados por parametro
	// *
	// * @param avaliacoes
	// * @param disciplina
	// * @return
	// */
	// private List<Avaliacao>
	// listarAvaliacoesProfessorPorDisciplinaStatus(List<Avaliacao> avaliacoes,
	// Disciplina disciplina, String statusAvaliacao) {
	// List<Avaliacao> retorno = new ArrayList<>();
	//
	// if (avaliacoes != null && disciplina != null) {
	// for (Avaliacao avaliacao : avaliacoes) {
	// if
	// (avaliacao.getDisciplina().getIdDisciplina().equals(disciplina.getIdDisciplina())
	// && avaliacao.getStatusAvaliacao().equals(statusAvaliacao))
	// retorno.add(avaliacao);
	// }
	// return retorno;
	// }
	// return null;
	// }

	// /**
	// * Método que retorna as avaliações da lista informada cuja a discipla é
	// * igual a disciplina informa por parametro
	// *
	// * @param avaliacoes
	// * @param disciplina
	// * @return
	// */
	// private List<Avaliacao>
	// listarAvaliacoesProfessorPorDisciplina(List<Avaliacao> avaliacoes,
	// Disciplina disciplina) {
	// List<Avaliacao> retorno = new ArrayList<>();
	//
	// if (avaliacoes != null && disciplina != null) {
	// for (Avaliacao avaliacao : avaliacoes) {
	// if
	// (avaliacao.getDisciplina().getIdDisciplina().equals(disciplina.getIdDisciplina()))
	// retorno.add(avaliacao);
	// }
	// return retorno;
	// }
	// return null;
	// }

	/**
	 * * Método que retorna as avaliações da lista informada cuja a turma é
	 * igual a turma informa por parametro
	 * 
	 * @param turma
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Avaliacao> listarAvaliacoesPorTurma(Turma turma) throws Exception {
		final Session session = em.unwrap(Session.class);
		try {
			final Criteria crit = CriaCriteria.createCriteria(Avaliacao.class, session);
			crit.add(Restrictions.eq("turma", turma));
			return crit.list();
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar dados da tabela.", e);
		}
	}

	// /**
	// * Método que retorna as avalições diponíveis para um aluno
	// *
	// * @param usuario
	// * @return
	// * @throws Exception
	// */
	// @SuppressWarnings("unchecked")
	// private List<Avaliacao> listarAvaliacoesAluno(Usuario usuario) throws
	// Exception {
	// final Session session = em.unwrap(Session.class);
	// try {
	// List<Turma> turmas = daoAlunosTurma.listarTurmasDoAluno(usuario);
	// final Criteria crit = CriaCriteria.createCriteria(Avaliacao.class,
	// session);
	// crit.add(Restrictions.in("turma", turmas));
	// return crit.list();
	//
	// } catch (final Exception e) {
	// throw new Exception("Erro ao carregar Avaliações do aluno no banco de
	// dados.", e);
	// }
	// }

	public Avaliacao buscaPorId(Integer idAvaliacao) throws Exception {
		final Session session = em.unwrap(Session.class);
		Avaliacao disc = null;

		try {
			final Criteria crit = CriaCriteria.createCriteria(Avaliacao.class, session);
			crit.add(Restrictions.eq("idAvaliacao", idAvaliacao));

			disc = (Avaliacao) crit.uniqueResult();

		} catch (final Exception e) {
			throw new Exception("Erro ao carregar Avaliacao no banco de dados.", e);
		}
		return disc;
	}

	//
	// /**
	// * Método que retorna as avaliações para o professor, turma, status
	// * avaliação e disciplina informados, parametros nulos serão
	// desconsiderados
	// *
	// * @param usuario
	// * @param statusAvaliacao
	// * @param disciplina
	// * * @param turmas
	// * @return {@link List} Avaliacao
	// * @throws Exception
	// */
	// public List<Avaliacao> listarAvaliacoesProfessor(Usuario usuario, String
	// statusAvaliacao,
	// Disciplina disciplina, Turma turma) throws Exception {
	// // Caso someente seja informado um usuario
	// if (usuario != null && usuario.getIdUsuario() != null
	// && (statusAvaliacao == null || statusAvaliacao == null)
	// && (turma == null || turma.getIdTurma() == null)
	// && (disciplina == null || disciplina.getIdDisciplina() == null))
	// return listarAvaliacoesProfessor(usuario);
	//
	// //Quando deve filtrar somente disciplina
	// if (usuario != null && usuario.getIdUsuario() != null
	// && (statusAvaliacao == null || statusAvaliacao == null)
	// && (turma == null || turma.getIdTurma() == null)
	// && disciplina != null && disciplina.getIdDisciplina() != null)
	// return
	// listarAvaliacoesProfessorPorDisciplina(listarAvaliacoesProfessor(usuario),
	// disciplina);
	//
	// //Quando deve filtrar somente o status da avaliação
	// if (usuario.getIdUsuario() != null && (disciplina == null ||
	// disciplina.getIdDisciplina() == null)
	// && (turma == null || turma.getIdTurma() == null)
	// && statusAvaliacao != null && statusAvaliacao != null)
	// return
	// listarAvaliacoesAlunoPorStatusAvalicao(listarAvaliacoesProfessor(usuario),
	// statusAvaliacao);
	//
	// //Quando deve filtrar a turma
	// if (usuario.getIdUsuario() != null
	// && turma != null && turma.getIdTurma() != null
	// && (disciplina == null || disciplina.getIdDisciplina() == null)
	// && (statusAvaliacao == null || statusAvaliacao == null))
	// return
	// listarAvaliacoesProfessorPorTurma(listarAvaliacoesProfessor(usuario),
	// turma);
	//
	// //Quando deve filtrar disciplina e Status
	// if (usuario.getIdUsuario() != null
	// && disciplina != null && disciplina.getIdDisciplina() != null
	// && statusAvaliacao != null && statusAvaliacao != null
	// && (turma == null || turma.getIdTurma() == null))
	// return
	// listarAvaliacoesProfessorPorDisciplinaStatus(listarAvaliacoesProfessor(usuario),
	// disciplina, statusAvaliacao);
	//
	// //Quando deve filtrar disciplina e turmas
	// if (usuario.getIdUsuario() != null
	// && disciplina != null && disciplina.getIdDisciplina() != null
	// && turma != null && turma.getIdTurma() != null
	// && (statusAvaliacao == null || statusAvaliacao == null))
	// return
	// listarAvaliacoesProfessorPorDisciplinaTurma(listarAvaliacoesProfessor(usuario),
	// disciplina, turma);
	//
	// //Quando deve filtrar status e turmas
	// if (usuario.getIdUsuario() != null
	// && statusAvaliacao != null && statusAvaliacao != null
	// && turma != null && turma.getIdTurma() != null
	// && (disciplina == null || disciplina.getIdDisciplina() == null))
	// return
	// listarAvaliacoesProfessorPorStatusTurma(listarAvaliacoesProfessor(usuario),
	// statusAvaliacao, turma);
	//
	//
	//
	// //Quando deve filtar todos os parametros
	// if (usuario.getIdUsuario() != null && statusAvaliacao != null &&
	// statusAvaliacao != null
	// && disciplina != null && disciplina.getIdDisciplina() != null
	// && turma != null && turma.getIdTurma() != null)
	// return
	// listarAvaliacoesProfessorPorStatusAvalicaoDisciplinaTurma(listarAvaliacoesProfessor(usuario),
	// statusAvaliacao,
	// disciplina, turma);
	// return null;
	// }

	// /**
	// * Método que retorna as avaliações da lista informada cujo o Status e
	// turma sejam
	// * iguais a Status e turma informados por parâmetro
	// *
	// * @param avaliacoes
	// * @param StatusAvaliacao
	// * * @param turma
	// * @return
	// */
	// private List<Avaliacao>
	// listarAvaliacoesProfessorPorStatusTurma(List<Avaliacao> avaliacoes,
	// String statusAvaliacao, Turma turma) {
	// List<Avaliacao> retorno = new ArrayList<>();
	//
	// if (avaliacoes != null && statusAvaliacao != null && turma != null) {
	// for (Avaliacao avaliacao : avaliacoes) {
	// if (avaliacao.getStatusAvaliacao().equals(statusAvaliacao)
	// && avaliacao.getTurma().getIdTurma().equals(turma.getIdTurma()))
	// retorno.add(avaliacao);
	// }
	// return retorno;
	// }
	// return null;
	// }

	// /**
	// * Método que retorna as avaliações da lista informada cuja a discipla e
	// turma sejam
	// * iguais a disciplina e turma informado por parâmetro
	// *
	// * @param avaliacoes
	// * @param disciplina
	// * * @param turma
	// * @return
	// */
	// private List<Avaliacao>
	// listarAvaliacoesProfessorPorDisciplinaTurma(List<Avaliacao> avaliacoes,
	// Disciplina disciplina, Turma turma) {
	// List<Avaliacao> retorno = new ArrayList<>();
	//
	// if (avaliacoes != null && disciplina != null && turma != null) {
	// for (Avaliacao avaliacao : avaliacoes) {
	// if
	// (avaliacao.getDisciplina().getIdDisciplina().equals(disciplina.getIdDisciplina())
	// && avaliacao.getTurma().getIdTurma().equals(turma.getIdTurma()))
	// retorno.add(avaliacao);
	// }
	// return retorno;
	// }
	// return null;
	// }

}
