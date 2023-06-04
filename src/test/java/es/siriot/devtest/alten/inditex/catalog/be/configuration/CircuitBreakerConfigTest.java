package es.siriot.devtest.alten.inditex.catalog.be.configuration;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.cache.CacheManager;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CircuitBreakerConfigTest {

	@BeforeAll
	static void beforeAll() {
		log.info("Initializing tests");
	}

	@AfterAll()
	static void afterAll() { log.info("Finishing tests"); }

	@Test
	void when_call_get_product_detail_circuit_breaker_config_should_be_circuit_breaker_config_customizer() {
		String methodName = "when_call_get_product_detail_circuit_breaker_config_should_be_circuit_breaker_config_customizer";
		log.info("Checking test: " + methodName);

		String name = "getProductDetail";

		CircuitBreakerConfig circuitBreakerConfig = new CircuitBreakerConfig();

		assertEquals(name, circuitBreakerConfig.getProductDetailCircuitBreakerConfig().name(), "name not equal");
	}

	@Test
	void when_call_get_similar_products_circuit_breaker_config_should_be_circuit_breaker_config_customizer() {
		String methodName = "when_call_get_similar_products_circuit_breaker_config_should_be_circuit_breaker_config_customizer";
		log.info("Checking test: " + methodName);

		String name = "getSimilarProducts";

		CircuitBreakerConfig circuitBreakerConfig = new CircuitBreakerConfig();

		assertEquals(name, circuitBreakerConfig.getSimilarProductsCircuitBreakerConfig().name(), "name not equal");
	}

}