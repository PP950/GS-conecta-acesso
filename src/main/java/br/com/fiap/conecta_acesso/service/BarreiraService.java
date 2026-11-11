
package br.com.fiap.conecta_acesso.service;

import br.com.fiap.conecta_acesso.domain.barreira.Barreira;
import br.com.fiap.conecta_acesso.domain.barreira.BarreiraRepository;
import br.com.fiap.conecta_acesso.domain.barreira.BarreiraRequestDTO;
import br.com.fiap.conecta_acesso.domain.barreira.BarreiraResponseDTO;
import br.com.fiap.conecta_acesso.domain.estabelecimento.Estabelecimento;
import br.com.fiap.conecta_acesso.domain.estabelecimento.EstabelecimentoRepository;
import br.com.fiap.conecta_acesso.infra.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public BarreiraResponseDTO cadastrar(BarreiraRequestDTO dto) {
        Estabelecimento estabelecimento = estabelecimentoRepository
                .findById(dto.estabelecimentoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Estabelecimento não encontrado"
                ));

        Barreira barreira = converterParaEntidade(dto, estabelecimento);

        Barreira salva = barreiraRepository.save(barreira);

        return converterParaDTO(salva);
    }

    public List<BarreiraResponseDTO> listarTodas() {
        return barreiraRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public BarreiraResponseDTO buscarPorId(Long id) {
        Barreira barreira = barreiraRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Barreira não encontrada"
                ));

        return converterParaDTO(barreira);
    }

    public BarreiraResponseDTO atualizar(Long id, BarreiraRequestDTO dto) {
        Barreira barreira = barreiraRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Barreira não encontrada"
                ));

        Estabelecimento estabelecimento = estabelecimentoRepository
                .findById(dto.estabelecimentoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Estabelecimento não encontrado"
                ));

        barreira.setEstabelecimento(estabelecimento);
        barreira.setTipo(dto.tipo());
        barreira.setDescricao(dto.descricao());
        barreira.setSeveridade(dto.severidade());
        barreira.setStatus(dto.status());

        Barreira atualizada = barreiraRepository.save(barreira);

        return converterParaDTO(atualizada);
    }

    public void excluir(Long id) {
        if (!barreiraRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException(
                    "Barreira não encontrada"
            );
        }

        barreiraRepository.deleteById(id);
    }

    private Barreira converterParaEntidade(
            BarreiraRequestDTO dto,
            Estabelecimento estabelecimento) {

        Barreira barreira = new Barreira();

        barreira.setEstabelecimento(estabelecimento);
        barreira.setTipo(dto.tipo());
        barreira.setDescricao(dto.descricao());
        barreira.setSeveridade(dto.severidade());
        barreira.setStatus(dto.status());

        return barreira;
    }

    private BarreiraResponseDTO converterParaDTO(Barreira barreira) {
        return new BarreiraResponseDTO(
                barreira.getId(),
                barreira.getEstabelecimento().getId(),
                barreira.getEstabelecimento().getNome(),
                barreira.getTipo(),
                barreira.getDescricao(),
                barreira.getSeveridade(),
                barreira.getStatus(),
                barreira.getDataRegistro()
        );
    }
}
