package com.ordemservico.com.demo.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class OrdemServicoInput {

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal preco;

    @NotNull
    @Valid
    private ClienteIdInput cliente;

}
