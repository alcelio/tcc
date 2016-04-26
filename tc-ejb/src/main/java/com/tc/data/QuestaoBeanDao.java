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

import com.tc.model.Questao;
import com.tc.util.CriaCriteria;

/**
 * Session Bean implementation class EnderecoBeanDao
 */

@Stateless
@LocalBean
@Remote
public class QuestaoBeanDao implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	public QuestaoBeanDao() {
    }
	
    public void create(Questao entidade){
    	em.persist(entidade);
    }
    
    public void update(Questao entidade){
    	em.merge(entidade);
    }
    public void remove(Questao entidade){
    	em.remove(em.getReference(Questao.class, entidade.getIdQuestao()));  
    }
    
    public List<Questao> listarQuestoes (){
    	return em.createNamedQuery("Questao.findAll", Questao.class).getResultList();
    }
    
	public Questao buscaPorId(Integer idQuestao) throws Exception {
		final Session session = em.unwrap(Session.class);
		try {
			final Criteria crit = CriaCriteria.createCriteria(Questao.class, session);
			crit.add(Restrictions.eq("idQuestao", idQuestao));
			return (Questao) crit.uniqueResult();
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar Questão no banco de dados.", e);
		}
	}
    
}
