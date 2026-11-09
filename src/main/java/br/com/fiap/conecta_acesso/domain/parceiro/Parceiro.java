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

    private String nome;

    @Enumerated(EnumType.STRING)
    private EspecialidadeParceiro especialidade;

    private String descricao;

    private String telefone;

    private String email;

    private String bairro;

    private String cidade;
}