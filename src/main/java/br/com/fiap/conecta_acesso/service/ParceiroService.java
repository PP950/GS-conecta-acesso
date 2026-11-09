package br.com.fiap.conecta_acesso.service;

import br.com.fiap.conecta_acesso.domain.parceiro.Parceiro;
import br.com.fiap.conecta_acesso.domain.parceiro.ParceiroRepository;
import br.com.fiap.conecta_acesso.infra.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParceiroService {

    private final ParceiroRepository repository;

    public ParceiroService(ParceiroRepository repository) {
        this.repository = repository;
    }

    public Parceiro cadastrar(Parceiro parceiro) {
        return repository.save(parceiro);
    }

    public List<Parceiro> listarTodos() {
        return repository.findAll();
    }

    public Optional<Parceiro> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Parceiro atualizar(Long id, Parceiro dados) {
        return repository.findById(id)
                .map(parceiro -> {
                    parceiro.setNome(dados.getNome());
                    parceiro.setEspecialidade(dados.getEspecialidade());
                    parceiro.setDescricao(dados.getDescricao());
                    parceiro.setTelefone(dados.getTelefone());
                    parceiro.setEmail(dados.getEmail());
                    parceiro.setBairro(dados.getBairro());
                    parceiro.setCidade(dados.getCidade());

                    return repository.save(parceiro);
                })
                .orElseThrow(() -> new RuntimeException("Parceiro não encontrado"));
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Parceiro não encontrado");
        }

        repository.deleteById(id);
    }
}