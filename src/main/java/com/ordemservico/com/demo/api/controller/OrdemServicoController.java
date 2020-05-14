package com.ordemservico.com.demo.api.controller;

import com.ordemservico.com.demo.api.model.OrdemServicoInput;
import com.ordemservico.com.demo.api.model.OrdemServicoModel;
import com.ordemservico.com.demo.domain.model.OrdemServico;
import com.ordemservico.com.demo.domain.repository.OrdemServicoRepository;
import com.ordemservico.com.demo.domain.service.GestaoOrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    @Autowired
    private GestaoOrdemServicoService service;

    @Autowired
    private OrdemServicoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServicoModel criar(@Valid @RequestBody OrdemServicoInput ordemServicoInput) {
        OrdemServico ordemServico = toEntity(ordemServicoInput);
        return toModel(service.criar(ordemServico));
    }

    @GetMapping
    public List<OrdemServicoModel> listar() {
        return toCollectionModel(repository.findAll());
    }

    @GetMapping(value = "/ativas")
    public List<OrdemServico> listarStatusAberta() {
        return repository.findByStatusAtivo();
    }

    @GetMapping("/{ordemServicoId}")
    public ResponseEntity<OrdemServicoModel> buscar(@PathVariable Long ordemServicoId) {
        Optional<OrdemServico> ordemServico = repository.findById(ordemServicoId);

        if(ordemServico.isPresent()) {
            OrdemServicoModel ordemServicoModel = toModel(ordemServico.get());
            return ResponseEntity.ok(ordemServicoModel);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{ordemServicoId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long ordemServicoId){
        service.finalizar(ordemServicoId);
    }

    private OrdemServicoModel toModel(OrdemServico ordemServico) {
        return modelMapper.map(ordemServico, OrdemServicoModel.class);
    }

    private List<OrdemServicoModel> toCollectionModel(List<OrdemServico> ordemServicos) {
        return ordemServicos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    private OrdemServico toEntity(OrdemServicoInput ordemServicoInput) {
        return modelMapper.map(ordemServicoInput, OrdemServico.class);
    }
}
