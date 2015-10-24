package com.tc.data;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tc.model.Questao;

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
    
    
}
