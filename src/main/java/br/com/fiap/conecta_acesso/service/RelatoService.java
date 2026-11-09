package br.com.fiap.conecta_acesso.service;

import br.com.fiap.conecta_acesso.domain.relato.Relato;
import br.com.fiap.conecta_acesso.domain.estabelecimento.EstabelecimentoRepository;
import br.com.fiap.conecta_acesso.domain.relato.RelatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelatoService {

    private final RelatoRepository relatoRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;

    public RelatoService(
            RelatoRepository relatoRepository,
            EstabelecimentoRepository estabelecimentoRepository) {
        this.relatoRepository = relatoRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    public Relato cadastrar(Relato relato) {
        Long estabelecimentoId = relato.getEstabelecimento().getId();

        if (!estabelecimentoRepository.existsById(estabelecimentoId)) {
            throw new RuntimeException("Estabelecimento não encontrado");
        }

        return relatoRepository.save(relato);
    }

    public List<Relato> listarTodos() {
        return relatoRepository.findAll();
    }

    public Optional<Relato> buscarPorId(Long id) {
        return relatoRepository.findById(id);
    }

    public Relato atualizar(Long id, Relato dados) {
        return relatoRepository.findById(id)
                .map(relato -> {
                    Long estabelecimentoId = dados.getEstabelecimento().getId();

                    if (!estabelecimentoRepository.existsById(estabelecimentoId)) {
                        throw new RuntimeException("Estabelecimento não encontrado");
                    }

                    relato.setEstabelecimento(dados.getEstabelecimento());
                    relato.setDescricao(dados.getDescricao());
                    relato.setStatus(dados.getStatus());

                    return relatoRepository.save(relato);
                })
                .orElseThrow(() -> new RuntimeException("Relato não encontrado"));
    }

    public void excluir(Long id) {
        if (!relatoRepository.existsById(id)) {
            throw new RuntimeException("Relato não encontrado");
        }

        relatoRepository.deleteById(id);
    }
}