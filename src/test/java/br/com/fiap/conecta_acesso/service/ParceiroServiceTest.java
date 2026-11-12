
package br.com.fiap.conecta_acesso.service;

import br.com.fiap.conecta_acesso.domain.parceiro.*;
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
class ParceiroServiceTest {

    @Mock
    private ParceiroRepository repository;

    @InjectMocks
    private ParceiroService service;

    @Test
    void deveCadastrarParceiro() {
        ParceiroRequestDTO dto = new ParceiroRequestDTO(
                "Acessibilidade Brasil",
                EspecialidadeParceiro.CONSULTORIA_ACESSIBILIDADE,
                "Consultoria especializada em acessibilidade",
                "11999999999",
                "contato@acessibilidade.com",
                "Centro",
                "São Paulo"
        );

        when(repository.save(any(Parceiro.class)))
                .thenAnswer(invocation -> {
                    Parceiro parceiro = invocation.getArgument(0);
                    parceiro.setId(1L);
                    return parceiro;
                });

        ParceiroResponseDTO resposta = service.cadastrar(dto);

        assertNotNull(resposta);
        assertEquals(1L, resposta.id());
        assertEquals("Acessibilidade Brasil", resposta.nome());
        assertEquals(
                EspecialidadeParceiro.CONSULTORIA_ACESSIBILIDADE,
                resposta.especialidade()
        );
        assertEquals("São Paulo", resposta.cidade());
    }

    @Test
    void deveLancarExcecaoQuandoParceiroNaoExistir() {
        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> service.buscarPorId(99L)
        );
    }

    @Test
    void deveAtualizarParceiro() {
        Parceiro parceiro = new Parceiro();
        parceiro.setId(1L);
        parceiro.setNome("Empresa Antiga");

        ParceiroRequestDTO dto = new ParceiroRequestDTO(
                "Empresa Atualizada",
                EspecialidadeParceiro.ARQUITETURA,
                "Especializada em projetos acessíveis",
                "11888888888",
                "novo@empresa.com",
                "Pinheiros",
                "São Paulo"
        );

        when(repository.findById(1L))
                .thenReturn(Optional.of(parceiro));

        when(repository.save(any(Parceiro.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ParceiroResponseDTO resposta = service.atualizar(1L, dto);

        assertNotNull(resposta);
        assertEquals("Empresa Atualizada", resposta.nome());
        assertEquals(EspecialidadeParceiro.ARQUITETURA, resposta.especialidade());
        assertEquals("Pinheiros", resposta.bairro());
    }

    @Test
    void naoDeveAtualizarParceiroInexistente() {
        ParceiroRequestDTO dto = new ParceiroRequestDTO(
                "Empresa Atualizada",
                EspecialidadeParceiro.ARQUITETURA,
                "Descrição",
                "11888888888",
                "novo@empresa.com",
                "Pinheiros",
                "São Paulo"
        );

        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> service.atualizar(99L, dto)
        );

        verify(repository, never()).save(any(Parceiro.class));
    }

    @Test
    void deveExcluirParceiro() {
        when(repository.existsById(1L)).thenReturn(true);

        service.excluir(1L);

        verify(repository).deleteById(1L);
    }
}
