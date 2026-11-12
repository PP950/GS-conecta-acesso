package br.com.fiap.conecta_acesso.domain.parceiro;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ParceiroRequestDTO(
        @NotBlank
        String nome,

        @NotNull
        EspecialidadeParceiro especialidade,

        @NotBlank
        String descricao,

        @NotBlank
        String telefone,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String bairro,

        @NotBlank
        String cidade
) {
}