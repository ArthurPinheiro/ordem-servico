package com.ordemservico.com.demo.domain.service;

import com.ordemservico.com.demo.domain.exception.NegocioException;
import com.ordemservico.com.demo.domain.model.Cliente;
import com.ordemservico.com.demo.domain.model.OrdemServico;
import com.ordemservico.com.demo.domain.model.StatusOdemServico;
import com.ordemservico.com.demo.domain.repository.ClienteRepository;
import com.ordemservico.com.demo.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class GestaoOrdemServicoService {

    @Autowired
    private OrdemServicoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    public OrdemServico criar (OrdemServico ordemServico) {
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());
        return repository.save(ordemServico);
    }
}
