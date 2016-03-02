package com.tc.util;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tc.exceptions.ScreenException;
import com.tc.logger.ALogger;

/**
 * Classe com m�todos estéticos para auxiliar o desenvolmento de forma geral
 * 
 * @author Rodrigo G. Tavares de Souza
 */
public class IavaliarUtil {
	
	static String								acentuado	= "çÇáéíóúýÁÉÍÓÚÝàèìòùÀÈÌÒÙãõñäëïöüÿÄËÏÖÜÃÕÑâêîôûÂÊÎÔÛ";
	static String								semAcento	= "cCaeiouyAEIOUYaeiouAEIOUaonaeiouyAEIOUAONaeiouAEIOU";
	static char[]								tabela;
	
	private static SimpleDateFormat				sdfHr;
	private static SimpleDateFormat				sdfHrSg;
	
	private static HashSet<Character>			lstCharAscii;
	
	private static ALogger						log			= ALogger.getLogger(IavaliarUtil.class);
	
	
	static {
		
		tabela = new char[256];
		for (int i = 0; i < tabela.length; ++i) {
			tabela[i] = (char) i;
		}
		for (int i = 0; i < acentuado.length(); ++i) {
			tabela[acentuado.charAt(i)] = semAcento.charAt(i);
		}
		
		lstCharAscii = new HashSet<Character>(0);
		lstCharAscii.add((char) 10);
		lstCharAscii.add((char) 13);
		lstCharAscii.add((char) 16);
		lstCharAscii.add((char) 27);
		for (char c = 32; c < 127; c++)
			lstCharAscii.add(c);
		
	}

	public static String formatar(SimpleDateFormat sdf, Date data) {
		return (data == null) ? null : sdf.format(data);
	}
	
	public static String formatarHora(Date data) {
		return formatar(sdfHr, data);
	}
	
	public static String formatarHoraComSegundos(Date data) {
		return formatar(sdfHrSg, data);
	}
	
	/**
	 * Quebra uma {@link String} em linhas conforme o tamanho especificado
	 * 
	 * @param str
	 *            String que será quebrada
	 * @param size
	 *            Tamanho para quebra
	 * @return Um {@link List} com as linhas retornadas
	 */
	public static List<String> brakeLine(String str, int size) {
		ArrayList<String> lines = new ArrayList<String>(0);
		if (StringUtils.isEmpty(str)) {
			lines.add("");
			return lines;
		}
		
		String s = str.replace('\n', ' ').replace('\r', ' ');
		
		if (s.length() < size) {
			lines.add(s.trim());
			return lines;
		}
		
		int idxIni = 0;
		int idxEnd = size - 1;
		
		while (true) {
			if (idxEnd > s.length()) {
				lines.add(s.substring(idxIni).trim());
				break;
			}
			
			String tmp = s.substring(idxIni, idxEnd);
			if (tmp.indexOf(" ") < 0) {
				lines.add(tmp.trim());
				idxIni = idxEnd;
				idxEnd += (size - 1);
				continue;
			}
			
			int idxEspace = tmp.lastIndexOf(" ");
			lines.add(tmp.substring(0, idxEspace).trim());
			idxEnd = idxIni + idxEspace + size;
			idxIni += (idxEspace + 1);
		}
		
		return lines;
	}
	
	/**
	 * Quebra uma {@link String} em linhas adicionando um <code>\n</code> conforme tamanho especificado
	 * 
	 * @param str
	 *            String que será quebrada
	 * @param size
	 *            Tamanho para quebra
	 * @return Uma {@link String} com quebra de linhas com o char #13
	 */
	public static String brakeLineChar(String str, int size) {
		StringBuffer sb = new StringBuffer();
		List<String> lines =  IavaliarUtil.brakeLine(str, size);
		if (lines == null || lines.isEmpty())
			return "";
		
		if (lines.size() == 1)
			return lines.get(0);
		
		String n = "";
		for (String s : lines) {
			sb.append(n + s);
			n = "\n";
		}
		return sb.toString();
	}
	
	
	/**
	 * Remove os acentos da String, substituindo os caracteres acentuados pelos seus equivalentes 
	 * 
	 * @param desConteudo
	 *            A {@link String} com o conteúdo desejado.
	 * @param isRemoverQuebraLinha
	 *            Um indicador booleano informando se deseja, ou não, remover as quebras de linha.
	 * @return
	 */
	public static String removerAcentos(final String desConteudo, boolean isRemoverQuebraLinha) {
		String desRetorno = "";
		if (!StringUtils.isBlank(desConteudo)) {
			desRetorno = Normalizer.normalize(desConteudo, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}", "");
			if (isRemoverQuebraLinha)
				desRetorno = desRetorno.toString().replace('\t', ' ').replace('\r', ' ').replace('\n', ' ');
		}
		return desRetorno;
	}
	public static String removerAcentos(final String desConteudo) {
		return removerAcentos(desConteudo, false);
	}
	
	
	/**
	 * Consulta o IP da estação
	 * 
	 * @return Uma {@link String} com o IP da estação
	 */
	public static String ipAddress() {
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			log.error("Nao foi possivel consulta o numero " + "do ip da maquina", e);
		}
		return ip;
	}

	/**
	 * Retorna a quantidade de dias que existe entre a data de inicio e a data
	 * de fim
	 * 
	 * @param dtaInicial
	 * @param dtaFinal
	 * @return Integer
	 * @throws ScreenException
	 */
	public static Integer diferencaEntreDias(Date dtaInicial, Date dtaFinal) throws ScreenException {
		
		if (dtaInicial == null)
			throw new ScreenException("Datas devem ser informadas!");
		
		long difEmMsegs = dtaInicial.getTime() - dtaFinal.getTime();
		int difEmDias = (int) ((difEmMsegs / 1000) / 3600) / 24;
		
		return difEmDias;
	}
	
	
	/**
	/**
	 * Método NVL, método retorna segundo parâmetro, caso o primeiro esteja nulo
	 * 
	 * @param <T>
	 *            <T>
	 * @param param1
	 *            T
	 * @param param2
	 *            T
	 * @return T
	 */
	public static <T> T nvl(T param1, T param2) {
		if (param1 != null)
			return param1;
		return param2;
	}
	
	/**
	 * Faz o cast de uma String para BigDecimal, formato aceito: "#,###.#"
	 * 
	 * @param vlr
	 * @return BigDecimal
	 * @throws Exception
	 */
	public static BigDecimal stringToBigDecimal(String vlr) throws Exception {
		if (vlr == null || vlr.trim().isEmpty())
			return BigDecimal.ZERO;
		
		// verifica se há mais de um ponto ou se há alguma vírgula após o ponto
		if (vlr.split("\\.").length > 2 || (vlr.split("\\.").length == 2 && vlr.split("\\.")[1].contains(",")))
			throw new Exception("Formato incorreto.");
		
		vlr = vlr.replace(",", "");
		
		return new BigDecimal(vlr);
	}
	
}
