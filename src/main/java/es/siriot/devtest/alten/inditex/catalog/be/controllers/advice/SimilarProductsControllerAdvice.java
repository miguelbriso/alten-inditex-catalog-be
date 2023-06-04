package es.siriot.devtest.alten.inditex.catalog.be.controllers.advice;

import es.siriot.devtest.alten.inditex.catalog.be.exceptions.ProductNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class SimilarProductsControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException e) {
        log.debug("Bad request (ConstraintViolationException): {}", e.getMessage());
        return e.getMessage();
    }

//    @ExceptionHandler({CallNotPermittedException.class})
//    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
//    public String handleCallNotPermittedException(Exception e) {
//        log.debug("Service unavailable (CallNotPermittedException): {}", e.getMessage());
//        return e.getMessage();
//    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleProductNotFoundException(ProductNotFoundException e) {
        log.debug("Not found (ProductNotFoundException): {}", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleUnknownException(Exception e) {
        log.error("Unexpected exception.", e);
    }

}
