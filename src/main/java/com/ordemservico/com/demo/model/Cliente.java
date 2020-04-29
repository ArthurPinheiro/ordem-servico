package com.ordemservico.com.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString(exclude = "id")
@EqualsAndHashCode(exclude ={ "nome","email", "telefone" })
@NoArgsConstructor
@Entity
public class Cliente {
// git remote add origin https://github.com/ArthurPinheiro/ordem-servico.git
//git push -u origin master
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 60)
    private String nome;

    @NotBlank
    @Email
    private String email;

    @Column(name = "fone")
    @Size(max = 20)
    private String telefone;
}
