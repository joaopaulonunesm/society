package com.society.apis.backend;

import com.society.usecases.exceptions.UseCaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {

    private final MessageSource messageSource;

    @ExceptionHandler(value = UseCaseException.class)
    protected ResponseEntity<ResponseApiVO> handleBusinessException(final UseCaseException ex) {
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseApiVO().withError(messageSource.getMessage(ex.getCodigo(), ex.getParametros(), Locale.getDefault())));
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<ResponseApiVO> handleException(final Exception ex) {
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseApiVO().withError(ex.getMessage()));
    }
}
