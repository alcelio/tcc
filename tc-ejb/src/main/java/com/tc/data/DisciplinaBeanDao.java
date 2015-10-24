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

import com.tc.model.Disciplina;
import com.tc.util.CriaCriteria;

/**
 * Session Bean implementation class EnderecoBeanDao
 */

@Stateless
@LocalBean
@Remote
public class DisciplinaBeanDao implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	public DisciplinaBeanDao() {
    }
	
    public void create(Disciplina entidade){
    	em.persist(entidade);
    }
    
    public void update(Disciplina entidade){
    	em.merge(entidade);
    }
    public void remove(Disciplina entidade){
    	em.remove(em.getReference(Disciplina.class, entidade.getIdDisciplina()));  
    }
    
    public List<Disciplina> listarDisciplina(){
    	return em.createNamedQuery("Disciplina.findAll", Disciplina.class).getResultList();
    }

	public Disciplina buscaPorId(Integer disciplina) throws Exception {
		final Session session = em.unwrap(Session.class);
		Disciplina disc = null;
		
		try {
			final Criteria crit = CriaCriteria.createCriteria(Disciplina.class, session);
			crit.add(Restrictions.eq("idDisciplina", disciplina));
			
			disc = (Disciplina) crit.uniqueResult();
			
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar disciplina no banco de dados.", e);
		}
		return disc;
	}
    
}
