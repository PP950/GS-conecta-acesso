package br.com.fiap.conecta_acesso.domain.relato;

import br.com.fiap.conecta_acesso.domain.relato.Relato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelatoRepository extends JpaRepository<Relato, Long> {
}