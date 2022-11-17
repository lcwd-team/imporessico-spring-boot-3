package com.lcwd.store.excetions;


public class InvalidAgeException extends RuntimeException {
	
	public InvalidAgeException() {
		super("Age is Invalid");
	}

	public InvalidAgeException(String message) {
		super(message);
	}
}
