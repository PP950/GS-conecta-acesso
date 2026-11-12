package br.com.fiap.conecta_acesso.domain.relato;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RelatoRequestDTO(
        @NotNull
        Long estabelecimentoId,

        @NotBlank
        String descricao,

        @NotNull
        StatusRelato status
) {
}