package com.ordemservico.com.demo.domain.service;

import com.ordemservico.com.demo.domain.model.Cliente;
import com.ordemservico.com.demo.domain.repository.ClienteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CadastroClienteServiceTest {

    private static final Long ID = 1L;
    private static final String NOME = "Arthur";
    private static final String EMAIL = "arthur@gmail.com";
    private static final String TELEFONE = "84 9877-442187";

    @Autowired
    private ClienteRepository repository;

    @Test
    public void createClient() {
        Cliente cliente = Cliente.builder()
                .id(ID).nome(NOME).email(EMAIL).telefone(TELEFONE).build();
        this.repository.save(cliente);
        assertThat(cliente.getId()).isNotNull();
        assertThat(cliente.getNome()).isEqualTo(NOME);
        assertThat(cliente.getEmail()).isEqualTo(EMAIL);
        assertThat(cliente.getTelefone()).isEqualTo(TELEFONE);
    }

    @Test
    public void deleteClient() {
        Cliente cliente = Cliente.builder()
                .id(ID).nome(NOME).email(EMAIL).telefone(TELEFONE).build();
        this.repository.save(cliente);
        this.repository.delete(cliente);
        assertThat(repository.findById(cliente.getId()).isPresent()).isFalse();
    }

    @Test
    public void updateClient() {
        Cliente cliente = Cliente.builder()
                .id(ID).nome(NOME).email(EMAIL).telefone(TELEFONE).build();
        this.repository.save(cliente);
        cliente.setNome("Felipe");
        cliente.setEmail("felipe@gmail.com");
        this.repository.save(cliente);
        cliente = this.repository.getOne(cliente.getId());
        assertThat(cliente.getNome()).isEqualTo("Felipe");
        assertThat(cliente.getEmail()).isEqualTo("felipe@gmail.com");
    }
}
