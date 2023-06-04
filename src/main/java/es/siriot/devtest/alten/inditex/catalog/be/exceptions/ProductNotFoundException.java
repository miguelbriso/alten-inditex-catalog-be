package es.siriot.devtest.alten.inditex.catalog.be.exceptions;

public class ProductNotFoundException extends Exception {
	public ProductNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
