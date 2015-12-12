package com.tc.data;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tc.model.ParametrosUsuario;
import com.tc.model.PK.ParametrosUsuarioPK;
import com.tc.util.CriaCriteria;

import tc.common.exceptions.DatabaseException;
@Stateless
@LocalBean
@Remote
public class ParametrosUsuarioDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	
	public ParametrosUsuario buscarPorChave(Integer idParametro, Integer idUsuario) throws DatabaseException {

		final Session session = em.unwrap(Session.class);

		ParametrosUsuarioPK pk = new ParametrosUsuarioPK();
		pk.setIdParametro(idParametro);
		pk.setIdUsuario(idUsuario);

		final Criteria crit = CriaCriteria.createCriteria(ParametrosUsuario.class, session);
		crit.add(Restrictions.eq("id", pk));
		
		return (ParametrosUsuario) crit.uniqueResult();

	}


}
