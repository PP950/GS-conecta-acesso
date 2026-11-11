
package br.com.fiap.conecta_acesso.service;

import br.com.fiap.conecta_acesso.domain.relato.Relato;
import br.com.fiap.conecta_acesso.domain.relato.RelatoRepository;
import br.com.fiap.conecta_acesso.domain.relato.RelatoRequestDTO;
import br.com.fiap.conecta_acesso.domain.relato.RelatoResponseDTO;
import br.com.fiap.conecta_acesso.domain.estabelecimento.Estabelecimento;
import br.com.fiap.conecta_acesso.domain.estabelecimento.EstabelecimentoRepository;
import br.com.fiap.conecta_acesso.infra.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public RelatoResponseDTO cadastrar(RelatoRequestDTO dto) {
        Estabelecimento estabelecimento = estabelecimentoRepository
                .findById(dto.estabelecimentoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Estabelecimento não encontrado"
                ));

        Relato relato = converterParaEntidade(dto, estabelecimento);

        Relato salvo = relatoRepository.save(relato);

        return converterParaDTO(salvo);
    }

    public List<RelatoResponseDTO> listarTodos() {
        return relatoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public RelatoResponseDTO buscarPorId(Long id) {
        Relato relato = relatoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Relato não encontrado"
                ));

        return converterParaDTO(relato);
    }

    public RelatoResponseDTO atualizar(Long id, RelatoRequestDTO dto) {
        Relato relato = relatoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Relato não encontrado"
                ));

        Estabelecimento estabelecimento = estabelecimentoRepository
                .findById(dto.estabelecimentoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Estabelecimento não encontrado"
                ));

        relato.setEstabelecimento(estabelecimento);
        relato.setDescricao(dto.descricao());
        relato.setStatus(dto.status());

        Relato atualizado = relatoRepository.save(relato);

        return converterParaDTO(atualizado);
    }

    public void excluir(Long id) {
        if (!relatoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException(
                    "Relato não encontrado"
            );
        }

        relatoRepository.deleteById(id);
    }

    private Relato converterParaEntidade(
            RelatoRequestDTO dto,
            Estabelecimento estabelecimento) {

        Relato relato = new Relato();

        relato.setEstabelecimento(estabelecimento);
        relato.setDescricao(dto.descricao());
        relato.setStatus(dto.status());

        return relato;
    }

    private RelatoResponseDTO converterParaDTO(Relato relato) {
        return new RelatoResponseDTO(
                relato.getId(),
                relato.getEstabelecimento().getId(),
                relato.getEstabelecimento().getNome(),
                relato.getDescricao(),
                relato.getStatus(),
                relato.getDataRegistro()
        );
    }
}
