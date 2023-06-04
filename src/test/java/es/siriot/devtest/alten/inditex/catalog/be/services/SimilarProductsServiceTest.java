package es.siriot.devtest.alten.inditex.catalog.be.services;

import es.siriot.devtest.alten.inditex.catalog.be.dto.ProductDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Slf4j
class SimilarProductsServiceTest {

	@Mock
	private WebClient webClient;
	@Mock
	private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
	@Mock
	private WebClient.RequestHeadersSpec requestHeadersSpec;
	@Mock
	private WebClient.ResponseSpec responseSpec;
	@Mock
	private Mono<List<String>> listMono;
	@Mock
	private Mono<ProductDetail> productDetailMono;
	@InjectMocks
	private SimilarProductsService similarProductsService;

	private List<String> similarIdsList = Arrays.asList("1", "2", "3");
	private List<ProductDetail> productDetailList;

	@BeforeAll
	static void beforeAll() { log.info("Initializing tests"); }
	@AfterAll
	static void afterAll() { log.info("Finishing tests"); }

	@BeforeEach
	void beforeEach() {

		MockitoAnnotations.openMocks(this);
		productDetailList = new ArrayList<>();

		for (int i = 1; i < 6; i++)
			productDetailList.add(
					ProductDetail.builder()
							.id("" + i)
							.name("Product " + i)
							.price(19.99f)
							.availability(true)
							.build()
			);
	}

	@Test
	void when_call_get_similar_products_should_be_similar_products_list() {
		String methodName = "when_call_get_similar_products_should_be_similar_products_list";
		log.info("Checking test: " + methodName);

		when(webClient.get()).thenReturn(requestHeadersUriSpec);
		when(requestHeadersUriSpec.uri(anyString(), any(Object.class))).thenReturn(requestHeadersSpec);
		when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(new ParameterizedTypeReference<List<String>>() {})).thenReturn(listMono);
		when(responseSpec.bodyToMono(ProductDetail.class)).thenReturn(productDetailMono);
		when(listMono.block()).thenReturn(similarIdsList);
		when(productDetailMono.block()).thenReturn(productDetailList.get(0));

		List<ProductDetail> productDetailListResponse = similarProductsService.getSimilarProducts("1");

		assertEquals(3, productDetailListResponse.size(), "list size not equal");
	}

	@Test
	void when_call_get_similar_products_should_be_empty_similar_products_list() {
		String methodName = "when_call_get_similar_products_should_be_empty_similar_products_list";
		log.info("Checking test: " + methodName);

		when(webClient.get()).thenReturn(requestHeadersUriSpec);
		when(requestHeadersUriSpec.uri(anyString(), any(Object.class))).thenReturn(requestHeadersSpec);
		when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(new ParameterizedTypeReference<List<String>>() {})).thenReturn(listMono);
		when(responseSpec.bodyToMono(ProductDetail.class)).thenReturn(productDetailMono);
		when(listMono.block()).thenReturn(similarIdsList);
		when(productDetailMono.block()).thenReturn(null);

		List<ProductDetail> productDetailListResponse = similarProductsService.getSimilarProducts("2");

		assertEquals(0, productDetailListResponse.size(), "list size not equal");
	}

}