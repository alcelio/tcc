package com.tc.data;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tc.model.Endereco;

/**
 * Session Bean implementation class EnderecoBeanDao
 */

@Stateless
@LocalBean
@Remote
public class EnderecoBeanDao implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	public EnderecoBeanDao() {
    }
	
    public void create(Endereco entidade){
    	em.persist(entidade);
    }
    
    public void update(Endereco entidade){
    	em.merge(entidade);
    }
    public void remove(Endereco entidade){
    	em.remove(em.getReference(Endereco.class, entidade.getIdEndereco()));  
    }
    
    public List<Endereco> listarEndereco(){
    	return em.createNamedQuery("Endereco.findAll", Endereco.class).getResultList();
    }
    
    public Endereco buscaEnderecoUsuario(Integer idEndereco){
    	return em.find(Endereco.class, idEndereco);
    	
    }
    
    
    

}
