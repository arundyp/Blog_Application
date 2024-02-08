package com.arun.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	String resourceName;
	String filedName;
	int fieldValue;
	public ResourceNotFoundException(String resourceName, String filedName, int fieldValue) {
		super(String.format("%s with user  %s  %s: not found",resourceName,filedName, fieldValue));
		this.resourceName = resourceName;
		this.filedName = filedName;
		this.fieldValue = fieldValue;
	}
	
	
	

}
