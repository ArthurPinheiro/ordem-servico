package com.ordemservico.com.demo.controller;

import com.ordemservico.com.demo.model.OrdemServico;
import com.ordemservico.com.demo.repository.OrdemServicoRepository;
import com.ordemservico.com.demo.service.GestaoOrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    @Autowired
    private GestaoOrdemServicoService service;

    @Autowired
    private OrdemServicoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@Valid @RequestBody OrdemServico ordemServico) {
        return service.criar(ordemServico);
    }

    @GetMapping
    public List<OrdemServico> listar() {
        return repository.findAll();
    }

    @GetMapping("/{ordemServicoId}")
    public ResponseEntity<OrdemServico> buscar(@PathVariable Long ordemServicoId) {
        Optional<OrdemServico> ordemServico = repository.findById(ordemServicoId);
        return ordemServico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
