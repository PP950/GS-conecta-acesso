package br.com.fiap.conecta_acesso.controller;

import br.com.fiap.conecta_acesso.domain.relato.Relato;
import br.com.fiap.conecta_acesso.service.RelatoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relatos")
public class RelatoController {

    private final RelatoService service;

    public RelatoController(RelatoService service) {
        this.service = service;
    }

    @PostMapping
    public Relato cadastrar(@RequestBody Relato relato) {
        return service.cadastrar(relato);
    }

    @GetMapping
    public List<Relato> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Relato buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Relato não encontrado"));
    }

    @PutMapping("/{id}")
    public Relato atualizar(
            @PathVariable Long id,
            @RequestBody Relato relato) {
        return service.atualizar(id, relato);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}