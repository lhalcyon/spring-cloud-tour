package com.example.server.service;

import com.example.server.domain.Book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;

import javax.annotation.PostConstruct;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiter.Metrics;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;

@Service
public class RateLimiterService {

    private static final Logger logger = LoggerFactory.getLogger(RateLimiterService.class);
    private RateLimiter rateLimiter;

    @PostConstruct
    public void initCircuitbreaker() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofMillis(15000))
                .limitForPeriod(5)
                .timeoutDuration(Duration.ofMillis(5000))
                .build();

        // Or create RateLimiter directly
        rateLimiter = RateLimiter.of("RateLimiterService", config);
        rateLimiter.getEventPublisher().onEvent(event -> logger.info(event.toString()));

    }

    public Book queryBook(String param) throws Exception {
        Metrics metrics = rateLimiter.getMetrics();
        logger.info("getNumberOfWaitingThreads = {},getAvailablePermissions = {}",metrics.getAvailablePermissions());

        return rateLimiter.executeCallable(() -> {
            if ("".equalsIgnoreCase(param)){
                throw new Exception();
            }
            if ("delay".equalsIgnoreCase(param)){
                Thread.sleep(2000);
                return new Book("Richard A. Knaak", "The war of the Ancients Trilogy");
            }
            return new Book("Richard A. Knaak", "The war of the Ancients Trilogy");
        });
    }
}
