package br.com.projetoapi.projeto.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column (name = "nome", length= 200, nullable = true)
    private String nome;

    @Column (name = "email", length= 50, nullable = true)
    private String email;

    @Column (name = "senha", columnDefinition = "TEXT", nullable = true)
    private String senha;

    @Column (name = "telefone", length= 15, nullable = true)
    private String telefone;


}
