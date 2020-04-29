package com.ordemservico.com.demo.controller;

import com.ordemservico.com.demo.model.Cliente;
import com.ordemservico.com.demo.repository.ClienteRepository;
import com.ordemservico.com.demo.service.CadastroClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Cliente salvar(@Valid @RequestBody Cliente cliente) {
        return service.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {
        if(!repository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteId);
        cliente = service.salvar(cliente);
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
}
