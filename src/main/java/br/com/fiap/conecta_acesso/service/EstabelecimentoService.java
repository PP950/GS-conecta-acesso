package br.com.fiap.conecta_acesso.service;

import br.com.fiap.conecta_acesso.domain.estabelecimento.Estabelecimento;
import br.com.fiap.conecta_acesso.domain.estabelecimento.EstabelecimentoRepository;
import br.com.fiap.conecta_acesso.infra.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstabelecimentoService {

    private final EstabelecimentoRepository repository;

    public EstabelecimentoService(EstabelecimentoRepository repository) {
        this.repository = repository;
    }

    public Estabelecimento cadastrar(Estabelecimento estabelecimento) {
        return repository.save(estabelecimento);
    }

    public Optional<Estabelecimento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Estabelecimento atualizar(Long id, Estabelecimento dados) {
        return repository.findById(id)
                .map(estabelecimento -> {
                    estabelecimento.setNome(dados.getNome());
                    estabelecimento.setCategoria(dados.getCategoria());
                    estabelecimento.setEndereco(dados.getEndereco());
                    estabelecimento.setBairro(dados.getBairro());
                    estabelecimento.setCidade(dados.getCidade());
                    estabelecimento.setContato(dados.getContato());

                    return repository.save(estabelecimento);
                })
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Estabelecimento não encontrado");
        }

        repository.deleteById(id);
    }

    public List<Estabelecimento> listarTodos() {
        return repository.findAll();
    }

}