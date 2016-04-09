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

import com.tc.model.Avaliacao;
import com.tc.model.Disciplina;
import com.tc.model.StatusAvaliacao;
import com.tc.model.Turma;
import com.tc.model.Usuario;
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

	public AvaliacaoBeanDao() {
	}

	public void create(Avaliacao entidade) throws Exception {
		em.persist(entidade);
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

	/**
	 * Método que retorna as avaliações para o aluno, turma e disciplinas
	 * informados, parametros nulos serão desconsiderados
	 * 
	 * @param usuario
	 * @param statusAvaliacao
	 * @param disciplina
	 * @return {@link List} Avaliacao
	 * @throws Exception
	 */
	public List<Avaliacao> listarAvaliacoesAluno(Usuario usuario, StatusAvaliacao statusAvaliacao,
			Disciplina disciplina) throws Exception {

		if (usuario != null && usuario.getIdUsuario() != null
				&& (statusAvaliacao == null || statusAvaliacao.getIdStatusAvaliacao() == null)
				&& (disciplina == null || disciplina.getIdDisciplina() == null))
			return listarAvaliacoesAluno(usuario);

		if (usuario != null && usuario.getIdUsuario() != null
				&& (statusAvaliacao == null || statusAvaliacao.getIdStatusAvaliacao() == null) && disciplina != null
				&& disciplina.getIdDisciplina() != null)
			return listarAvaliacoesAlunoPorDisciplina(listarAvaliacoesAluno(usuario), disciplina);

		if (usuario.getIdUsuario() != null && (disciplina == null || disciplina.getIdDisciplina() == null)
				&& statusAvaliacao != null && statusAvaliacao.getIdStatusAvaliacao() != null)
			return listarAvaliacoesAlunoPorStatusAvalicao(listarAvaliacoesAluno(usuario), statusAvaliacao);

		if (usuario.getIdUsuario() != null && statusAvaliacao != null && statusAvaliacao.getIdStatusAvaliacao() != null
				&& disciplina != null && disciplina.getIdDisciplina() != null)
			return listarAvaliacoesAlunoPorStatusAvalicaoDisciplina(listarAvaliacoesAluno(usuario), statusAvaliacao,
					disciplina);
		return null;
	}

	/**
	 * Método que retorna as avaliações da lista informada cuja a discipla e
	 * status de avaliações sejam iguais aos parametros informados para
	 * disciplina e status
	 * 
	 * @param avaliacoes
	 * @param status
	 * @return
	 */
	private List<Avaliacao> listarAvaliacoesAlunoPorStatusAvalicaoDisciplina(List<Avaliacao> avaliacoes,
			StatusAvaliacao status, Disciplina disciplina) {
		List<Avaliacao> retorno = new ArrayList<>();

		if (avaliacoes != null && status != null) {
			for (Avaliacao avaliacao : avaliacoes) {
				if (avaliacao.getStatusAvaliacao().getIdStatusAvaliacao().equals(status.getIdStatusAvaliacao())
						&& avaliacao.getDisciplina().getIdDisciplina().equals(disciplina.getIdDisciplina()))
					retorno.add(avaliacao);
			}
			return retorno;

		}
		return null;
	}

	/**
	 * Método que retorna as avaliações da lista informada cuja a discipla é
	 * igual a disciplina informa por parametro
	 * 
	 * @param avaliacoes
	 * @param status
	 * @return
	 */
	private List<Avaliacao> listarAvaliacoesAlunoPorStatusAvalicao(List<Avaliacao> avaliacoes, StatusAvaliacao status) {
		List<Avaliacao> retorno = new ArrayList<>();

		if (avaliacoes != null && status != null) {
			for (Avaliacao avaliacao : avaliacoes) {
				if (avaliacao.getStatusAvaliacao().getIdStatusAvaliacao().equals(status.getIdStatusAvaliacao()))
					retorno.add(avaliacao);
			}
			return retorno;

		}
		return null;
	}

	/**
	 * Método que retorna as avaliações da lista informada cuja a discipla é
	 * igual a disciplina informa por parametro
	 * 
	 * @param avaliacoes
	 * @param disciplina
	 * @return
	 */
	private List<Avaliacao> listarAvaliacoesAlunoPorDisciplina(List<Avaliacao> avaliacoes, Disciplina disciplina) {
		List<Avaliacao> retorno = new ArrayList<>();

		if (avaliacoes != null && disciplina != null) {
			for (Avaliacao avaliacao : avaliacoes) {
				if (avaliacao.getDisciplina().getIdDisciplina().equals(disciplina.getIdDisciplina()))
					retorno.add(avaliacao);
			}
			return retorno;

		}
		return null;
	}

	/**
	 * Método que retorna as avalições diponíveis para um aluno
	 * 
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<Avaliacao> listarAvaliacoesAluno(Usuario usuario) throws Exception {
		final Session session = em.unwrap(Session.class);
		try {
			List<Turma> turmas = daoAlunosTurma.listarTurmasDoAluno(usuario);
			final Criteria crit = CriaCriteria.createCriteria(Avaliacao.class, session);
			crit.add(Restrictions.in("turma", turmas));
			return crit.list();

		} catch (final Exception e) {
			throw new Exception("Erro ao carregar Avaliações do aluno no banco de dados.", e);
		}
	}

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

	/**
	 * Busca todas a avalição das turms onde o aluno passado como parametro está
	 * presente;
	 */
	public List<Avaliacao> buscaAvaliacoesTurmaAluno(Usuario aluno) {
		List<Avaliacao> listaAvaliacoes = new ArrayList<Avaliacao>();
		// Query query = em.createQuery("SELECT e FROM Professor e");
		// return (Collection<Professor>) query.getResultList();
		StringBuilder sql = new StringBuilder();

		// select * from Avaliacao, AlunosTurma where Avaliacao.idTurma =
		// AlunosTurma.idTurma and AlunosTurma.idUsuario=1 ;

		// sql.append("SELECT * FROM AVALIACAO, ALUNOSTURMA ");
		// sql.append("WHERE ");
		// sql.append("AVALIACAO.IDTURMA = ALUNOSTURMA.IDTURMA ");
		// sql.append("AND ");
		// sql.append("ALUNOSTURMA.IDUSUARIO = "+
		// aluno.getIdUsuario().toString()+";");
		//
		sql.append("from Avaliacao, AlunosTurma ");
		sql.append("where ");
		sql.append("Avaliacao.idTurma = AlunosTurma.idTurma ");
		sql.append("and ");
		sql.append("AlunosTurma.idUsuario = " + aluno.getIdUsuario().toString());
		// TOSO Fazer este metodo funcionar
		// Query query = (Query) em.createQuery(sql.toString());
		System.out.println();
		return listaAvaliacoes;
	}

}
