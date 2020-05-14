package com.ordemservico.com.demo.api.controller;

import com.ordemservico.com.demo.api.model.ClienteInput;
import com.ordemservico.com.demo.domain.model.Cliente;
import com.ordemservico.com.demo.domain.repository.ClienteRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

public class ClienteValidador implements Validator {

    private ClienteRepository repository;

    public ClienteValidador(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ClienteInput.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ClienteInput emailDuplicado = (ClienteInput) target;
        Optional<Cliente> clienteOptional = repository.findByEmail(emailDuplicado.getEmail());

        if(clienteOptional.isPresent()) {
            errors.rejectValue("email" , "Já existe um e-mail cadastrado com esse endereço");
        }
    }
}
