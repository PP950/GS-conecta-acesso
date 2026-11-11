
package br.com.fiap.conecta_acesso.service;

import br.com.fiap.conecta_acesso.domain.parceiro.Parceiro;
import br.com.fiap.conecta_acesso.domain.parceiro.ParceiroRepository;
import br.com.fiap.conecta_acesso.domain.parceiro.ParceiroRequestDTO;
import br.com.fiap.conecta_acesso.domain.parceiro.ParceiroResponseDTO;
import br.com.fiap.conecta_acesso.infra.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParceiroService {

    private final ParceiroRepository repository;

    public ParceiroService(ParceiroRepository repository) {
        this.repository = repository;
    }

    public ParceiroResponseDTO cadastrar(ParceiroRequestDTO dto) {
        Parceiro parceiro = converterParaEntidade(dto);

        Parceiro salvo = repository.save(parceiro);

        return converterParaDTO(salvo);
    }

    public List<ParceiroResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public ParceiroResponseDTO buscarPorId(Long id) {
        Parceiro parceiro = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Parceiro não encontrado"
                ));

        return converterParaDTO(parceiro);
    }

    public ParceiroResponseDTO atualizar(Long id, ParceiroRequestDTO dto) {
        Parceiro parceiro = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Parceiro não encontrado"
                ));

        parceiro.setNome(dto.nome());
        parceiro.setEspecialidade(dto.especialidade());
        parceiro.setDescricao(dto.descricao());
        parceiro.setTelefone(dto.telefone());
        parceiro.setEmail(dto.email());
        parceiro.setBairro(dto.bairro());
        parceiro.setCidade(dto.cidade());

        Parceiro atualizado = repository.save(parceiro);

        return converterParaDTO(atualizado);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException(
                    "Parceiro não encontrado"
            );
        }

        repository.deleteById(id);
    }

    private Parceiro converterParaEntidade(ParceiroRequestDTO dto) {
        Parceiro parceiro = new Parceiro();

        parceiro.setNome(dto.nome());
        parceiro.setEspecialidade(dto.especialidade());
        parceiro.setDescricao(dto.descricao());
        parceiro.setTelefone(dto.telefone());
        parceiro.setEmail(dto.email());
        parceiro.setBairro(dto.bairro());
        parceiro.setCidade(dto.cidade());

        return parceiro;
    }

    private ParceiroResponseDTO converterParaDTO(Parceiro parceiro) {
        return new ParceiroResponseDTO(
                parceiro.getId(),
                parceiro.getNome(),
                parceiro.getEspecialidade(),
                parceiro.getDescricao(),
                parceiro.getTelefone(),
                parceiro.getEmail(),
                parceiro.getBairro(),
                parceiro.getCidade()
        );
    }
}
