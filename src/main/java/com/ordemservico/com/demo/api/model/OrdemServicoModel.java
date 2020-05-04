package com.ordemservico.com.demo.api.model;

import com.ordemservico.com.demo.domain.model.StatusOdemServico;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrdemServicoModel {
    private Long id;
    private ClienteResumoModel cliente;
    private String descricao;
    private StatusOdemServico status;
    private OffsetDateTime dataAbertura;
    private OffsetDateTime dataFinalizacao;
}
