package com.ordemservico.com.demo.domain.model;

import lombok.*;
import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private OrdemServico ordemServico;
    private String descricao;
    private OffsetDateTime dataEnvio;
}
