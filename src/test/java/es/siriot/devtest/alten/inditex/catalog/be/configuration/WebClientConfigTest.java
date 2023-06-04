package es.siriot.devtest.alten.inditex.catalog.be.configuration;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class WebClientConfigTest {

	@BeforeAll
	static void beforeAll() {
		log.info("Initializing tests");
	}

	@AfterAll()
	static void afterAll() { log.info("Finishing tests"); }

	@Test
	void when_call_web_client_should_be_web_client() {
		String methodName = "when_call_web_client_should_be_web_client";
		log.info("Checking test: " + methodName);

		WebClientConfig webClientConfig = new WebClientConfig();

		assertNotNull(webClientConfig, "webClient is null");
	}

}