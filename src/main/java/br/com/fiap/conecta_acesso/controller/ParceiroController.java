package br.com.fiap.conecta_acesso.controller;

import br.com.fiap.conecta_acesso.domain.parceiro.ParceiroRequestDTO;
import br.com.fiap.conecta_acesso.domain.parceiro.ParceiroResponseDTO;
import br.com.fiap.conecta_acesso.service.ParceiroService;

import jakarta.validation.Valid;
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
    public ParceiroResponseDTO cadastrar(
            @Valid @RequestBody ParceiroRequestDTO dto) {
        return service.cadastrar(dto);
    }

    @GetMapping
    public List<ParceiroResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ParceiroResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ParceiroResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ParceiroRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}