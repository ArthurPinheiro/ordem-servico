package com.ordemservico.com.demo.domain.model;

import lombok.*;
import javax.persistence.*;
import java.time.OffsetDateTime;

@ToString(exclude = {"id"})
@Getter
@Setter
@EqualsAndHashCode(of = {"descricao"})
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
