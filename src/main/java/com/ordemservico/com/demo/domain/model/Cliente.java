package com.ordemservico.com.demo.domain.model;

import com.ordemservico.com.demo.domain.ValidationGroups;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString(exclude = "id")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Entity
public class Cliente {

    @NotNull(groups = ValidationGroups.ClienteId.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 60) private String nome;

    @NotBlank @Email private String email;

    @Column(name = "fone") @Size(max = 20)
    private String telefone;
}
