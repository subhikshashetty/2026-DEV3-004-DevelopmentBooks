package com.bnppf.bookShoppingCart.exception;

import com.bnppf.bookShoppingCart.model.ErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tools.jackson.databind.exc.InvalidFormatException;

import java.util.Arrays;
import java.util.stream.Collectors;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEnum(HttpMessageNotReadableException ex) {

        Throwable cause = ex.getCause();

        if (cause instanceof InvalidFormatException invalidFormatException
                && invalidFormatException.getTargetType().isEnum()) {

            String invalidValue = invalidFormatException.getValue().toString();

            String acceptedValues = Arrays.stream(
                            invalidFormatException.getTargetType().getEnumConstants())
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));

            String message = String.format(
                    "Invalid book name '%s'. Accepted values are: [%s]",
                    invalidValue, acceptedValues);

            ErrorResponse error = new ErrorResponse(
                    message,
                    "Invalid Enum Value",
                    HttpStatus.BAD_REQUEST.value()
            );

            return ResponseEntity.badRequest().body(error);
        }

        ErrorResponse error = new ErrorResponse(
                "Request body is invalid",
                "Malformed JSON request",
                HttpStatus.BAD_REQUEST.value()

        );

        return ResponseEntity.badRequest().body(error);
    }
}
