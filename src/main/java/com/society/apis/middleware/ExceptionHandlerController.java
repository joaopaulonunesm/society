package com.society.apis.middleware;

import com.society.usecases.exceptions.UseCaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {

    private final MessageSource messageSource;

    @ExceptionHandler(value = UseCaseException.class)
    protected ResponseEntity<ResponseApiVO<List>> handleBusinessException(final UseCaseException ex) {

        ErrorResponse erro = new ErrorResponse(ex.getCodigo(), messageSource.getMessage(ex.getCodigo(), ex.getParametros(), Locale.getDefault()));

        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseApiVO(List.of(erro)));
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<ResponseApiVO<List>> handleException(final Exception ex) {
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseApiVO(List.of(ex.getMessage())));
    }
}
