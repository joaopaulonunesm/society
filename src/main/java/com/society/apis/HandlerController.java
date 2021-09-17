package com.society.apis;

import com.society.exceptions.BusinessException;
import com.society.features.society.domain.ResponseSocietyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerController {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> businessExceptionHandler(BusinessException businessException){
        return ResponseEntity
                .status(businessException.getHttpStatus().value())
                .body(ResponseSocietyDTO.withError(businessException.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionHandler(Exception exception){
        return ResponseEntity
                .badRequest()
                .body(ResponseSocietyDTO.withError(exception.getMessage()));
    }
}
