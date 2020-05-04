package com.ordemservico.com.demo.api.exceptionhandler;

import com.ordemservico.com.demo.domain.exception.EntidadeNaoEncontradaException;
import com.ordemservico.com.demo.domain.exception.NegocioException;
import lombok.var;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que trata as exceções
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocio(NegocioException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var problema = new Problema();

        problema.setStatus(status.value());
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(OffsetDateTime.now());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> entidadeHandleNegocio(NegocioException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var problema = new Problema();

        problema.setStatus(status.value());
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(OffsetDateTime.now());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<Campo> campos = new ArrayList<>();
        var problema = new Problema();

        for (ObjectError error: ex.getBindingResult().getAllErrors() ) {
            String nome = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();
            campos.add(new Campo(nome, mensagem));
        }
        problema.setStatus(status.value());
        problema.setTitulo("Um ou mais campos estão inválidos.");
        problema.setDataHora(OffsetDateTime.now());
        problema.setCampos(campos);
        return super.handleExceptionInternal(ex,problema, headers, status, request);
    }
}
