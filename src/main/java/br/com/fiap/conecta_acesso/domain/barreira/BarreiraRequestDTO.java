package br.com.fiap.conecta_acesso.domain.barreira;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BarreiraRequestDTO(
        @NotNull
        Long estabelecimentoId,

        @NotNull
        TipoBarreira tipo,

        @NotBlank
        String descricao,

        @NotNull
        SeveridadeBarreira severidade,

        @NotBlank
        String status
) {
}