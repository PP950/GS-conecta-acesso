package br.com.fiap.conecta_acesso.domain.relato;

import java.time.LocalDateTime;

public record RelatoResponseDTO(
        Long id,
        Long estabelecimentoId,
        String estabelecimentoNome,
        String descricao,
        StatusRelato status,
        LocalDateTime dataRegistro
) {
}
