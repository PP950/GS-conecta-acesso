package br.com.fiap.conecta_acesso.controller;

import br.com.fiap.conecta_acesso.domain.relato.RelatoRequestDTO;
import br.com.fiap.conecta_acesso.domain.relato.RelatoResponseDTO;
import br.com.fiap.conecta_acesso.service.RelatoService;

import jakarta.validation.Valid;
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
    public RelatoResponseDTO cadastrar(
            @Valid @RequestBody RelatoRequestDTO dto) {
        return service.cadastrar(dto);
    }

    @GetMapping
    public List<RelatoResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public RelatoResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public RelatoResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody RelatoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}