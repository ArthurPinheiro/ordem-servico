package com.ordemservico.com.demo.domain.service;

import com.ordemservico.com.demo.domain.exception.EntidadeNaoEncontradaException;
import com.ordemservico.com.demo.domain.exception.NegocioException;
import com.ordemservico.com.demo.domain.model.Cliente;
import com.ordemservico.com.demo.domain.model.Comentario;
import com.ordemservico.com.demo.domain.model.OrdemServico;
import com.ordemservico.com.demo.domain.model.StatusOdemServico;
import com.ordemservico.com.demo.domain.repository.ClienteRepository;
import com.ordemservico.com.demo.domain.repository.ComentarioRepository;
import com.ordemservico.com.demo.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.OffsetDateTime;

@Service
public class GestaoOrdemServicoService {

    @Autowired
    private OrdemServicoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public OrdemServico criar(OrdemServico ordemServico) {
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());
        return repository.save(ordemServico);
    }

    public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
        OrdemServico ordemServico = buscarOrdemServico(ordemServicoId);

        Comentario comentario = new Comentario();
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServico);
        comentario.setDataEnvio(OffsetDateTime.now());

        return comentarioRepository.save(comentario);
    }

    public void finalizar(Long ordemServicoId) {
        OrdemServico ordemServico = buscarOrdemServico(ordemServicoId);
        ordemServico.finalizar();
        repository.save(ordemServico);
    }

    private OrdemServico buscarOrdemServico(@PathVariable Long ordemServicoId) {
        return repository.findById(ordemServicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
    }
}
