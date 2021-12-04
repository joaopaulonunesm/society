package com.society.apis.backend;

import com.society.usecases.exceptions.UseCaseException;
import com.society.usecases.models.ResponseSocietyVO;
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
    protected ResponseEntity<ResponseSocietyVO> handleBusinessException(final UseCaseException ex) {
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseSocietyVO().withError(messageSource.getMessage(ex.getCodigo(), ex.getParametros(), Locale.getDefault())));
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<ResponseSocietyVO> handleException(final Exception ex) {
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseSocietyVO().withError(ex.getMessage()));
    }
}
