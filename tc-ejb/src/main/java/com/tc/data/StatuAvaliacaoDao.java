package com.tc.data;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tc.model.StatusAvaliacao;
import com.tc.util.CriaCriteria;
/**
 * Session Bean implementation class StatuAvaliacaoDao
 */
@Stateless
@LocalBean
@Remote
public class StatuAvaliacaoDao implements Serializable{
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;
	
	public StatuAvaliacaoDao() {
    }
	
	public void create( StatusAvaliacao entidade) {
		em.persist(entidade);
	}

	public void update( StatusAvaliacao entidade) {
		em.merge(entidade);
	}

	public void remove( StatusAvaliacao entidade) {
		em.remove(em.getReference( StatusAvaliacao.class, entidade.getIdStatusAvaliacao()));
	}

	public List<StatusAvaliacao> listarStatusAvaliacoes() {
		return em.createNamedQuery("StatusAvaliacao.findAll",  StatusAvaliacao.class).getResultList();
	}
	
	public StatusAvaliacao buscaStatusAvaliacaoPorId(Integer id) throws Exception{
		final Session session = em.unwrap(Session.class);
		StatusAvaliacao status = null;
		
		try {
			final Criteria crit = CriaCriteria.createCriteria(StatusAvaliacao.class, session);
			crit.add(Restrictions.eq("idStatusAvaliacao", id));
			status = (StatusAvaliacao) crit.uniqueResult();
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar dados da tabela Status Avaliação.", e);
			
		}
		return status;
    }

}
