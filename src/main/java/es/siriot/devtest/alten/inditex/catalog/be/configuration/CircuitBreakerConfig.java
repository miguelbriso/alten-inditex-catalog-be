package es.siriot.devtest.alten.inditex.catalog.be.configuration;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED;

@Configuration
public class CircuitBreakerConfig {

    @Value("${resilience.slidingWindowSize}")
    private int slidingWindowSize;
    @Value("${resilience.waitDurationInOpenState}")
    private int waitDurationInOpenState;
    @Value("${resilience.minimumNumberOfCalls}")
    private int minimumNumberOfCalls;
    @Value("${resilience.failureRateThreshold}")
    private float failureRateThreshold;

    public CircuitBreakerConfigCustomizer getProductDetailCircuitBreakerConfig() {
        return CircuitBreakerConfigCustomizer
                .of("getProductDetail",
                        builder -> builder.slidingWindowSize(slidingWindowSize)
                                .slidingWindowType(COUNT_BASED)
                                .waitDurationInOpenState(Duration.ofSeconds(waitDurationInOpenState))
                                .minimumNumberOfCalls(minimumNumberOfCalls)
                                .failureRateThreshold(failureRateThreshold));
    }

    @Bean
    public CircuitBreakerConfigCustomizer getSimilarProductsCircuitBreakerConfig() {
        return CircuitBreakerConfigCustomizer
                .of("getSimilarProducts",
                        builder -> builder.slidingWindowSize(slidingWindowSize)
                                .slidingWindowType(COUNT_BASED)
                                .waitDurationInOpenState(Duration.ofSeconds(waitDurationInOpenState))
                                .minimumNumberOfCalls(minimumNumberOfCalls)
                                .failureRateThreshold(failureRateThreshold));
    }
}
