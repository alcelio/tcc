package com.tc.logger;

import static com.tc.util.IavaliarUtil.removerAcentos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

/**
 * Gerenciador de Log, onde al�m de registrar o log no console, todos os Logs
 * ser�o armazenados em um {@link ArrayList} para consulta e/ou exibi��o em tela
 * 
 */
public class ALogger {
	
	/**
	 * Qual o limite de itens que ser� mantido no log
	 */
	public static int	listLimit	= 500;
	
	/**
	 * Identifica o tipo do log, podendo ser
	 * <ul>
	 * <li>DEBUG</li>
	 * <li>ERROR</li>
	 * <li>FATAL</li>
	 * <li>INFO</li>
	 * <li>WARN</li>
	 * </ul>
	 */
	public enum LogType {
		DEBUG,
		ERROR,
		FATAL,
		INFO,
		WARN
	}
	
	/**
	 * Cria uma inst�cia do gerenciador de Logs
	 * 
	 * @param clazz
	 * @return MLogger
	 */
	public static ALogger getLogger(Class<?> clazz) {
		String name = (clazz == null) ? null : clazz.getSimpleName();
		ALogger logger = new ALogger(Logger.getLogger(clazz), name);
		return logger;
	}
	
	/**
	 * Cria uma instâcia do gerenciador de Logs
	 * 
	 * @param name
	 * @return MLogger
	 */
	public static ALogger getLogger(String name) {
		ALogger logger = new ALogger(Logger.getLogger(name), name);
		return logger;
	}
	
	/**
	 * Cria uma inst�cia do gerenciador de Logs
	 * 
	 * @param name
	 * @param factory
	 * @return MLogger
	 */
	public static ALogger getLogger(String name, LoggerFactory factory) {
		ALogger logger = new ALogger(Logger.getLogger(name, factory), name);
		return logger;
	}
	
	/**
	 * Lista de logs registrados
	 */
	private static List<LoggerBean>	logList;
	
	/**
	 * Lista de logs registrados
	 * 
	 * @return Uma lista de {@link LoggerBean} com as informa��es de log
	 */
	public static List<LoggerBean> getLogList() {
		if (logList == null)
			logList = new ArrayList<LoggerBean>();
		
		return logList;
	}
	
	/**
	 * limpa a lista de Loggs
	 */
	public static void clearList() {
		getLogList().clear();
	}
	
	private Logger	log;
	private String	name;
	
	/**
	 * Construtor da classe
	 * 
	 * @see Logger
	 */
	public ALogger(Logger log, String name) {
		super();
		this.log = log;
		this.name = name;
	}
	
	/**
	 * Inclui um novo Log na lista de logs
	 * 
	 * @param message
	 *            Mensagem que ser� inclu�da no log
	 */
	private void addLogger(LogType type, Object message) {
		if (listLimit <= 0)
			return;
		
		LoggerBean b = new LoggerBean();
		b.setName(name);
		b.setDate(new Date());
		b.setLogType(type);
		b.setMessage((message == null) ? "-------------" : message);
		getLogList().add(b);
		
		if (getLogList().size() > listLimit)
			getLogList().remove(0);
	}
	
	/**
	 * @see org.apache.log4j.Category#debug(java.lang.Object, java.lang.Throwable)
	 */
	public void debug(Object message, Throwable t) {
		log.debug((message instanceof String) ? removerAcentos((String) message) : message, t);
		if (log.isDebugEnabled())
			addLogger(LogType.DEBUG, message);
	}
	
	/**
	 * @see org.apache.log4j.Category#debug(java.lang.Object)
	 */
	public void debug(Object message) {
		log.debug((message instanceof String) ? removerAcentos((String) message) : message);
		if (log.isDebugEnabled())
			addLogger(LogType.DEBUG, message);
	}
	
	/**
	 * @see org.apache.log4j.Category#error(java.lang.Object, java.lang.Throwable)
	 */
	public void error(Object message, Throwable t) {
		log.error((message instanceof String) ? removerAcentos((String) message) : message, t);
		addLogger(LogType.ERROR, message);
	}
	
	/**
	 * @see org.apache.log4j.Category#error(java.lang.Object)
	 */
	public void error(Object message) {
		log.error((message instanceof String) ? removerAcentos((String) message) : message);
		addLogger(LogType.ERROR, message);
	}
	
	/**
	 * @see org.apache.log4j.Category#fatal(java.lang.Object, java.lang.Throwable)
	 */
	public void fatal(Object message, Throwable t) {
		log.fatal((message instanceof String) ? removerAcentos((String) message) : message, t);
		addLogger(LogType.FATAL, message);
	}
	
	/**
	 * @see org.apache.log4j.Category#fatal(java.lang.Object)
	 */
	public void fatal(Object message) {
		log.fatal((message instanceof String) ? removerAcentos((String) message) : message);
		addLogger(LogType.FATAL, message);
	}
	
	/**
	 * @see org.apache.log4j.Category#info(java.lang.Object, java.lang.Throwable)
	 */
	public void info(Object message, Throwable t) {
		log.info((message instanceof String) ? removerAcentos((String) message) : message, t);
		if (log.isInfoEnabled())
			addLogger(LogType.INFO, message);
	}
	
	/**
	 * @see org.apache.log4j.Category#info(java.lang.Object)
	 */
	public void info(Object message) {
		log.info((message instanceof String) ? removerAcentos((String) message) : message);
		if (log.isInfoEnabled())
			addLogger(LogType.INFO, message);
	}
	
	/**
	 * @see org.apache.log4j.Category#warn(java.lang.Object, java.lang.Throwable)
	 */
	public void warn(Object message, Throwable t) {
		log.warn((message instanceof String) ? removerAcentos((String) message) : message, t);
		addLogger(LogType.WARN, message);
	}
	
	/**
	 * @see org.apache.log4j.Category#warn(java.lang.Object)
	 */
	public void warn(Object message) {
		log.warn((message instanceof String) ? removerAcentos((String) message) : message);
		addLogger(LogType.WARN, message);
	}
	
}
