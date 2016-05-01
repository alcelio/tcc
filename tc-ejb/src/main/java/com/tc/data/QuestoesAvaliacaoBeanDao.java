package com.tc.data;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tc.model.QuestoesAvaliacao;
import com.tc.model.PK.QuestoesAvaliacaoPK;

/**
 * Session Bean implementation class RespostasAvalicao
 */

@Stateless
@LocalBean
@Remote
public class QuestoesAvaliacaoBeanDao implements Serializable{


	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	public QuestoesAvaliacaoBeanDao() {
    }
	
    public void create(QuestoesAvaliacao entidade){
    	em.persist(entidade);
    }
    
    public void update(QuestoesAvaliacao entidade){
    	em.merge(entidade);
    }
    public void remove(QuestoesAvaliacaoPK pk){
    	em.remove(em.getReference(QuestoesAvaliacao.class, pk));  
    }
    
    public List<QuestoesAvaliacao> listarQuestoesAvalicao(){
    	return em.createNamedQuery("QuestoesAvaliacao.findAll", QuestoesAvaliacao.class).getResultList();
    }

}
