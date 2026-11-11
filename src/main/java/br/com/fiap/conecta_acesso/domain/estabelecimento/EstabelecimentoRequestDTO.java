package br.com.fiap.conecta_acesso.domain.estabelecimento;

public record EstabelecimentoRequestDTO(
        String nome,
        String categoria,
        String endereco,
        String bairro,
        String cidade,
        String contato
){
}

