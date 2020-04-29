package com.ordemservico.com.demo.exceptionhandler;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Problema {
    private int status;
    private String titulo;
    private LocalDateTime dataHora;
    private List<Campo> campos;
}
