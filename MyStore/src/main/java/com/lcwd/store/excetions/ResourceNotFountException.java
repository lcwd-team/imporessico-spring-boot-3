package com.lcwd.store.excetions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFountException extends RuntimeException {



	public ResourceNotFountException(String message) {
		super(message);
	
	}

	public ResourceNotFountException() {
		super("Resource Not Found on Server !!");
		
	}

}
