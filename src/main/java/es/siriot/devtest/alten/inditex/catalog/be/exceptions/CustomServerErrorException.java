package es.siriot.devtest.alten.inditex.catalog.be.exceptions;

public class CustomServerErrorException extends Exception {
	public CustomServerErrorException(String errorMessage) {
		super(errorMessage);
	}
}
