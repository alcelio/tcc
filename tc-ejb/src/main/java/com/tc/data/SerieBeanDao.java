package com.tc.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tc.model.Serie;

/**
 * Session Bean implementation class SerieBeanDao
 */
@Stateless
@LocalBean
@Remote
public class SerieBeanDao {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public SerieBeanDao() {
    }

    public void create(Serie entidade){
    	em.persist(entidade);
    }
    
    public void update(Serie entidade){
    	em.merge(entidade);
    }
    public void remove(Serie entidade){
    	em.remove(em.getReference(Serie.class, entidade.getIdSerie()));  
    }
    
    public List<Serie> listarSeries(){
    	return em.createNamedQuery("Serie.findAll", Serie.class).getResultList();
    }
}
