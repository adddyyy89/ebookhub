package com.ebookhub.exceptions;

public class SetupException extends EbookHubException {

	private static final long serialVersionUID = 8812973547862778807L;
	private String message;

	public SetupException(String message) {
		super(message);
		this.message = message;
	}

	public SetupException(String message, Throwable ex) {
		super(message, ex);
		this.message = message;
	}

	public String getMessag() {
		return this.message;
	}
}
