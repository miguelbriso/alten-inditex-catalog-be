package es.siriot.devtest.alten.inditex.catalog.be.controllers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.siriot.devtest.alten.inditex.catalog.be.controllers.SimilarProductsController;
import es.siriot.devtest.alten.inditex.catalog.be.dto.ProductDetail;
import es.siriot.devtest.alten.inditex.catalog.be.services.SimilarProductsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(SimilarProductsController.class)
class SimilarProductsControllerImplTest {

	@MockBean
	private SimilarProductsService similarProductsService;
	@Autowired
	private MockMvc mockMvc;
	private AutoCloseable closeable;
	private List<ProductDetail> productDetailList;
	private ObjectMapper mapper;

	@BeforeAll
	static void beforeAll() { log.info("Initializing tests"); }

	@AfterAll
	static void afterAll() { log.info("Finishing tests"); }

	@BeforeEach
	void beforeEach() {

		closeable = MockitoAnnotations.openMocks(this);

		mapper = new ObjectMapper();
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

	@AfterEach
	void afterEach() throws Exception { closeable.close(); }

	@Test
	void when_call_get_similar_products_should_be_list_of_products() throws Exception {
		String methodName = "when_call_get_similar_products_should_be_list_of_products";
		log.info("Checking test: " + methodName);

		String JSONResponse = mapper.writeValueAsString(productDetailList);

		when(similarProductsService.getSimilarProducts("1")).thenReturn(productDetailList);

		mockMvc.perform(get("/product/1/similar"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(JSONResponse));
	}

	@Test
	void when_call_get_similar_products_should_be_empty_list_of_products_and_http_status_not_found() throws Exception {
		String methodName = "when_call_get_similar_products_should_be_empty_list_of_products_and_http_status_not_found";
		log.info("Checking test: " + methodName);

		when(similarProductsService.getSimilarProducts("2")).thenReturn(new ArrayList<>());

		mockMvc.perform(get("/product/2/similar"))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

}