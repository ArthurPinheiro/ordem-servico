package com.ordemservico.com.demo.exceptionhandler;

import lombok.var;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que trata as exceções
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

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
        problema.setDataHora(LocalDateTime.now());
        problema.setCampos(campos);
        return super.handleExceptionInternal(ex,problema, headers, status, request);
    }
}
