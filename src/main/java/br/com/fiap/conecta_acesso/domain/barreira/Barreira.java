package br.com.fiap.conecta_acesso.domain.barreira;

import br.com.fiap.conecta_acesso.domain.estabelecimento.Estabelecimento;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Barreira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estabelecimento_id", nullable = false)
    private Estabelecimento estabelecimento;

    @Enumerated(EnumType.STRING)
    private TipoBarreira tipo;

    private String descricao;

    private String severidade;

    private String status;

    @CreationTimestamp
    private LocalDateTime dataRegistro;
}