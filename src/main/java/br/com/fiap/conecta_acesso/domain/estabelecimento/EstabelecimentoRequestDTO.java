package br.com.fiap.conecta_acesso.domain.estabelecimento;

import jakarta.validation.constraints.NotBlank;

public record EstabelecimentoRequestDTO(
        @NotBlank
        String nome,

        @NotBlank
        String categoria,

        @NotBlank
        String endereco,

        @NotBlank
        String bairro,

        @NotBlank
        String cidade,

        String contato
) {
}