
package br.com.fiap.conecta_acesso.service;

import br.com.fiap.conecta_acesso.domain.barreira.*;
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
class BarreiraServiceTest {

    @Mock
    private BarreiraRepository barreiraRepository;

    @Mock
    private EstabelecimentoRepository estabelecimentoRepository;

    @InjectMocks
    private BarreiraService service;

    @Test
    void deveCadastrarBarreira() {
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);
        estabelecimento.setNome("Restaurante Central");

        BarreiraRequestDTO dto = new BarreiraRequestDTO(
                1L,
                TipoBarreira.RAMPA,
                "Ausência de rampa de acesso",
                SeveridadeBarreira.ALTA,
                "PENDENTE"
        );

        when(estabelecimentoRepository.findById(1L))
                .thenReturn(Optional.of(estabelecimento));

        when(barreiraRepository.save(any(Barreira.class)))
                .thenAnswer(invocation -> {
                    Barreira barreira = invocation.getArgument(0);
                    barreira.setId(1L);
                    return barreira;
                });

        BarreiraResponseDTO resposta = service.cadastrar(dto);

        assertNotNull(resposta);
        assertEquals(1L, resposta.id());
        assertEquals(1L, resposta.estabelecimentoId());
        assertEquals("Restaurante Central", resposta.estabelecimentoNome());
        assertEquals(TipoBarreira.RAMPA, resposta.tipo());
    }

    @Test
    void naoDeveCadastrarBarreiraComEstabelecimentoInexistente() {
        BarreiraRequestDTO dto = new BarreiraRequestDTO(
                99L,
                TipoBarreira.RAMPA,
                "Ausência de rampa de acesso",
                SeveridadeBarreira.ALTA,
                "PENDENTE"
        );

        when(estabelecimentoRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> service.cadastrar(dto)
        );

        verify(barreiraRepository, never()).save(any(Barreira.class));
    }

    @Test
    void deveLancarExcecaoQuandoBarreiraNaoExistir() {
        when(barreiraRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> service.buscarPorId(99L)
        );
    }

    @Test
    void deveAtualizarBarreira() {
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);
        estabelecimento.setNome("Restaurante Central");

        Barreira barreira = new Barreira();
        barreira.setId(1L);
        barreira.setEstabelecimento(estabelecimento);

        BarreiraRequestDTO dto = new BarreiraRequestDTO(
                1L,
                TipoBarreira.RAMPA,
                "Rampa instalada na entrada",
                SeveridadeBarreira.BAIXA,
                "EM_RESOLUCAO"
        );

        when(barreiraRepository.findById(1L))
                .thenReturn(Optional.of(barreira));

        when(estabelecimentoRepository.findById(1L))
                .thenReturn(Optional.of(estabelecimento));

        when(barreiraRepository.save(any(Barreira.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        BarreiraResponseDTO resposta = service.atualizar(1L, dto);

        assertNotNull(resposta);
        assertEquals("Rampa instalada na entrada", resposta.descricao());
        assertEquals(SeveridadeBarreira.BAIXA, resposta.severidade());
        assertEquals("EM_RESOLUCAO", resposta.status());
    }

    @Test
    void deveExcluirBarreira() {
        when(barreiraRepository.existsById(1L)).thenReturn(true);

        service.excluir(1L);

        verify(barreiraRepository).deleteById(1L);
    }
}
