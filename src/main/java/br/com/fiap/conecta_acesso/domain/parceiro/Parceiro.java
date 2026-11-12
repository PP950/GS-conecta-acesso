package br.com.fiap.conecta_acesso.domain.parceiro;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EspecialidadeParceiro especialidade;

    @Column(length = 1000)
    private String descricao;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String email;

    private String bairro;

    private String cidade;
}