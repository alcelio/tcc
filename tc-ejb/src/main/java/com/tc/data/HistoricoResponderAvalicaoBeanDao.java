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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tc.model.HistoricoResponderAvaliacao;
import com.tc.model.Questao;
import com.tc.util.CriaCriteria;

/**
 * Session Bean implementation class EnderecoBeanDao
 */

@Stateless
@LocalBean
@Remote
public class HistoricoResponderAvalicaoBeanDao implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	public HistoricoResponderAvalicaoBeanDao() {
    }
	
    public void create(HistoricoResponderAvaliacao entidade){
    	em.persist(entidade);
    }
    
    public void update(HistoricoResponderAvaliacao entidade){
    	em.merge(entidade);
    }
    public void remove(HistoricoResponderAvaliacao entidade){
    	em.remove(em.getReference(HistoricoResponderAvaliacao.class, entidade.getIdHistorico()));  
    }
    
    public List<HistoricoResponderAvaliacao> listarDisciplina(){
    	return em.createNamedQuery("HistoricoResponderAvalicao.findAll", HistoricoResponderAvaliacao.class).getResultList();
    }

	public HistoricoResponderAvaliacao buscaPorId(Integer pk) throws Exception {
		final Session session = em.unwrap(Session.class);
		
		try {
			final Criteria crit = CriaCriteria.createCriteria(HistoricoResponderAvaliacao.class, session);
			crit.add(Restrictions.eq("idHistorico", pk));
			return  (HistoricoResponderAvaliacao) crit.uniqueResult();
			
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar históico no banco de dados.", e);
		}
	}
	
	public long tempoMedioPorQuestao(Questao questao) throws Exception {
		final Session session = em.unwrap(Session.class);
		
		try {
			final Criteria crit = CriaCriteria.createCriteria(HistoricoResponderAvaliacao.class, session);
			crit.add(Restrictions.eq("questao", questao));
			
			long totalUtilizacoes = (long) crit.setProjection(Projections.rowCount()).uniqueResult();
			long tempTotalUtilizado = (long) crit.setProjection(Projections.sum("tempoRespondendo")).uniqueResult();
			
			return tempTotalUtilizado/totalUtilizacoes;
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar históico da questão.", e);
		}
	}
    
}
