package com.tc.data;

import static com.tc.util.IavaliarGlobal.STATUS_AVALIACAO_AGUARDANDO_INICIO;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.Serializable;
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
import com.tc.model.Usuario;
import com.tc.model.PK.AvalicoesPK;
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

	public AvaliacoesBeanDao() {
	}

	public void create(Avaliacoes entidade) throws Exception {
		try {
			em.persist(entidade);
		} catch (Exception e) {
			throw new Exception("Erro ao persistir entidade avaliações.");
		}
	}

	public void update(Avaliacoes entidade) throws Exception {
		try {
			em.merge(entidade);
		} catch (Exception e) {
			throw new Exception("Erro ao persistir entidade avaliações.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Avaliacoes> listarAvaliacoesAluno(Usuario usuarioLogado, String status, Disciplina disciplina)
			throws Exception {
		final Session session = em.unwrap(Session.class);
		try {
			final Criteria crit = CriaCriteria.createCriteria(Avaliacoes.class, session);

			crit.add(Restrictions.eq("aluno", usuarioLogado));

			if (!isBlank(status))
				crit.add(Restrictions.eq("statusAvaliacao", status));

			if (disciplina != null && disciplina.getIdDisciplina() != null)
				crit.add(Restrictions.eq("avaliacao.disciplina", disciplina));

			return crit.list();
		} catch (Exception e) {
			throw new Exception("Erro ao obeter lista de avaliações." + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	public List<Avaliacoes> listarAvaliacoesProfessor(Usuario usuarioLogado, String status, Disciplina disciplina)
			throws Exception {
		final Session session = em.unwrap(Session.class);
		try {
			final Criteria crit = CriaCriteria.createCriteria(Avaliacoes.class, session);

			crit.add(Restrictions.eq("professor", usuarioLogado));

			if (!isBlank(status))
				crit.add(Restrictions.eq("statusAvaliacao", status));

			if (disciplina != null && disciplina.getIdDisciplina() != null)
				crit.add(Restrictions.eq("avaliacao.disciplina", disciplina));

			return crit.list();
		} catch (Exception e) {
			throw new Exception("Erro ao obeter lista de avaliações." + e.getMessage());
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
		try {
			AvalicoesPK pk = new AvalicoesPK();
			pk.setIdAluno(usuario.getIdUsuario());
			pk.setIdAvaliacao(entidade.getIdAvaliacao());
			
			Avaliacao avaliacao = daoAvaliacao.buscaPorId(entidade.getIdAvaliacao());
			
			Avaliacoes avaliacoes = new Avaliacoes();
			avaliacoes.setId(pk);
			avaliacoes.setAluno(daoUsuario.buscaUsuarioPorId(usuario.getIdUsuario()));
			avaliacoes.setProfessor(daoUsuario.buscaUsuarioPorId(avaliacao.getProfessor().getIdUsuario()));
			avaliacoes.setAvaliacao(avaliacao);
			avaliacoes.setStatusAvaliacao(STATUS_AVALIACAO_AGUARDANDO_INICIO);
			create(avaliacoes);
		} catch (Exception e) {
			throw new Exception("Erro incluir avaliação para o aluno [" + usuario.getNome() + "]" + e.getMessage());
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
		// Obetem a lista de alunos
		try {
			alunosTurmas = daoAlunosTurma.listarAlunosPorTurma(entidade.getTurma());
		} catch (Exception e) {
			throw new Exception("Erro ao obter lista de alunos da turma");
		}
		// Grava as avalições no banco conforme lista de alunos que precisarão
		// responder
		try {
			
			Avaliacoes avaliacoes;
			
			
			for (AlunosTurma alunosTurma : alunosTurmas) {
				Avaliacao avaliacao = daoAvaliacao.buscaPorId(entidade.getIdAvaliacao());
				avaliacoes = new Avaliacoes();
				AvalicoesPK pk = new AvalicoesPK();
				pk.setIdAluno(alunosTurma.getAluno().getIdUsuario());
				pk.setIdAvaliacao(avaliacao.getIdAvaliacao());
				avaliacoes.setId(pk);
				avaliacoes.setProfessor(daoUsuario.buscaUsuarioPorId(avaliacao.getProfessor().getIdUsuario()));
				avaliacoes.setAvaliacao(avaliacao);
				avaliacoes.setStatusAvaliacao(STATUS_AVALIACAO_AGUARDANDO_INICIO);
				create(avaliacoes);
			}
		} catch (Exception e) {
			throw new Exception(
					"Erro ao salvar as avaliações para os alunos da turma [" + entidade.getTurma().getDsTurma() + "]");
		}

	}

}
