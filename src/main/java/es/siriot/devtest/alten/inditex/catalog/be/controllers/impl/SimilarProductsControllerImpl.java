package es.siriot.devtest.alten.inditex.catalog.be.controllers.impl;

import es.siriot.devtest.alten.inditex.catalog.be.exceptions.ExceptionHandler;
import es.siriot.devtest.alten.inditex.catalog.be.controllers.SimilarProductsController;
import es.siriot.devtest.alten.inditex.catalog.be.dto.ProductDetail;
import es.siriot.devtest.alten.inditex.catalog.be.exceptions.ProductNotFoundException;
import es.siriot.devtest.alten.inditex.catalog.be.services.SimilarProductsService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
public class SimilarProductsControllerImpl extends ExceptionHandler implements SimilarProductsController {

    private SimilarProductsService similarProductsService;

    @Override
    @GetMapping("/product/{productId}/similar")
    public ResponseEntity<List<ProductDetail>> getSimilarProducts(@NotNull @PathVariable("productId") String productId) throws ProductNotFoundException {
        log.debug("Call [getSimilarProducts] productId = {}.", productId);

        List<ProductDetail> similarProducts = similarProductsService.getSimilarProducts(productId);

        if (similarProducts.isEmpty()) {
            log.debug("The product {} have not similar products.", productId);
            throw new ProductNotFoundException("The product " + productId + " has not similar products.");
        }

        return ResponseEntity.ok(similarProducts);
    }

}
