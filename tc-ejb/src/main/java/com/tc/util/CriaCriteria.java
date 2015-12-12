package com.tc.util;

import org.hibernate.Criteria;
import org.hibernate.Session;

import tc.common.exceptions.DatabaseException;

import org.hibernate.FetchMode;

public final class CriaCriteria {

	/**
	 * Cria uma {@link Criteria} a partir da classe informada
	 *
	 * @param clazz
	 *            Classe para a criação da {@link Criteria}
	 * @param session
	 *           Sessão do banco para criar a {@link Criteria}
	 * @param joins
	 *            Seleção de quais joins a consulta deverá executar
	 * @return Uma isntância de {@link Criteria}
	 */
	public static Criteria createCriteria(Class<?> clazz, Session session, String... joins) throws DatabaseException {
		try {
			final Criteria crit = session.createCriteria(clazz).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			for (final String join : joins) {
				crit.setFetchMode(join, FetchMode.JOIN);
			}

			return crit;
		} catch (final Exception e) {
			throw new DatabaseException("Erro ao criar critério de consulta. Endidade: " + clazz.getSimpleName(), e);
		}
	}

}
