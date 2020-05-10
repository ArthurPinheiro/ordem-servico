package com.ordemservico.com.demo.domain.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrdemServicoTest {

    private static final String MANUTENCAO = "manutencao";
    private static final String AVALIACAO = "avaliacao";

    @Test
    public void igualdadeDescricao() {
        assertThat(OrdemServico.builder().descricao(MANUTENCAO).build())
                .isEqualTo(OrdemServico.builder().descricao(MANUTENCAO).build());
    }

    @Test
    public void diferencaDescricao() {
        assertThat(OrdemServico.builder().descricao(MANUTENCAO).build())
                .isNotEqualTo(OrdemServico.builder().descricao(AVALIACAO).build());
    }
}
