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

import com.tc.model.Avisos;
import com.tc.model.Usuario;
import com.tc.util.CriaCriteria;

/**
 * Session Bean implementation class EnderecoBeanDao
 */

@Stateless
@LocalBean
@Remote
public class AvisosBeanDao implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	public AvisosBeanDao() {
    }
	
    public void create(Avisos entidade){
    	em.persist(entidade);
    }
    
    public void update(Avisos entidade){
    	em.merge(entidade);
    }
    public void remove(Avisos entidade){
    	em.remove(em.getReference(Avisos.class, entidade.getIdAvisos()));  
    }
    
    public List<Avisos> listarAvisos(){
    	return em.createNamedQuery("Avisos.findAll", Avisos.class).getResultList();
    }

	@SuppressWarnings("unchecked")
	public List<Avisos> listarAvisosPendenteUsuario(Usuario usuario) throws Exception {
		final Session session = em.unwrap(Session.class);
		
		try {
			final Criteria crit = CriaCriteria.createCriteria(Avisos.class, session);
			crit.add(Restrictions.eq("usuario", usuario)).add(Restrictions.eq("ativo", true));
			
			return crit.list();
			
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar lista de avisos", e);
		}
	}
    
}
