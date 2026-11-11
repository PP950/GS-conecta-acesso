package br.com.fiap.conecta_acesso.domain.barreira;

public record BarreiraRequestDTO(
        Long estabelecimentoId,
        TipoBarreira tipo,
        String descricao,
        String severidade,
        String status
) {
}