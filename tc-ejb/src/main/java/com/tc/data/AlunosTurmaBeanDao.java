package com.tc.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tc.model.AlunosTurma;
import com.tc.model.Turma;
import com.tc.model.Usuario;
import com.tc.model.PK.AlunosTurmaPK;
import com.tc.util.CriaCriteria;

/**
 * Session Bean implementation class EnderecoBeanDao
 */

@Stateless
@LocalBean
@Remote
public class AlunosTurmaBeanDao implements Serializable{
	private static final long serialVersionUID = 1L;
	@PersistenceContext

	private EntityManager em;

	public AlunosTurmaBeanDao() {
    }
	
    public void create(AlunosTurma entidade){
    	em.persist(entidade);
    }
    
    public void update(AlunosTurma entidade){
    	em.merge(entidade);
    }
    public void remove(AlunosTurma pk){
    	em.remove(em.getReference(AlunosTurma.class, pk));  
    }
    
    public List<AlunosTurma> listarAlunosTurma(){
    	return em.createNamedQuery("AlunosTurma.findAll", AlunosTurma.class).getResultList();
    }
    
    public boolean isExistePk(AlunosTurmaPK pk) throws Exception{
    	final Session session = em.unwrap(Session.class);
		try {
			final Criteria crit = CriaCriteria.createCriteria(AlunosTurma.class, session);
			crit.add(Restrictions.eq("id", pk));
			if(crit.uniqueResult() != null){
				return true;
			}else{
				return false;
			}
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar dados da tabela.", e);
		}
    }
//    
//	@SuppressWarnings("unchecked")
//	public List<AlunosTurma> listarTurmasPorAluno(Usuario aluno) throws Exception{
//		final Session session = em.unwrap(Session.class);
//		List<AlunosTurma>  alunosTurma = null;
//		try {
//			final Criteria crit = CriaCriteria.createCriteria(AlunosTurma.class, session);
//			crit.add(Restrictions.eq("idUsuario", aluno.getIdUsuario()));
//			alunosTurma = crit.list();
//		} catch (final Exception e) {
//			throw new Exception("Erro ao carregar dados da tabela.", e);
//		}
//		return alunosTurma;
//    }
    
    /**
     * Método que lista todas a entidades AlunosTurma em que um aluno está lotado
     * @param aluno
     * @return
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public List<Turma> listarTurmasDoAluno(Usuario aluno) throws Exception{
		final Session session = em.unwrap(Session.class);
		try {
			final Criteria crit = CriaCriteria.createCriteria(AlunosTurma.class, session);
			crit.add(Restrictions.eq("aluno", aluno));
			List<AlunosTurma> alunosTurma = crit.list();
			List<Turma> turmas = new ArrayList<>();
			for (AlunosTurma turmasAluno : alunosTurma) {
				turmas.add(turmasAluno.getTurma());
			}
			return turmas;
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar dados da tabela.", e);
		}
    }
	
	/**
	 * Método que lista todas retorna todas as turmas em que um aluno está lotado
	 * @param aluno
	 * @return
	 * @throws Exception
	 */
	
	@SuppressWarnings("unchecked")
	public List<AlunosTurma> listarAlunosTurmaPorAluno(Usuario aluno) throws Exception{
		final Session session = em.unwrap(Session.class);
		try {
			final Criteria crit = CriaCriteria.createCriteria(AlunosTurma.class, session);
			crit.add(Restrictions.eq("aluno", aluno));
			return crit.list();
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar dados da tabela.", e);
		}
    }
	/**
	 * Método que retorna todos os alunos que participam de uma turma
	 * @param turma
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AlunosTurma> listarAlunosPorTurma(Turma turma) throws Exception{
		final Session session = em.unwrap(Session.class);
		List<AlunosTurma>  alunosTurma = null;
		try {
			final Criteria crit = CriaCriteria.createCriteria(AlunosTurma.class, session);
			crit.add(Restrictions.eq("turma", turma));
			alunosTurma = crit.list();
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar dados da tabela.", e);
		}
		return alunosTurma;
    }
}
