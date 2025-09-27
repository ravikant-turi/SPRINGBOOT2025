package com.micro.plot.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException() {
		super("RESOURCE_NOT_FOUND_EXCEPTIONS");
	}

}
