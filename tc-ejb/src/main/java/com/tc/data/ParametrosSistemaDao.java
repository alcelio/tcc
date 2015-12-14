package com.tc.data;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tc.model.ParametrosSistema;
import com.tc.util.CriaCriteria;

import tc.common.exceptions.DatabaseException;

public class ParametrosSistemaDao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;

	public ParametrosSistemaDao() {
	}

	/**
	 * Busca parâmetro do sistema pelo id
	 * @throws DatabaseException 
	 * 
	 */
	public ParametrosSistema buscarPorChave(Integer idParametro)throws Exception {
		
		final Session session = em.unwrap(Session.class);
		
		final Criteria crit = CriaCriteria.createCriteria(ParametrosSistema.class, session);
		crit.add(Restrictions.eq("idParametroSistema", idParametro));
		
		return (ParametrosSistema) crit.uniqueResult();
	}
	
	public void saveOrUpdate(ParametrosSistema parametro){
		em.merge(parametro);
	}

}
