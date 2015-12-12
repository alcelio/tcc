package tc.common.util;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import tc.common.exceptions.ScreenException;
import tc.common.logger.ALogger;

/**
 * Classe com m�todos est�ticos para auxiliar o desenvolmento de forma geral
 * 
 * @author Rodrigo G. Tavares de Souza
 */
public class IavaliarUtil {
	
	static String								acentuado	= "���������������������������������������������������";
	static String								semAcento	= "cCaeiouyAEIOUYaeiouAEIOUaonaeiouyAEIOUAONaeiouAEIOU";
	static char[]								tabela;
	
	private static SimpleDateFormat				sdfDt;
	private static SimpleDateFormat				sdfHr;
	private static SimpleDateFormat				sdfHrSg;
	private static SimpleDateFormat				sdfDtHr;
	private static SimpleDateFormat				sdfDiaMes;
	private static SimpleDateFormat				sdfTimestamp;
	
	private static DecimalFormat				dFormat2;
	private static DecimalFormat				dFormat3;
	
	private static HashSet<Character>			lstCharAscii;
	
	private static ALogger						log			= ALogger.getLogger(IavaliarUtil.class);
	
	
	static {
		
		sdfDt = new SimpleDateFormat("dd/MM/yyyy");
		sdfHr = new SimpleDateFormat("HH:mm");
		sdfHrSg = new SimpleDateFormat("HH:mm:ss");
		sdfDtHr = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		sdfDiaMes = new SimpleDateFormat("dd/MM");
		sdfTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		dFormat2 = new DecimalFormat("###,##0.00");
		dFormat3 = new DecimalFormat("###,##0.000");
		
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
	
	public static BigDecimal stringToBigDecimal(String vlr) throws Exception {
		if (vlr == null || vlr.trim().isEmpty())
			return BigDecimal.ZERO;
		
		// verifica se h� mais de um ponto ou se h� alguma v�rgula ap�s o ponto
		if (vlr.split("\\.").length > 2 || (vlr.split("\\.").length == 2 && vlr.split("\\.")[1].contains(",")))
			throw new Exception("Formato incorreto.");
		
		vlr = vlr.replace(",", "");
		
		return new BigDecimal(vlr);
	}
	
	/**
	 * Formata��o de um {@link Date} para ums String: dd/mm/aaaa
	 * 
	 * @param data
	 *            Data que ser� formatada
	 * @return String formatada em dd/mm/aaaa
	 */
	public static String formatarData(Date data) {
		return formatar(sdfDt, data);
	}
	
	/**
	 * Formata��o de um {@link Date} para ums String: dd/mm
	 * 
	 * @param data
	 *            Data que ser� formatada
	 * @return String formatada em dd/mm
	 */
	public static String formatarDataDiaMes(Date data) {
		return formatar(sdfDiaMes, data);
	}
	
	/**
	 * Formata��o de um {@link Date} para ums String: dd/mm/aaaa hh:mm
	 * 
	 * @param data
	 *            Data que ser� formatada
	 * @return String formatada em dd/mm/aaaa hh:mm
	 */
	public static String formatarDataHora(Date data) {
		return formatar(sdfDtHr, data);
	}
	
	/**
	 * Formata��o de um {@link Date} para uma String: yyyy-MM-dd HH:mm:ss
	 * 
	 * @param data
	 *            Data que ser� formatada
	 * @return String formatada em yyyy-MM-dd HH:mm:ss
	 */
	public static String formatarDataTimestamp(Date data) {
		return formatar(sdfTimestamp, data);
	}
	
	/**
	 * Auxilia os m�todos de convers�o de data e/ou hora
	 * 
	 * @param sdf
	 *            Inst�ncia do formato
	 * @param strData
	 *            String que ser� convertida para Data
	 * @return Data a partir da String informada
	 */
	public static Date formatar(SimpleDateFormat sdf, String strData) {
		try {
			return sdf.parse(strData);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Converte uma String no formato dd/mm/aaaa para um objeto {@link Date}
	 * 
	 * @param strData
	 *            Data que ser� formatada
	 * @return Data a partir da String informada
	 */
	public static Date formatarData(String strData) {
		return formatar(sdfDt, strData);
	}
	
	/**
	 * Converte uma String no formato dd/mm/aaaa hh:mm para um objeto {@link Date}
	 * 
	 * @param strData
	 *            Data que ser� formatada
	 * @return Data a partir da String informada
	 */
	public static Date formatarDataHora(String strData) {
		return formatar(sdfDtHr, strData);
	}
	
	/**
	 * Formata��o de uma String: yyyy-MM-dd HH:mm:ss para um {@link Date}
	 * 
	 * @param strData
	 *            Data que ser� formatada
	 * @return String formatada em yyyy-MM-dd HH:mm:ss
	 */
	public static Date formatarDataTimestamp(String strData) {
		return formatar(sdfTimestamp, strData);
	}
	
	/**
	 * Converte uma String no formato hh:mm para um objeto {@link Date}
	 * 
	 * @param strData
	 *            Data que ser� formatada
	 * @return Data a partir da String informada
	 */
	public static Date formatarHora(String strData) {
		return formatar(sdfHr, strData);
	}
	
	
	/**
	 * Fomatar um numero decimal com a duas casas decimais
	 * 
	 * @param d
	 *            N�mero que ser� formatado
	 * @return String formatada
	 */
	public static String formatarNumero(BigDecimal d) {
		return formatarNumero(d, false);
	}
	
	/**
	 * Fomatar um numero decimal com a quantidade de casas decimais informadas
	 * 
	 * @param d
	 *            N�mero que ser� formatado
	 * @param tresCasas
	 *            Indica se deve usar formata��o com tr�s casas decimais, <code>false</code> formatar� com 2 casas
	 * @return String formatada
	 */
	public static String formatarNumero(BigDecimal d, boolean tresCasas) {
		if (d == null)
			d = BigDecimalUtil.ZERO;
		return (tresCasas) ? dFormat3.format(d) : dFormat2.format(d);
	}
	
	/**
	 * Trunca a data atual removendo as horas <code>[data] 00:00:00</code>
	 * 
	 * @return Um {@link Date} com a data de hoje sem as horas
	 */
	public static Date dataSemHora() {
		return dataSemHora(new Date());
	}
	
	/**
	 * Trunca uma data removendo as horas <code>[data] 00:00:00</code>
	 * 
	 * @param data
	 *            Data que sera truncada
	 * 
	 * @return Um {@link Date} com a data de hoje sem as horas
	 */
	public static Date dataSemHora(Date data) {
		try {
			return DateUtils.truncate(data, Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			log.error("Falha ao remover a hora da data [" + data + "]", e);
			return null;
		}
	}
	
	
	/**
	 * Quebra uma {@link String} em linhas conforme o tamanho especificado
	 * 
	 * @param str
	 *            String que ser� quebrada
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
	 *            String que ser� quebrada
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
	 *            A {@link String} com o conte�do desejado.
	 * @param isRemoverQuebraLinha
	 *            Um indicador booleano informando se deseja, ou n�o, remover as quebras de linha.
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
	 * Realiza a valida��o do CNPJ.
	 * 
	 * @param strCNPJ
	 *            n�mero de strCNPJ a ser validado
	 * @return <code>true</code> se o strCNPJ � v�lido e <code>false</code> se
	 *         n�o � v�lido
	 */
	public static boolean isCNPJ(String strCNPJ) {
		
		if ((strCNPJ == null) || (strCNPJ.length() != 14))
			return false;
		
		int soma = 0, dig;
		String cnpj_calc = strCNPJ.substring(0, 12);
		
		char[] chr_cnpj = strCNPJ.toCharArray();
		
		/* Primeira parte */
		for (int i = 0; i < 4; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
				soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);
		
		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
		
		/* Segunda parte */
		soma = 0;
		for (int i = 0; i < 5; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
				soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);
		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
		
		return strCNPJ.equals(cnpj_calc);
	}
	
	/**
	 * Efetua a formata��o de um n�mero de telefone
	 * <ul>
	 * <li>Fone 1: 3334444 => 333-4444</li>
	 * <li>Fone 2: 44444444 => 4444-4444</li>
	 * <li>Fone 3: 223334444 => (22) 333-4444</li>
	 * <li>Fone 4: 2244444444 => (22) 4444-4444</li>
	 * </ul>
	 * 
	 * @param numFone
	 *            N�mero que ser� formatado
	 * @return O n�mero do telefone formatado
	 */
	public static String formatarFone(String numFone) {
		if (numFone == null)
			throw new NullPointerException("Impossivel formatar um telefone nulo");
		
		// Removendo espa�os em branco
		numFone = numFone.trim();
		if (numFone.isEmpty())
			// Vazio
			return "";
		else if (numFone.length() == 7)
			// 7 n�meros
			return numFone.substring(0, 3) + "-" + numFone.substring(3);
		else if (numFone.length() == 8)
			// 8 n�meros
			return numFone.substring(0, 4) + "-" + numFone.substring(4);
		else if (numFone.length() == 9 || numFone.length() == 10)
			// 9 ou 10 n�meros
			return "(" + numFone.substring(0, 2) + ") " + formatarFone(numFone.substring(2));
		else if (numFone.length() > 10) {
			
			char[] array = numFone.substring(2).toCharArray();
			
			String restoFone = "";
			Integer count = 0;
			for (Integer a = array.length - 1; a >= 0; a--) {
				if (count == 4) {
					count = 0;
					restoFone = "-" + restoFone;
				}
				restoFone = array[a] + restoFone;
				count++;
			}
			
			// maior que 10 n�meros
			return "(" + numFone.substring(0, 2) + ") " + restoFone;
			
		}
		
		return numFone;
	}
	
	
	/**
	 * Consulta o IP da esta��o
	 * 
	 * @return Uma {@link String} com o IP da esta��o
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
	 * 
	 * Formata um BigDecimal para duas casas ap�s a virgula sem nenhum
	 * arredondamento
	 * 
	 * @return Um {@link BigDecimal} com duas casas ap�s a v�rgula sem
	 *         arredondamento
	 * */
	public static BigDecimal formatarNumeroDuasCasas(BigDecimal vlr) {
		
		if (vlr == null || BigDecimalUtil.equalToZero(vlr))
			return BigDecimal.ZERO;
		
		if (!vlr.toString().contains(".")) {
			String number = vlr.toString();
			number += ".00";
			return new BigDecimal(number);
		}
		
		// Captura valores ap�s a virgula
		String casasDecimais = vlr.toString().substring(vlr.toString().indexOf("."), vlr.toString().length());
		
		// Deixa apenas duas casas ap�s a virgula sem arredondamento
		try {
			casasDecimais = casasDecimais.substring(1, 3);
		} catch (Exception e) {
			// Caso nao possua o indice 3, adiciona um zero ao final
			casasDecimais = casasDecimais.substring(1, 2);
			casasDecimais += "0";
		}
		
		// Captura o valor at� a v�rgula
		String number = vlr.toString().substring(0, vlr.toString().indexOf("."));
		
		// Concatena novamente o numero
		number = number + "." + casasDecimais;
		
		return new BigDecimal(number);
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
	 * Fomatar um numero decimal com a quantidade de casas decimais informadas
	 * 
	 * @param number
	 *            N�mero que ser� truncado e formatado
	 * @param qtdCasas
	 *            quantidade de casas
	 * 
	 * @return String formatada
	 */
	public static String formatarNumeroTruncamento(BigDecimal number, Integer qtdCasas) {
		
		if (number == null)
			number = BigDecimalUtil.ZERO;
		
		if (qtdCasas == null || qtdCasas < 2)
			qtdCasas = 2;
		
		number = number.setScale(qtdCasas, BigDecimal.ROUND_DOWN);
		
		String strQtdCasas = "";
		
		for (int i = 0; i < qtdCasas; i++)
			strQtdCasas += "0";
		
		DecimalFormat dfDesObs = (DecimalFormat) NumberFormat.getNumberInstance(new Locale("en", "US"));
		dfDesObs.applyPattern("####0." + strQtdCasas);
		
		return dfDesObs.format(number);
	}
	
	/**
	 * Fomatar um numero decimal com a quantidade de casas decimais informadas
	 * 
	 * @param number
	 *            N�mero que ser� truncado e formatado
	 * @param qtdCasas
	 *            Quantidade de casas
	 * 
	 * @return String formatada
	 */
	public static String formatarNumeroComVirgula(BigDecimal number, Integer qtdCasas) {
		
		if (number == null)
			number = BigDecimalUtil.ZERO;
		
		if (qtdCasas == null || qtdCasas < 2)
			qtdCasas = 2;
		
		number = number.setScale(qtdCasas, BigDecimal.ROUND_DOWN);
		
		String strQtdCasas = "";
		
		for (int i = 0; i < qtdCasas; i++)
			strQtdCasas += "0";
		
		DecimalFormat dfDesObs = (DecimalFormat) NumberFormat.getNumberInstance(new Locale("en", "US"));
		dfDesObs.applyPattern("####0." + strQtdCasas);
		
		return dfDesObs.format(number).replace(".", ",");
	}
	
	/**
	 * Fomatar um numero decimal com a quantidade de casas decimais informadas
	 * 
	 * @param number
	 *            N�mero que ser� truncado e formatado
	 * 
	 * @return String formatada
	 */
	public static String formatarNumeroTrunc2Casas(BigDecimal number) {
		return formatarNumeroTruncamento(number, 2);
	}
	
	
	/**
	 * Preenche as casas do CPF caso necess�rio.
	 * 
	 * @param cpf
	 *            o n�mero do CPF.
	 * @return <code>null</code> quando o CPF recebido for nulo,
	 *         o pr�prio CPF caso ele tenha 11 d�gitos, ou um CPF
	 *         preenchido com zeros � esquerda caso ele tenha
	 *         menos que 11 d�gitos.
	 */
	public static String formataPreencheCpf(String cpf) {
		if (cpf == null)
			return null;
		
		return StringUtils.leftPad(cpf, 11, "0");
	}
	
	/**
	/**
	 * M�todo NVL, m�todo retorna segundo par�metro, caso o primeiro esteja nulo
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
	
	
	
}
