package com.tc.regras;

import static org.apache.commons.lang3.StringUtils.trimToEmpty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import com.tc.data.ParametrosSistemaDao;
import com.tc.exceptions.RegrasException;
import com.tc.logger.ALogger;
import com.tc.model.ParametrosSistema;
import com.tc.util.BigDecimalUtil;
import com.tc.util.IntegerUtil;

/**
 * Classe de implementação das regras de negócio para parametros de Usuários;
 * 
 * @author Alcélio Gomes
 */

public class ParametrosSistemaRN {
	@EJB
	private ParametrosSistemaDao dao;

	private static ALogger log = ALogger.getLogger(ParametrosSistemaRN.class);

	public ParametrosSistemaDao getDao() {
		return dao;
	}

	public void gravaParametroSistema(ParametrosSistema parametrosSistema) throws RegrasException {
		try {
			getDao().saveOrUpdate(parametrosSistema);
		} catch (Exception e) {
			throw new RegrasException(
					"Falha ao Gravar Parametro de Sistema [" + parametrosSistema.getParametro().getDesParametro()
							+ ", Valor: " + parametrosSistema.getDesValorParametro(),
					e);
		}
	}

	public ParametrosSistema buscarPorChave(Integer idParametro) throws RegrasException {

		try {
			ParametrosSistema retorno = getDao().buscarPorChave(idParametro);

			log.debug("Consultando parametro de sistema  [id: " + idParametro + "]");
			return retorno;
		} catch (Exception e) {
			throw new RegrasException("Falha ao buscar parametro [id: " + idParametro + "]", e);
		}
	}

	public Long buscarPorLongNoBanco(Integer idParamero, Long lngDefault) {

		try {
			ParametrosSistema param = buscarPorChave(idParamero);

			if ((param == null)
					|| ((param != null) && (StringUtils.trimToEmpty(param.getDesValorParametro()).isEmpty())))
				return lngDefault;

			return Long.valueOf(param.getDesValorParametro().trim());
		} catch (RegrasException e) {
			log.error("Falha ao consultar parametro [" + idParamero + "]", e);
			return lngDefault;
		}
	}

	public BigDecimal buscarPorBigDecimalNoBanco(Integer idParametro, BigDecimal bigDefault) {

		try {
			ParametrosSistema param = buscarPorChave(idParametro);

			if ((param == null) || ((param != null) && (trimToEmpty(param.getDesValorParametro()).isEmpty())))
				return bigDefault;

			if (param.getDesValorParametro().indexOf('.') >= 0 && param.getDesValorParametro().indexOf(',') >= 0) {
				log.info("Valor do parametro de integração [" + idParametro + "] contém formato inválido ["
						+ param.getDesValorParametro() + "]");
				return bigDefault;
			}

			// Convertendo "," em "."
			String aux = StringUtils.replace(param.getDesValorParametro().trim(), ",", ".");

			BigDecimal bigAux = BigDecimalUtil.createBigDecimal(aux, 10);
			return bigAux.setScale(10, RoundingMode.DOWN);
		} catch (RegrasException e) {
			log.error("Falha ao consultar parametro [" + idParametro + "]", e);
			return bigDefault;
		}
	}

	public Date buscarPorDataNoBanco(Integer idParametro, Date dateDefault) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			ParametrosSistema param = buscarPorChave(idParametro);

			if ((param == null) || (StringUtils.trimToEmpty(param.getDesValorParametro()).isEmpty()))
				return dateDefault;

			try {
				return sdf.parse(param.getDesValorParametro().trim());
			} catch (Exception e) {
				log.error("Falha ao converter String [" + param.getDesValorParametro() + "] para Date. "
						+ "Formato esperado [DD/MM/AAAA]", e);
				return dateDefault;
			}
		} catch (RegrasException e) {
			log.error("Falha ao consultar parametro [" + idParametro + "]", e);
			return dateDefault;
		}
	}

	public String buscarPorStringNoBanco(Integer idParametro, String strDefault) {

		try {
			ParametrosSistema param = buscarPorChave(idParametro);

			if ((param == null)
					|| ((param != null) && (StringUtils.trimToEmpty(param.getDesValorParametro()).isEmpty())))
				return strDefault;

			return param.getDesValorParametro();
		} catch (RegrasException e) {
			log.error("Falha ao consultar parametro [" + idParametro + "]", e);
			return strDefault;
		}
	}

	public boolean buscarPorBooleanNoBanco(Integer idParametro, boolean booDefault) {

		ParametrosSistema param = null;
		try {
			param = buscarPorChave(idParametro);

			if ((param == null) || ((param != null) && (StringUtils.isEmpty(param.getDesValorParametro()))))
				return booDefault;

			String vlrParam = StringUtils.trimToEmpty(param.getDesValorParametro());

			if (!StringUtils.isNumeric(vlrParam))
				return booDefault;

			return IntegerUtil.ONE.equals(Integer.valueOf(vlrParam));
		} catch (RegrasException e) {
			log.error("Falha ao consultar parametro [" + idParametro + "]", e);
			return booDefault;
		}
	}

	public Integer buscarPorIntegerNoBanco(Integer idParametro, Integer intDefault) {

		// Consultando parâmetro
		ParametrosSistema param = null;
		try {
			param = buscarPorChave(idParametro);

			// Verifica se encontrou o parametro
			if ((param == null) || ((param != null) && (trimToEmpty(param.getDesValorParametro()).isEmpty())))
				return intDefault;

			// Converte para um Inteiro
			return Integer.valueOf(param.getDesValorParametro().trim());
		} catch (RegrasException e) {
			log.error("Falha ao consultar parametro [" + idParametro + "]", e);
			return intDefault;
		}
	}

}
