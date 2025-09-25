package com.micro.account.exceptiosns;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ResourceNotFoundException() {
		super("ResourceNotFoundException!");
	}
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
