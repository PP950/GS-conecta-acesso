package br.com.fiap.conecta_acesso.domain.barreira;

import java.time.LocalDateTime;

public record BarreiraResponseDTO(
        Long id,
        Long estabelecimentoId,
        String estabelecimentoNome,
        TipoBarreira tipo,
        String descricao,
        SeveridadeBarreira severidade,
        String status,
        LocalDateTime dataRegistro
) {
}