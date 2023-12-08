package ru.zimch.ajaxtest.controllers.advices;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * Перехватчик всех ошибок :)
 * Можно было бы сделать что-то конкретнее, сделал вот так..
 */
@RestControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler(Exception.class)
    public Map<String, String> foundSomeException(Exception exception) {
        return Map.of(
                "Error", exception.getLocalizedMessage()
        );
    }
}
