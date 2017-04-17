package com.ebookhub.exceptions;

public class EbookHubException extends Exception {

	private static final long serialVersionUID = -6442677370686860995L;

	private String message;

	public EbookHubException(String message) {
		super(message);
		this.message = message;
	}

	public EbookHubException(Throwable ex) {
		super(ex);
	}

	public EbookHubException(String message, Throwable ex) {
		super(message, ex);
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

}
