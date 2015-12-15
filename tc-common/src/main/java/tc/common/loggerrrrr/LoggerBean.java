package tc.common.loggerrrrr;

import java.io.Serializable;
import java.util.Date;

import tc.common.loggerrrrr.ALogger.LogType;

/**
 * Armazena o log identificando o tipo
 * 
 * @author Alcelio Gomes
 */
public class LoggerBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private LogType	logType;
	private Date	date;
	private String	name;
	private Object	message;
	
	/**
	 * Construtor da classe
	 */
	public LoggerBean() {
		super();
	}
	
	/**
	 * Construtor da classe informando os atributos
	 * 
	 * @param logType
	 *            Tipo do log
	 * @param name
	 *            Identifica��o do gerador do log
	 * @param message
	 *            Mensagem exibida
	 * @param date
	 *            Data do regitro do log
	 */
	public LoggerBean(LogType logType, String name, Object message, Date date) {
		this();
		this.logType = logType;
		this.message = message;
		this.date = date;
		this.name = name;
	}
	
	/**
	 * Obt�m logType
	 * 
	 * @return Um {@link LogType} com logType
	 */
	public LogType getLogType() {
		return logType;
	}
	
	/**
	 * Atualiza logType
	 * 
	 * @param logType
	 *            Um {@link LogType} com logType
	 */
	public void setLogType(LogType logType) {
		this.logType = logType;
	}
	
	/**
	 * Obt�m message
	 * 
	 * @return Um {@link Object} com message
	 */
	public Object getMessage() {
		return message;
	}
	
	/**
	 * Atualiza message
	 * 
	 * @param message
	 *            Um {@link Object} com message
	 */
	public void setMessage(Object message) {
		this.message = message;
	}
	
	/**
	 * Obt�m date
	 * 
	 * @return Um {@link Date} com date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Atualiza date
	 * 
	 * @param date
	 *            Um {@link Date} com date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return super.toString();
	}
	
	/**
	 * Obt�m name
	 * 
	 * @return Um {@link String} com name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Atualiza name
	 * 
	 * @param name
	 *            Um {@link String} com name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
