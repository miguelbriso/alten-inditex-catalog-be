package es.siriot.devtest.alten.inditex.catalog.be.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import java.io.IOException;

@Slf4j
public class ExceptionHandlerCustom {

	@ExceptionHandler(IOException.class)
	public void handleIOException(IOException e) {
		log.error("Handle IOException: {}", e.getMessage());
	}

	@ExceptionHandler(ServletException.class)
	public void handleServletException(ServletException e) {
		log.error("Handle ServletException: {}", e.getMessage());
	}

	@ExceptionHandler(CustomServerErrorException.class)
	public void handleCustomServerErrorException(CustomServerErrorException e) {
		log.error("Handle CustomServerErrorException: {}", e.getMessage());
	}

	@ExceptionHandler(CustomNotFoundException.class)
	public void handleCustomNotFoundException(CustomNotFoundException e) {
		log.error("Handle CustomServerErrorException: {}", e.getMessage());
	}


}
