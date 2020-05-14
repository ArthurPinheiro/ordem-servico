package com.ordemservico.com.demo.domain.repository;

import com.ordemservico.com.demo.domain.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    @Query("select p from ordem_servico p where p.status = 'ABERTA' ")
    List<OrdemServico> findByStatusAtivo();
}
