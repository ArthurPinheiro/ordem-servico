package com.ordemservico.com.demo.domain.service;

import com.ordemservico.com.demo.domain.exception.NegocioException;
import com.ordemservico.com.demo.domain.model.Cliente;
import com.ordemservico.com.demo.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente salvar(Cliente cliente) {
        Optional<Cliente> clienteExistente = repository.findByEmail(cliente.getEmail());
        if (clienteExistente.isPresent()) {
            throw new NegocioException("Já existe um cliente com esse e-mail");
        }
        return repository.save(cliente);
    }

    public void excluir(Long id){
        repository.deleteById(id);
    }
}
