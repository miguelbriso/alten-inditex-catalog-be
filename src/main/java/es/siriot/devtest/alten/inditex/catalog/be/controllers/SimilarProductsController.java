package es.siriot.devtest.alten.inditex.catalog.be.controllers;

import es.siriot.devtest.alten.inditex.catalog.be.dto.ProductDetail;
import es.siriot.devtest.alten.inditex.catalog.be.exceptions.ProductNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface SimilarProductsController {
	@GetMapping("/product/{id}/similar")
	ResponseEntity<List<ProductDetail>> getSimilarProducts(@NotNull @PathVariable("id") String id) throws ProductNotFoundException;

}
