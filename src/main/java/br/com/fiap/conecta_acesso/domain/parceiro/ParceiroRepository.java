package br.com.fiap.conecta_acesso.domain.parceiro;

import br.com.fiap.conecta_acesso.domain.parceiro.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {
}