
package br.com.fiap.conecta_acesso.domain.relato;

public record RelatoRequestDTO(
        Long estabelecimentoId,
        String descricao,
        StatusRelato status
) {
}
