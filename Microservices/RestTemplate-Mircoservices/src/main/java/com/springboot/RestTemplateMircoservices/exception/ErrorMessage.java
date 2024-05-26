package com.springboot.RestTemplateMircoservices.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ErrorMessage {

    private String message;
    private LocalDateTime time;
    private int errorCode;
}