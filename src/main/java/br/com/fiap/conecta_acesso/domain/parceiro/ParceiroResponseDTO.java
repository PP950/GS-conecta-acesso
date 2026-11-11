
package br.com.fiap.conecta_acesso.domain.parceiro;

public record ParceiroResponseDTO(
        Long id,
        String nome,
        EspecialidadeParceiro especialidade,
        String descricao,
        String telefone,
        String email,
        String bairro,
        String cidade
) {
}
