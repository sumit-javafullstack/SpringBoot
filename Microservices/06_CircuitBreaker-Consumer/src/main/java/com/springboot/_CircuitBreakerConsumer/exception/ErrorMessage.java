package com.springboot._CircuitBreakerConsumer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class ErrorMessage {

    private String message;
    private LocalDateTime time;
    private int errorCode;
}