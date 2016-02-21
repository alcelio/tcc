package com.tc.regras;

import static com.tc.util.IavaliarUtil.stringToBigDecimal;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import com.tc.data.ParametrosUsuarioDao;
import com.tc.logger.ALogger;
import com.tc.model.ParametrosUsuario;
import com.tc.util.StringUtil;

/**
 * Classe implementação de regras para busca de parametros de Usuários 
 * 
 * @author Alcélio Gomes
 */
public class ParametrosUsuarioRN {

	@EJB
	private ParametrosUsuarioDao dao;

	private static ALogger log = ALogger.getLogger(ParametrosUsuarioRN.class);

	public ParametrosUsuarioDao getDao() {
		return dao;
	}

	public Long buscarValorParametroSistemaLong(String[] descESeqParametro, Long valorDefault) {

		String valorString = buscarValorParametroUsuarioString(descESeqParametro, null);

		try {

			if (StringUtils.trimToEmpty(valorString).isEmpty()) {
				return valorDefault;
			}

			return Long.valueOf(valorString);
		} catch (Exception e) {
			log.error("Erro na conversão do valor " + valorString + " do parâmetro para o formato Long.", e);
			return valorDefault;
		}
	}

	public String buscarValorParametroUsuarioString(String[] descESeqParametro, String valorDefault) {

		// Retorna valor default caso sequência ou descrição do parâmetro não
		// tenha sido informado
		if (descESeqParametro == null || descESeqParametro[0] == null || descESeqParametro[1] == null)
			return valorDefault;

		log.info("Buscando parâmetro: " + descESeqParametro[1] + " - " + descESeqParametro[0]);

		String retorno = valorDefault;

		try {
			ParametrosUsuario paramUsuario = getDao().buscarPorChave(Integer.valueOf(descESeqParametro[0]),
					Integer.valueOf(descESeqParametro[1]));

			if (paramUsuario != null && !StringUtils.trimToEmpty(paramUsuario.getDesValorParametro()).isEmpty())
				retorno = paramUsuario.getDesValorParametro();

		} catch (Exception e) {
			log.error("Falha ao buscar parametro de sistema [idParametro:" + descESeqParametro[0] + " - idUsuario:"
					+ descESeqParametro[1] + "]", e);
		} finally {
			log.info("Valor retorno: " + retorno);
		}

		return retorno;
	}

	public Integer buscarValorParametroSistemaInteger(String[] descESeqParametro, Integer valorDefault) {

		String valorString = buscarValorParametroUsuarioString(descESeqParametro, null);

		if (StringUtils.trimToEmpty(valorString).isEmpty()) {
			return valorDefault;
		}

		try {

			return Integer.valueOf(valorString);
		} catch (Exception e) {
			log.error("Erro na conversão do valor " + valorString + " do parâmetro para o formato Integer.", e);
			return valorDefault;
		}
	}

	public BigDecimal buscarValorParametroSistemaBigDecimal(String[] descESeqParametro, BigDecimal valorDefault) {

		String valorString = buscarValorParametroUsuarioString(descESeqParametro, null);

		if (StringUtils.trimToEmpty(valorString).isEmpty()) {
			return valorDefault;
		}

		try {
			return stringToBigDecimal(valorString);
		} catch (Exception e) {
			log.error("Erro na conversão do valor " + valorString + " do parâmetro para o formato BigDecimal.", e);
			return valorDefault;
		}
	}

	public Date buscarValorParametroSistemaDate(String[] descESeqParametro, Date valorDefault) {

		String valorString = buscarValorParametroUsuarioString(descESeqParametro, null);

		if (StringUtils.trimToEmpty(valorString).isEmpty()) {
			return valorDefault;
		}
		String formato = "dd-MM-yyyy";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formato);
			return sdf.parse(valorString.trim());
		} catch (Exception e) {
			log.error("Erro na conversão do valor " + valorString + " do parâmetro para o formato Date.", e);

			return valorDefault;
		}
	}

	public Boolean buscarValorParametroSistemaBoolean(String[] descESeqParametro, Boolean valorDefault) {

		String valorString = buscarValorParametroUsuarioString(descESeqParametro, null);

		if (valorString == null)
			return valorDefault;
		if (StringUtil.ONE.equals(valorString))
			return true;
		if (StringUtil.ZERO.equals(valorString))
			return false;

		log.error("Erro na conversão do valor " + valorString + " do parâmetro para o formato boolean.");
		return valorDefault;
	}
}
