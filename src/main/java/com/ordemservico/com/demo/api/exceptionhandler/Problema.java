package com.ordemservico.com.demo.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problema {
    private int status;
    private String titulo;
    private OffsetDateTime dataHora;
    private List<Campo> campos;
}
