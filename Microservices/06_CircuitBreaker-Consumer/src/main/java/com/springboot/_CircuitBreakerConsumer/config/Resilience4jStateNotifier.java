package com.springboot._CircuitBreakerConsumer.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class Resilience4jStateNotifier {

  @Autowired CircuitBreakerRegistry circuitBreakerRegistry;

  @PostConstruct
  private void postConstruct() {
    circuitBreakerRegistry
        .getAllCircuitBreakers()
        .forEach(
            circuitBreaker -> {
              circuitBreaker
                  .getEventPublisher()
                  .onStateTransition((event) -> log.info(event.toString()));
            });
  }
}
