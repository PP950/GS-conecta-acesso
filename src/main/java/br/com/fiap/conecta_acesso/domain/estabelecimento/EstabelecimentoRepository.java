package br.com.fiap.conecta_acesso.domain.estabelecimento;

import br.com.fiap.conecta_acesso.domain.estabelecimento.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

}