package com.society.gerenciamento.api.middlewares;

import com.society.gerenciamento.application.exceptions.UseCaseException;
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
    protected ResponseApi<List> handleBusinessException(final UseCaseException ex) {
        ErrorResponse erro = new ErrorResponse(ex.getCodigo(), messageSource.getMessage(ex.getCodigo(), ex.getParametros(), Locale.getDefault()));
        return new ResponseApi(List.of(erro));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_REQUIRED)
    @ResponseBody
    public ResponseApi<List> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ErrorResponse> erros = new ArrayList<>();
        BindingResult bindingResult = exception.getBindingResult();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String campo = fieldError.getField();
            String mensagem = fieldError.getDefaultMessage();

            erros.add(new ErrorResponse(CODIGO_ERRO_PREENCHIMENTO, campo, mensagem));
        }

        return new ResponseApi(erros);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ResponseApi<List> handleException(final Exception ex) {
        return new ResponseApi(List.of(new ErrorResponse(CODIGO_ERRO_PREENCHIMENTO, ex.getMessage())));
    }
}
