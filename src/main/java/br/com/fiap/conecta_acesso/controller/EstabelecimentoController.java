package br.com.fiap.conecta_acesso.controller;

import br.com.fiap.conecta_acesso.domain.estabelecimento.Estabelecimento;
import br.com.fiap.conecta_acesso.service.EstabelecimentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estabelecimentos")
public class EstabelecimentoController {

    private final EstabelecimentoService service;

    public EstabelecimentoController(EstabelecimentoService service) {
        this.service = service;
    }

    @PostMapping
    public Estabelecimento cadastrar(@RequestBody Estabelecimento estabelecimento) {
        return service.cadastrar(estabelecimento);
    }

    @GetMapping("/{id}")
    public Estabelecimento buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));
    }

    @PutMapping("/{id}")
    public Estabelecimento atualizar(
            @PathVariable Long id,
            @RequestBody Estabelecimento estabelecimento) {
        return service.atualizar(id, estabelecimento);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping
    public List<Estabelecimento> listarTodos() {
        return service.listarTodos();
    }
}