package com.micro.account.exceptiosns;

public class DuplicateResourceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateResourceException() {
		super("DUPLICATE_RESOURCE_FOUND!");
	}

	public DuplicateResourceException(String message) {
		super(message);
	}

}
