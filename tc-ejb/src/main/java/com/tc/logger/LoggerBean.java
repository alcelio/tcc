package com.tc.logger;

import java.io.Serializable;
import java.util.Date;

import com.tc.logger.ALogger.LogType;

/**
 * Armazena o log identificando o tipo
 */
public class LoggerBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private LogType	logType;
	private Date	date;
	private String	name;
	private Object	message;
	
	public LoggerBean() {
		super();
	}
	
	public LoggerBean(LogType logType, String name, Object message, Date date) {
		this();
		this.logType = logType;
		this.message = message;
		this.date = date;
		this.name = name;
	}
	
	public LogType getLogType() {
		return logType;
	}
	
	public void setLogType(LogType logType) {
		this.logType = logType;
	}
	
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return super.toString();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
