package br.com.fiap.conecta_acesso.controller;

import br.com.fiap.conecta_acesso.service.EstabelecimentoService;
import br.com.fiap.conecta_acesso.domain.estabelecimento.EstabelecimentoRequestDTO;
import br.com.fiap.conecta_acesso.domain.estabelecimento.EstabelecimentoResponseDTO;

import jakarta.validation.Valid;
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
    public EstabelecimentoResponseDTO cadastrar(
            @Valid @RequestBody EstabelecimentoRequestDTO dto) {
        return service.cadastrar(dto);
    }

    @GetMapping("/{id}")
    public EstabelecimentoResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public EstabelecimentoResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody EstabelecimentoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping
    public List<EstabelecimentoResponseDTO> listarTodos() {
        return service.listarTodos();
    }
}