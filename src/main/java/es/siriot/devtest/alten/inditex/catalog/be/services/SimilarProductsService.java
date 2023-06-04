package es.siriot.devtest.alten.inditex.catalog.be.services;

import es.siriot.devtest.alten.inditex.catalog.be.dto.ProductDetail;
import es.siriot.devtest.alten.inditex.catalog.be.exceptions.CustomNotFoundException;
import es.siriot.devtest.alten.inditex.catalog.be.exceptions.CustomServerErrorException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class SimilarProductsService {

    @Autowired
    private WebClient webClient;

    @Value("${external.product.service.url}")
    private String baseURL;

    @Cacheable(value = "similarProducts")
    @CircuitBreaker(name = "getSimilarProducts")
    public List<ProductDetail> getSimilarProducts(@NotNull String productId) {

        List<String> similarProductIds = webClient
                .get()
                .uri(baseURL + "/product/{productId}/similarids", productId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<String>>() {})
                .block();

        log.debug(similarProductIds.toString());

        return similarProductIds.parallelStream().collect(ArrayList::new, this::getProductDetail, ArrayList::addAll);
    }

    @Cacheable(value = "productDetail")
    @CircuitBreaker(name = "getProductDetail")
    private void getProductDetail(List<ProductDetail> list, @NotNull String productId) {

        ProductDetail productDetail = webClient
            .get()
            .uri(baseURL + "/product/{productId}", productId)
            .retrieve()
            .onStatus(
                    HttpStatus.INTERNAL_SERVER_ERROR::equals,
                    response -> response
                            .bodyToMono(String.class)
                            .map(CustomServerErrorException::new)
            )
            .onStatus(
                    HttpStatus.NOT_FOUND::equals,
                    response -> response
                            .bodyToMono(String.class)
                            .map(CustomNotFoundException::new)
            )
            .bodyToMono(ProductDetail.class)
            .block();

        if (productDetail == null) {
            log.debug("NotFoundException Product with id = " + productId + " will not be included in the response due it was not found.");
            return;
        }

        list.add(productDetail);
    }
}
