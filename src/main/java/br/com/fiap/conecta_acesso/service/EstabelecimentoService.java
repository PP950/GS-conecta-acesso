package br.com.fiap.conecta_acesso.service;

import br.com.fiap.conecta_acesso.domain.estabelecimento.Estabelecimento;
import br.com.fiap.conecta_acesso.domain.estabelecimento.EstabelecimentoRepository;
import br.com.fiap.conecta_acesso.infra.exception.RecursoNaoEncontradoException;
import br.com.fiap.conecta_acesso.domain.estabelecimento.EstabelecimentoRequestDTO;
import br.com.fiap.conecta_acesso.domain.estabelecimento.EstabelecimentoResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstabelecimentoService {

    private final EstabelecimentoRepository repository;

    public EstabelecimentoService(EstabelecimentoRepository repository) {
        this.repository = repository;
    }

    public EstabelecimentoResponseDTO cadastrar(EstabelecimentoRequestDTO dto) {
        Estabelecimento estabelecimento = converterParaEntidade(dto);

        Estabelecimento salvo = repository.save(estabelecimento);

        return converterParaDTO(salvo);
    }

    public EstabelecimentoResponseDTO buscarPorId(Long id) {
        Estabelecimento estabelecimento = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Estabelecimento não encontrado"
                ));

        return converterParaDTO(estabelecimento);
    }

    public EstabelecimentoResponseDTO atualizar(Long id, EstabelecimentoRequestDTO dto) {
        Estabelecimento estabelecimento = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Estabelecimento não encontrado"
                ));

        estabelecimento.setNome(dto.nome());
        estabelecimento.setCategoria(dto.categoria());
        estabelecimento.setEndereco(dto.endereco());
        estabelecimento.setBairro(dto.bairro());
        estabelecimento.setCidade(dto.cidade());
        estabelecimento.setContato(dto.contato());

        Estabelecimento atualizado = repository.save(estabelecimento);

        return converterParaDTO(atualizado);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException(
                    "Estabelecimento não encontrado"
            );
        }

        repository.deleteById(id);
    }

    public List<EstabelecimentoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    private Estabelecimento converterParaEntidade(EstabelecimentoRequestDTO dto) {
        Estabelecimento estabelecimento = new Estabelecimento();

        estabelecimento.setNome(dto.nome());
        estabelecimento.setCategoria(dto.categoria());
        estabelecimento.setEndereco(dto.endereco());
        estabelecimento.setBairro(dto.bairro());
        estabelecimento.setCidade(dto.cidade());
        estabelecimento.setContato(dto.contato());

        return estabelecimento;
    }

    private EstabelecimentoResponseDTO converterParaDTO(Estabelecimento estabelecimento) {
        return new EstabelecimentoResponseDTO(
                estabelecimento.getId(),
                estabelecimento.getNome(),
                estabelecimento.getCategoria(),
                estabelecimento.getEndereco(),
                estabelecimento.getBairro(),
                estabelecimento.getCidade(),
                estabelecimento.getContato()
        );
    }

}