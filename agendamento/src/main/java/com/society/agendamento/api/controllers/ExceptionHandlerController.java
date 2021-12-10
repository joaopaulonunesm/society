package com.society.agendamento.api.controllers;

import com.society.agendamento.api.models.ApiResponse;
import com.society.agendamento.api.models.ErroResponse;
import com.society.agendamento.application.exceptions.UseCaseException;
import com.society.agendamento.domain.exceptions.DomainException;
import com.society.agendamento.infrastructure.exceptions.DataProviderException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {

    public static final String CODIGO_ERRO_PREENCHIMENTO = "agendamento.error.campo-preenchido-incorretamente";
    public static final String CODIGO_ERRO_GENERICO = "agendamento.error.erro-generico";

    private final MessageSource messageSource;

    @ExceptionHandler(value = UseCaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ApiResponse<List> handleBusinessException(final UseCaseException ex) {
        ErroResponse erro = new ErroResponse(ex.getCodigo(), messageSource.getMessage(ex.getCodigo(), ex.getParametros(), Locale.getDefault()));
        return ApiResponse.comErros(List.of(erro));
    }

    @ExceptionHandler(value = DomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ApiResponse<List> handleDomainException(final DomainException ex) {
        ErroResponse erro = new ErroResponse(ex.getCodigo(), messageSource.getMessage(ex.getCodigo(), ex.getParametros(), Locale.getDefault()));
        return ApiResponse.comErros(List.of(erro));
    }

    @ExceptionHandler(value = DataProviderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ApiResponse<List> handleDataProviderException(final DataProviderException ex) {
        ErroResponse erro = new ErroResponse(ex.getCodigo(), messageSource.getMessage(ex.getCodigo(), ex.getParametros(), Locale.getDefault()));
        return ApiResponse.comErros(List.of(erro));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_REQUIRED)
    @ResponseBody
    public ApiResponse<List> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ErroResponse> erros = new ArrayList<>();
        BindingResult bindingResult = exception.getBindingResult();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String campo = fieldError.getField();
            String mensagem = fieldError.getDefaultMessage();

            erros.add(new ErroResponse(CODIGO_ERRO_PREENCHIMENTO, campo, mensagem));
        }

        return ApiResponse.comErros(erros);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ApiResponse<List> handleException(final Exception ex) {
        return ApiResponse.comErros(List.of(new ErroResponse(CODIGO_ERRO_GENERICO, ex.getMessage())));
    }
}
