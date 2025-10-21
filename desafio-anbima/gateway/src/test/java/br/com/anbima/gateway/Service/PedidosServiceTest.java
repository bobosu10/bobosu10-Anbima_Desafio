package br.com.anbima.gateway.Service;

import br.com.anbima.gateway.Dto.PedidoResponseDTO;
import br.com.anbima.gateway.Entity.Pedido;
import br.com.anbima.gateway.Messaging.PedidoProducer;
import br.com.anbima.gateway.Repository.PedidoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PedidosServiceTest {

    @Mock
    private PedidoRepository repository;

    @Mock
    private PedidoProducer producer;

    @InjectMocks
    private PedidoService service;


    //configura o mock do repositorio para simular save
    @BeforeEach
    void setUp(){
        when(repository.save(any(Pedido.class))).thenAnswer(invocationOnMock -> {
            Pedido pedidoSalvo = invocationOnMock.getArgument(0);

            pedidoSalvo.setId(1L);
            pedidoSalvo.setCriadoEm(LocalDateTime.now());

            return pedidoSalvo;
        });

        // Configura o mock do producer para não fazer nada (retorna void)
        doNothing().when(producer).enviarPedidoRecebido(anyLong());
    }

    @Test
    void testLinhaComDesconto(){
        //cenario HAMBURGUER + CARNE + SALADA, 1 unid
        String linha = "HAMBURGUERCARNE     SALADA    01COCA    ";

        PedidoResponseDTO response = service.processarLinha(linha);

        //verificacao
        assertEquals(0, new BigDecimal("18.0").compareTo(response.valor()), "O valor com desconto deve ser 18.0");
        assertEquals("RECEBIDO", response.status());
        assertEquals(1L, response.id());

        //verfica os mocks
        verify(repository, times(1)).save(any(Pedido.class));
        verify(producer, times(1)).enviarPedidoRecebido(1L);
    }

    @Test
    void testLinhaSemDesconto(){

        //cenario PASTEL + FRANGO + BACON, 2 unid
        String linha = "PASTEL    FRANGO    BACON     02SUCO    ";

        PedidoResponseDTO response = service.processarLinha(linha);

        //verificacao
        assertEquals(0, new BigDecimal("30.0").compareTo(response.valor()), "O valor sem desconto deve ser 30.0");
        assertEquals("RECEBIDO", response.status());
    }

    @Test
    void testLinhaOutros(){

        //cenario tipo lanche = outros e 3 unids
       String linha = "PIZZA     CALABRESA QUEIJO    03GUARANA ";

       PedidoResponseDTO response = service.processarLinha(linha);

       //verificacao
        assertEquals(0, new BigDecimal("36.0").compareTo(response.valor()), "O valor 'outros' deve ser 36.0");
        assertEquals("RECEBIDO", response.status());
    }

    @Test
    void testLinhaQuantidadeZero(){

        //cenario quantidade 00
        String linha = "HAMBURGUERCARNE     SALADA    00COCA    ";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.processarLinha(linha);
        });

        assertEquals("A quantidade deve ser um valor entre 01 e 99.", exception.getMessage());

        verify(repository, never()).save(any());
        verify(producer, never()).enviarPedidoRecebido(anyLong());
    }







































}
