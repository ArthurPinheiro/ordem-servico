package com.ordemservico.com.demo.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString(exclude = "id")
@EqualsAndHashCode(of = {"email"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 60)
    private String nome;

    @Email
    @Column(unique = true)
    private String email;

    @Column(name = "fone")
    @Size(max = 20)
    private String telefone;

}
