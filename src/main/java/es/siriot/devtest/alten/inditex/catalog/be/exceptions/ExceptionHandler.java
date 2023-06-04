package es.siriot.devtest.alten.inditex.catalog.be.exceptions;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(IOException.class)
	public void handleIOException(IOException e) {
		log.error("Handle IOException: {}", e.getMessage());
	}

}
