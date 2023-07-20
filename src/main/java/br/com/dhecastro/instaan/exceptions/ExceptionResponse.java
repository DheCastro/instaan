package br.com.dhecastro.instaan.exceptions;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ExceptionResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date timeStamp;
	private List<String> messages;
	
	public ExceptionResponse(Date timeStamp, List<String> messages) {
		super();
		this.timeStamp = timeStamp;
		this.messages = messages;
	}
	
	public ExceptionResponse(Date timeStamp, String message) {
		super();
		this.timeStamp = timeStamp;
		this.messages = Arrays.asList(message);
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public List<String> getMessages() {
		return messages;
	}

}
