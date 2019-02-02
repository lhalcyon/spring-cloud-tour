package com.example.server.service;

import com.example.server.domain.Book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;

import javax.annotation.PostConstruct;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

@Service
public class CircuitbreakerService {

    private static final Logger logger = LoggerFactory.getLogger(CircuitbreakerService.class);

    private CircuitBreaker circuitBreaker;

    @PostConstruct
    public void initCircuitbreaker() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .ringBufferSizeInClosedState(4)
                .ringBufferSizeInHalfOpenState(2)
                .waitDurationInOpenState(Duration.ofMillis(10000))
                .recordFailure(throwable -> !(throwable instanceof NumberFormatException))
                .build();
        circuitBreaker = CircuitBreaker.of("CircuitbreakerService", config);
        circuitBreaker.getEventPublisher()
                .onEvent(event -> logger.info(event.toString()));
    }

    public Book queryBook(String param) throws Exception {
        logger.info("circuit breaker current state is {}",order2State(circuitBreaker.getState().getOrder()));
        CircuitBreaker.Metrics metrics = circuitBreaker.getMetrics();
        logger.info("failure rate = {},failed = {},success = {}",metrics.getFailureRate(),metrics.getNumberOfFailedCalls(),metrics.getNumberOfSuccessfulCalls());
        return circuitBreaker.executeCallable(() -> {
            if ("IllegalArgumentException".equalsIgnoreCase(param)) {
                throw new IllegalArgumentException();
            }
            if ("RuntimeException".equalsIgnoreCase(param)) {
                throw new RuntimeException();
            }
            if ("1".equalsIgnoreCase(param)){
                throw new NumberFormatException();
            }
            return new Book("Richard A. Knaak", "The war of the Ancients Trilogy");
        });
    }

    private String order2State(int order) {
        switch (order) {
            case 0:
                return "CLOSED";
            case 1:
                return "OPEN";
            case 2:
                return "HALF_OPEN";
            case 3:
                return "DISABLED";
            case 4:
                return "FORCED_OPEN";
            default:
                return "";
        }
    }
}
