package com.chalenges.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lady Cuizara
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ExceptionResponse> globalExceptionHandler(RuntimeException ex) {
        ExceptionResponse errorModel = new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<List<ExceptionResponse>> constraintViolationException(ConstraintViolationException exception) {

        List<ExceptionResponse> responseList = exception.getConstraintViolations().stream()
                .map((error) -> new ExceptionResponse(error.getMessage()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(responseList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<List<ExceptionResponse>> methodArgumentNotValidException(MethodArgumentNotValidException exception) {

        List<ExceptionResponse> responseList = exception.getBindingResult().getFieldErrors().stream()
                .map((error) -> new ExceptionResponse(error.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(responseList, HttpStatus.BAD_REQUEST);
    }
}