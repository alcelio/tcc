package com.tc.data;


import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tc.model.Sexo;
/**
 * Session Bean implementation class TipoLogradouroBeanDao
 */
@Stateless
@LocalBean
@Remote
public class SexoBeanDao implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	public SexoBeanDao() {
    }
	
    public void create(Sexo entidade){
    	em.persist(entidade);
    }
    
    public void update(Sexo entidade){
    	em.merge(entidade);
    }
    public void remove(Sexo entidade){
    	em.remove(em.getReference(Sexo.class, entidade.getIdSexo()));  
    }
    
    public List<Sexo> listarSexo(){
    	return em.createNamedQuery("Sexo.findAll", Sexo.class).getResultList();
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((em == null) ? 0 : em.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SexoBeanDao other = (SexoBeanDao) obj;
		if (em == null) {
			if (other.em != null)
				return false;
		} else if (!em.equals(other.em))
			return false;
		return true;
	}
    
    
}