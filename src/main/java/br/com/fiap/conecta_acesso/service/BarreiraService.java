package br.com.fiap.conecta_acesso.service;

import br.com.fiap.conecta_acesso.domain.barreira.Barreira;
import br.com.fiap.conecta_acesso.domain.barreira.BarreiraRepository;
import br.com.fiap.conecta_acesso.domain.estabelecimento.EstabelecimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarreiraService {

    private final BarreiraRepository barreiraRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;

    public BarreiraService(
            BarreiraRepository barreiraRepository,
            EstabelecimentoRepository estabelecimentoRepository) {
        this.barreiraRepository = barreiraRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    public Barreira cadastrar(Barreira barreira) {
        Long estabelecimentoId = barreira.getEstabelecimento().getId();

        if (!estabelecimentoRepository.existsById(estabelecimentoId)) {
            throw new RuntimeException("Estabelecimento não encontrado");
        }

        return barreiraRepository.save(barreira);
    }

    public List<Barreira> listarTodas() {
        return barreiraRepository.findAll();
    }

    public Optional<Barreira> buscarPorId(Long id) {
        return barreiraRepository.findById(id);
    }

    public Barreira atualizar(Long id, Barreira dados) {
        return barreiraRepository.findById(id)
                .map(barreira -> {
                    Long estabelecimentoId = dados.getEstabelecimento().getId();

                    if (!estabelecimentoRepository.existsById(estabelecimentoId)) {
                        throw new RuntimeException("Estabelecimento não encontrado");
                    }

                    barreira.setEstabelecimento(dados.getEstabelecimento());
                    barreira.setTipo(dados.getTipo());
                    barreira.setDescricao(dados.getDescricao());
                    barreira.setSeveridade(dados.getSeveridade());
                    barreira.setStatus(dados.getStatus());

                    return barreiraRepository.save(barreira);
                })
                .orElseThrow(() -> new RuntimeException("Barreira não encontrada"));
    }

    public void excluir(Long id) {
        if (!barreiraRepository.existsById(id)) {
            throw new RuntimeException("Barreira não encontrada");
        }

        barreiraRepository.deleteById(id);
    }
}