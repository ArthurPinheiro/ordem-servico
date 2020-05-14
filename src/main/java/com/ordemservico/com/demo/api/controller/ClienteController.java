package com.ordemservico.com.demo.api.controller;

import com.ordemservico.com.demo.api.model.ClienteInput;
import com.ordemservico.com.demo.api.model.ClienteModel;
import com.ordemservico.com.demo.domain.model.Cliente;
import com.ordemservico.com.demo.domain.repository.ClienteRepository;
import com.ordemservico.com.demo.domain.service.CadastroClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private CadastroClienteService service;

    @Autowired
    private ModelMapper modelMapper;

    @InitBinder("clienteInput")
    public void initBinder(WebDataBinder binder){
        binder.addValidators(new ClienteValidador(repository));
    }

    @GetMapping
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long idCliente) {
        Optional<Cliente> cliente = repository.findById(idCliente);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteModel salvar(@Valid @RequestBody ClienteInput clienteInput) {
        Cliente cliente = toEntity(clienteInput);
        return toModel(repository.save(cliente));
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @Valid @RequestBody ClienteInput clienteInput) {
        if(!repository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        Cliente cliente = toEntity(clienteInput);
        this.repository.save(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<?> remover(@PathVariable Long clienteId) {
        if(!repository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        service.excluir(clienteId);
        return ResponseEntity.noContent().build();
    }

    private ClienteModel toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteModel.class);
    }

    private Cliente toEntity(ClienteInput clienteInput) {
        return modelMapper.map(clienteInput, Cliente.class);
    }
}
