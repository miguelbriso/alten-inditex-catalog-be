package es.siriot.devtest.alten.inditex.catalog.be.configuration;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.cache.CacheManager;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CacheConfigTest {

	@BeforeAll
	static void beforeAll() {
		log.info("Initializing tests");
	}

	@AfterAll()
	static void afterAll() { log.info("Finishing tests"); }

	@Test
	void when_call_cache_manager_should_be_cache_manager() {
		String methodName = "when_call_cache_manager_should_be_cache_manager";
		log.info("Checking test: " + methodName);

		List<String> caches = Arrays.asList("productDetail", "similarProducts");

		CacheManager cacheManager = new CacheConfig().cacheManager();

		assertEquals(true, cacheManager.getCacheNames().containsAll(caches), "list of caches not equal");
	}
}