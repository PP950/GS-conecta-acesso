package br.com.fiap.conecta_acesso.controller;

import br.com.fiap.conecta_acesso.domain.barreira.Barreira;
import br.com.fiap.conecta_acesso.service.BarreiraService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barreiras")
public class BarreiraController {

    private final BarreiraService service;

    public BarreiraController(BarreiraService service) {
        this.service = service;
    }

    @PostMapping
    public Barreira cadastrar(@RequestBody Barreira barreira) {
        return service.cadastrar(barreira);
    }

    @GetMapping
    public List<Barreira> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public Barreira buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Barreira não encontrada"));
    }

    @PutMapping("/{id}")
    public Barreira atualizar(
            @PathVariable Long id,
            @RequestBody Barreira barreira) {
        return service.atualizar(id, barreira);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}