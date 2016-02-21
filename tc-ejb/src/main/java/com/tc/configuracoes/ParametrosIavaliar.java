package com.tc.configuracoes;

import java.math.BigDecimal;
import java.util.Date;

import com.tc.exceptions.ScreenException;
import com.tc.logger.ALogger;
import com.tc.regras.ParametrosSistemaRN;
import com.tc.regras.ParametrosUsuarioRN;

public class ParametrosIavaliar {

	private static ALogger log = ALogger.getLogger(ParametrosIavaliar.class);

	private static ParametrosIavaliar parametrosIavaliar;

	private ParametrosSistemaRN parametrosSistemaRN;

	private ParametrosUsuarioRN parametrosUsuarioRN;

	/**
	 * Retorna instancia da classe {@link ParametrosSistemaRN}
	 * 
	 * @return ParametrosSistemaRN
	 */
	private ParametrosSistemaRN getParametrosSistemaRN() {
		if (parametrosSistemaRN == null)
			parametrosSistemaRN = new ParametrosSistemaRN();

		return parametrosSistemaRN;
	}

	/**
	 * Retorna instância da classe {@link ParametrosUsuarioRN}
	 * 
	 * @return ParametrosSistemaRN
	 */
	private ParametrosUsuarioRN getParametrosUsuarioRN() {
		if (parametrosUsuarioRN == null)
			parametrosUsuarioRN = new ParametrosUsuarioRN();

		return parametrosUsuarioRN;
	}

	/**
	 * Instancia a classe de busca de parâmetros do Iavaliar;
	 */
	public static ParametrosIavaliar getParametrosIavaliar() {
		if (parametrosIavaliar == null)
			parametrosIavaliar = new ParametrosIavaliar();

		return parametrosIavaliar;
	}

	/**
	 * Busca parametros do Iavaliar
	 * 
	 */
	public <T> T buscarParametrosIavaliar(final Class<T> clazz, final Object parametro, final T defaultValue)
			throws ScreenException {

		T retorno = null;

		if (clazz == null) {
			throw new ScreenException("É necessário informar uma classe com o tipo do parâmetro!");
		}

		if (parametro == null) {
			throw new ScreenException("Parâmetro a ser buscado não informado!");
		}

		// considera parametro de Usuario
		if (parametro instanceof Integer) {
			log.debug("Buscando parametro de integracao [" + parametro + "]");
			retorno = buscarParametroSistema(clazz, parametro, defaultValue);
			log.info("Valor do parametro [" + parametro + "]: " + retorno);

		} else if (parametro instanceof String[]) {

			String[] param = (String[]) parametro;
			StringBuilder sbChave = new StringBuilder();
			sbChave.append(String.valueOf(param[0]));
			sbChave.append(String.valueOf(param[1]));

			log.debug("Buscando parametro [" + sbChave + "]..");

			retorno = buscarParametroUsuario(clazz, parametro, defaultValue);
			log.debug("Valor do parametro [" + sbChave.toString() + "]: " + retorno);

		}

		return retorno;

	}

	/**
	 * Consulta os parametros de sistema retornando seu valor
	 */
	private <T> T buscarParametroSistema(final Class<T> clazz, final Object parametro, final T defaultValue) {

		Object retorno = null;

		if (clazz.equals(String.class)) {
			retorno = getParametrosSistemaRN().buscarPorStringNoBanco((Integer) parametro, (String) defaultValue);
		} else if (clazz.equals(Integer.class)) {
			retorno = getParametrosSistemaRN().buscarPorIntegerNoBanco((Integer) parametro, (Integer) defaultValue);
		} else

		if (clazz.equals(Long.class)) {
			retorno = getParametrosSistemaRN().buscarPorLongNoBanco((Integer) parametro, (Long) defaultValue);
		} else

		if (clazz.equals(BigDecimal.class)) {
			BigDecimal decimal = new BigDecimal(defaultValue.toString());
			retorno = getParametrosSistemaRN().buscarPorBigDecimalNoBanco((Integer) parametro, decimal);
		} else

		if (clazz.equals(Date.class)) {
			retorno = getParametrosSistemaRN().buscarPorDataNoBanco((Integer) parametro, (Date) defaultValue);
		} else

		if (clazz.equals(Boolean.class)) {
			retorno = getParametrosSistemaRN().buscarPorBooleanNoBanco((Integer) parametro, (Boolean) defaultValue);
		}

		return clazz.cast(retorno);

	}

	/**
	 * Consulta os parametros de usuario retornando seu valor
	 */
	private <T> T buscarParametroUsuario(final Class<T> clazz, final Object parametro, final T defaultValue) {

		Object retorno = null;

		if (clazz.equals(Integer.class)) {
			retorno = getParametrosUsuarioRN().buscarValorParametroSistemaInteger((String[]) parametro,
					defaultValue == null ? null : (Integer) defaultValue);
		} else

		if (clazz.equals(Long.class)) {
			retorno = getParametrosUsuarioRN().buscarValorParametroSistemaLong((String[]) parametro,
					defaultValue == null ? null : (Long) defaultValue);
		} else

		if (clazz.equals(BigDecimal.class)) {
			retorno = getParametrosUsuarioRN().buscarValorParametroSistemaBigDecimal((String[]) parametro,
					defaultValue == null ? null : (BigDecimal) defaultValue);
		} else

		if (clazz.equals(Date.class)) {
			retorno = getParametrosUsuarioRN().buscarValorParametroSistemaDate((String[]) parametro,
					(Date) defaultValue == null ? null : (Date) defaultValue);
		} else

		if (clazz.equals(String.class)) {
			retorno = getParametrosUsuarioRN().buscarValorParametroUsuarioString((String[]) parametro,
					defaultValue == null ? null : (String) defaultValue);
		} else

		if (clazz.equals(Boolean.class)) {
			retorno = getParametrosUsuarioRN().buscarValorParametroSistemaBoolean((String[]) parametro,
					defaultValue == null ? null : (Boolean) defaultValue);
		}

		return clazz.cast(retorno);
	}

}
