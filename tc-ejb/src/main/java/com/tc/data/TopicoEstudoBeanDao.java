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

import com.tc.model.TopicoEstudo;
import com.tc.util.CriaCriteria;
import com.tc.model.Disciplina;
/**
 * Session Bean implementation class EnderecoBeanDao
 */

@Stateless
@LocalBean
@Remote
public class TopicoEstudoBeanDao implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	public TopicoEstudoBeanDao() {
		
	}
	
    public void create(TopicoEstudo entidade){
    	em.persist(entidade);
    }
    
    public void update(TopicoEstudo entidade){
    	em.merge(entidade);
    }
    public void remove(TopicoEstudo entidade){
    	em.remove(em.getReference(TopicoEstudo.class, entidade.getIdTopicoEstudo()));  
    }
    
    public List<TopicoEstudo> listarTopicoEstudo(){
    	return em.createNamedQuery("TopicoEstudo.findAll", TopicoEstudo.class).getResultList();
    }
    
    @SuppressWarnings("unchecked")
//	public List<TopicoEstudo> listarTopicoEstudoDisciplina(Disciplina  disciplina) throws Exception{
//		final Session session = em.unwrap(Session.class);
//		
//		return  session.createCriteria(TopicoEstudo.class)
//			    .add( Restrictions.eq("idDisclipina", 1 )).list();
//    }
    
	public List<TopicoEstudo> listarTopicoEstudoDisciplina(Disciplina  disciplina) throws Exception{
		final Session session = em.unwrap(Session.class);
		List<TopicoEstudo> topicos;
		
		try {
			final Criteria crit = CriaCriteria.createCriteria(TopicoEstudo.class, session);
			topicos = new ArrayList<TopicoEstudo>();
			crit.add(Restrictions.eq("disciplina", disciplina));
			topicos =  crit.list();
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar dados da tabela.", e);
		}
		return topicos;
    }

	public TopicoEstudo buscaPorId(Integer idTopicoEstudo) throws Exception {
		final Session session = em.unwrap(Session.class);
		TopicoEstudo topico = null;
		
		try {
			final Criteria crit = CriaCriteria.createCriteria(TopicoEstudo.class, session);
			crit.add(Restrictions.eq("idTopicoEstudo", idTopicoEstudo));
			
			topico = (TopicoEstudo) crit.uniqueResult();
			
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar TopicoEstudo no banco de dados.", e);
		}
		return topico;
	}
    
}
