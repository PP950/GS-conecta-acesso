package br.com.fiap.conecta_acesso.controller;

import br.com.fiap.conecta_acesso.domain.parceiro.Parceiro;
import br.com.fiap.conecta_acesso.service.ParceiroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parceiros")
public class ParceiroController {

    private final ParceiroService service;

    public ParceiroController(ParceiroService service) {
        this.service = service;
    }

    @PostMapping
    public Parceiro cadastrar(@RequestBody Parceiro parceiro) {
        return service.cadastrar(parceiro);
    }

    @GetMapping
    public List<Parceiro> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Parceiro buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Parceiro não encontrado"));
    }

    @PutMapping("/{id}")
    public Parceiro atualizar(
            @PathVariable Long id,
            @RequestBody Parceiro parceiro) {
        return service.atualizar(id, parceiro);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}