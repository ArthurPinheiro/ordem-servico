package com.ordemservico.com.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ordemservico.com.demo.domain.ValidationGroups;
import com.ordemservico.com.demo.domain.exception.NegocioException;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @ManyToOne @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
    private Cliente cliente;

    @NotBlank private String descricao;

    @NotNull private BigDecimal preco;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusOdemServico status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataAbertura;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataFinalizacao;

    @OneToMany(mappedBy = "ordemServico")
    private List<Comentario> comentarios;

    private boolean podeSerFinalizada() {
        return StatusOdemServico.ABERTA.equals(getStatus());
    }

    public void finalizar() {
        if(!podeSerFinalizada()){
            throw new NegocioException("Ordem de serviço não pode ser finalizada");
        }
        setStatus(StatusOdemServico.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }
}
