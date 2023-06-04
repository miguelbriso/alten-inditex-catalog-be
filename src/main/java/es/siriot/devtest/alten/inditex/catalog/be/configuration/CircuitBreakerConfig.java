package es.siriot.devtest.alten.inditex.catalog.be.configuration;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED;

@Configuration
public class CircuitBreakerConfig {

    public CircuitBreakerConfigCustomizer getProductDetailCircuitBreakerConfig() {
        return CircuitBreakerConfigCustomizer
                .of("getProductDetail",
                        builder -> builder.slidingWindowSize(10)
                                .slidingWindowType(COUNT_BASED)
                                .waitDurationInOpenState(Duration.ofSeconds(5))
                                .minimumNumberOfCalls(5)
                                .failureRateThreshold(50.0f));
    }

    @Bean
    public CircuitBreakerConfigCustomizer getSimilarProductsCircuitBreakerConfig() {
        return CircuitBreakerConfigCustomizer
                .of("getSimilarProducts",
                        builder -> builder.slidingWindowSize(10)
                                .slidingWindowType(COUNT_BASED)
                                .waitDurationInOpenState(Duration.ofSeconds(5))
                                .minimumNumberOfCalls(5)
                                .failureRateThreshold(50.0f));
    }
}
