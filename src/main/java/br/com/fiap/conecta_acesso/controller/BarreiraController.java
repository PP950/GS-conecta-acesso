package br.com.fiap.conecta_acesso.controller;

import br.com.fiap.conecta_acesso.domain.barreira.BarreiraRequestDTO;
import br.com.fiap.conecta_acesso.domain.barreira.BarreiraResponseDTO;
import br.com.fiap.conecta_acesso.service.BarreiraService;

import jakarta.validation.Valid;
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
    public BarreiraResponseDTO cadastrar(
            @Valid @RequestBody BarreiraRequestDTO dto) {
        return service.cadastrar(dto);
    }

    @GetMapping
    public List<BarreiraResponseDTO> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public BarreiraResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public BarreiraResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody BarreiraRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}