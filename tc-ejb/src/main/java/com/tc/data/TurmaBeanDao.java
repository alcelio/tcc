package com.tc.data;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tc.model.Turma;
/**
 * Session Bean implementation class TrunoBeanDao
 */
@Stateless
@LocalBean
@Remote
public class TurmaBeanDao implements Serializable{
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;
	
	public TurmaBeanDao() {
    }
    
	public void create(Turma entidade) {
		em.persist(entidade);
	}

	public void update(Turma entidade) {
		em.merge(entidade);
	}

	public void remove(Turma entidade) {
		em.remove(em.getReference(Turma.class, entidade.getIdTurma()));
	}

	public List<Turma> listarTurmas() {
		return em.createNamedQuery("Turma.findAll", Turma.class).getResultList();
	}
}
