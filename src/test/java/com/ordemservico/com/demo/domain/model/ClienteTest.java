package com.ordemservico.com.demo.domain.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClienteTest {

    private static final String EMAIL1 = "arthur@gmail.com";
    private static final String EMAIL2 = "arthur.pinheiro@gmail.com";

    @Test
    public void verificaEmailsIguais() {
        assertThat(Cliente.builder().email(EMAIL1))
                .isNotEqualTo(Cliente.builder().email(EMAIL2));
    }

}
