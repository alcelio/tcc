package com.tc.data;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.tc.model.Turno;
/**
 * Session Bean implementation class TrunoBeanDao
 */
@Stateless
@LocalBean
@Remote
public class TurnoBeanDao implements Serializable{
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;
	
	public TurnoBeanDao() {
    }
    
	public void create(Turno entidade) {
		em.persist(entidade);
	}

	public void update(Turno entidade) {
		em.merge(entidade);
	}

	public void remove(Turno entidade) {
		em.remove(em.getReference(Turno.class, entidade.getIdTurno()));
	}

	public List<Turno> listarTurnos() {
		return em.createNamedQuery("Turno.findAll", Turno.class).getResultList();
	}

}
