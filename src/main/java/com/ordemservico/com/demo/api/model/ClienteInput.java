package com.ordemservico.com.demo.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ClienteInput {

    @NotBlank
    private String nome;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String telefone;
}
