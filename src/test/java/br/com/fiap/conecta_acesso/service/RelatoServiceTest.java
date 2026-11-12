
package br.com.fiap.conecta_acesso.service;

import br.com.fiap.conecta_acesso.domain.relato.*;
import br.com.fiap.conecta_acesso.domain.estabelecimento.Estabelecimento;
import br.com.fiap.conecta_acesso.domain.estabelecimento.EstabelecimentoRepository;
import br.com.fiap.conecta_acesso.infra.exception.RecursoNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RelatoServiceTest {

    @Mock
    private RelatoRepository relatoRepository;

    @Mock
    private EstabelecimentoRepository estabelecimentoRepository;

    @InjectMocks
    private RelatoService service;

    @Test
    void deveCadastrarRelato() {
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);
        estabelecimento.setNome("Restaurante Central");

        RelatoRequestDTO dto = new RelatoRequestDTO(
                1L,
                "Dificuldade para acessar a entrada do estabelecimento",
                StatusRelato.RECEBIDO
        );

        when(estabelecimentoRepository.findById(1L))
                .thenReturn(Optional.of(estabelecimento));

        when(relatoRepository.save(any(Relato.class)))
                .thenAnswer(invocation -> {
                    Relato relato = invocation.getArgument(0);
                    relato.setId(1L);
                    return relato;
                });

        RelatoResponseDTO resposta = service.cadastrar(dto);

        assertNotNull(resposta);
        assertEquals(1L, resposta.id());
        assertEquals(1L, resposta.estabelecimentoId());
        assertEquals("Restaurante Central", resposta.estabelecimentoNome());
        assertEquals(StatusRelato.RECEBIDO, resposta.status());
    }

    @Test
    void naoDeveCadastrarRelatoComEstabelecimentoInexistente() {
        RelatoRequestDTO dto = new RelatoRequestDTO(
                99L,
                "Dificuldade de acesso",
                StatusRelato.RECEBIDO
        );

        when(estabelecimentoRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> service.cadastrar(dto)
        );

        verify(relatoRepository, never()).save(any(Relato.class));
    }

    @Test
    void deveLancarExcecaoQuandoRelatoNaoExistir() {
        when(relatoRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> service.buscarPorId(99L)
        );
    }

    @Test
    void deveAtualizarRelato() {
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);
        estabelecimento.setNome("Restaurante Central");

        Relato relato = new Relato();
        relato.setId(1L);
        relato.setEstabelecimento(estabelecimento);

        RelatoRequestDTO dto = new RelatoRequestDTO(
                1L,
                "Entrada sem acessibilidade adequada",
                StatusRelato.EM_ANALISE
        );

        when(relatoRepository.findById(1L))
                .thenReturn(Optional.of(relato));

        when(estabelecimentoRepository.findById(1L))
                .thenReturn(Optional.of(estabelecimento));

        when(relatoRepository.save(any(Relato.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        RelatoResponseDTO resposta = service.atualizar(1L, dto);

        assertNotNull(resposta);
        assertEquals("Entrada sem acessibilidade adequada", resposta.descricao());
        assertEquals(StatusRelato.EM_ANALISE, resposta.status());
        assertEquals("Restaurante Central", resposta.estabelecimentoNome());
    }

    @Test
    void deveExcluirRelato() {
        when(relatoRepository.existsById(1L)).thenReturn(true);

        service.excluir(1L);

        verify(relatoRepository).deleteById(1L);
    }
}
