package com.ordemservico.com.demo.api.controller;

import com.ordemservico.com.demo.api.model.ComentarioInput;
import com.ordemservico.com.demo.api.model.ComentarioModel;
import com.ordemservico.com.demo.domain.exception.EntidadeNaoEncontradaException;
import com.ordemservico.com.demo.domain.model.Comentario;
import com.ordemservico.com.demo.domain.model.OrdemServico;
import com.ordemservico.com.demo.domain.repository.OrdemServicoRepository;
import com.ordemservico.com.demo.domain.service.GestaoOrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServicoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioModel adicionar(@PathVariable Long ordemServicoId, @Valid @RequestBody ComentarioInput comentarioInput) {
        Comentario comentario = gestaoOrdemServicoService.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());
        return toModel(comentario);
    }

    @GetMapping
    public List<ComentarioModel> listar(@PathVariable Long ordemServicoId) {
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
        return toCollectionModel(ordemServico.getComentarios());
    }

    private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios) {
        return comentarios.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    private ComentarioModel toModel(Comentario comentario) {
        return modelMapper.map(comentario, ComentarioModel.class);
    }

}
