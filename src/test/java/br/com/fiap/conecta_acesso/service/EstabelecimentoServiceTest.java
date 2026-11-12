package br.com.fiap.conecta_acesso.service;

import br.com.fiap.conecta_acesso.domain.estabelecimento.EstabelecimentoRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.fiap.conecta_acesso.domain.estabelecimento.Estabelecimento;
import br.com.fiap.conecta_acesso.domain.estabelecimento.EstabelecimentoRequestDTO;
import br.com.fiap.conecta_acesso.domain.estabelecimento.EstabelecimentoResponseDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.Optional;
import br.com.fiap.conecta_acesso.infra.exception.RecursoNaoEncontradoException;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EstabelecimentoServiceTest {

    @Mock
    private EstabelecimentoRepository repository;

    @InjectMocks
    private EstabelecimentoService service;

    @Test
    void deveCadastrarEstabelecimento() {
        EstabelecimentoRequestDTO dto = new EstabelecimentoRequestDTO(
                "Restaurante Central",
                "Restaurante",
                "Rua das Flores, 100",
                "Centro",
                "São Paulo",
                "11999999999"
        );

        when(repository.save(any(Estabelecimento.class)))
                .thenAnswer(invocation -> {
                    Estabelecimento estabelecimento = invocation.getArgument(0);
                    estabelecimento.setId(1L);
                    return estabelecimento;
                });

        EstabelecimentoResponseDTO resposta = service.cadastrar(dto);

        assertNotNull(resposta);
        assertEquals(1L, resposta.id());
        assertEquals("Restaurante Central", resposta.nome());
        assertEquals("Centro", resposta.bairro());
    }

    @Test
    void deveBuscarEstabelecimentoPorId() {
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);
        estabelecimento.setNome("Restaurante Central");
        estabelecimento.setCategoria("Restaurante");
        estabelecimento.setEndereco("Rua das Flores, 100");
        estabelecimento.setBairro("Centro");
        estabelecimento.setCidade("São Paulo");

        when(repository.findById(1L))
                .thenReturn(Optional.of(estabelecimento));

        EstabelecimentoResponseDTO resposta = service.buscarPorId(1L);

        assertNotNull(resposta);
        assertEquals(1L, resposta.id());
        assertEquals("Restaurante Central", resposta.nome());
    }
    
    @Test
    void deveLancarExcecaoQuandoEstabelecimentoNaoExistir() {
        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> service.buscarPorId(99L)
        );
    }

    @Test
    void deveAtualizarEstabelecimento() {
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(estabelecimento));

        when(repository.save(any(Estabelecimento.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EstabelecimentoRequestDTO dto = new EstabelecimentoRequestDTO(
                "Restaurante Atualizado",
                "Restaurante",
                "Rua Nova, 200",
                "Vila Mariana",
                "São Paulo",
                "11988888888"
        );

        EstabelecimentoResponseDTO resposta = service.atualizar(1L, dto);

        assertEquals("Restaurante Atualizado", resposta.nome());
        assertEquals("Vila Mariana", resposta.bairro());
    }

    @Test
    void deveExcluirEstabelecimento() {
        when(repository.existsById(1L)).thenReturn(true);

        service.excluir(1L);

        verify(repository).deleteById(1L);
    }


}