package com.tc.data;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tc.model.Instituicao;

/**
 * Session Bean implementation class InstituicaoBeanDao
 */
@Stateless
@LocalBean
@Remote
public class InstituicaoBeanDao implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

    public InstituicaoBeanDao() {
    }

	
    public void create(Instituicao entidade){
    	em.persist(entidade);
    }
    
    public void update(Instituicao entidade){
    	em.merge(entidade);
    }
    public void remove(Instituicao entidade){
    	em.remove(em.getReference(Instituicao.class, entidade.getIdInstituicao()));  
    }
    
    public List<Instituicao> listarInstituicao(){
    	return em.createNamedQuery("Instituicao.findAll", Instituicao.class).getResultList();
    }
	

}
