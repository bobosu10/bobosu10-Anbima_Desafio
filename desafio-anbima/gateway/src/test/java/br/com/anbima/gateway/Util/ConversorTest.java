package br.com.anbima.gateway.Util;
import br.com.anbima.gateway.Entity.Pedido;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConversorTest {

    @Test
     void testConverterLinha(){

        //test 1:HAMBURGUER + CARNE + SALADA, 1 unid
        String linhaValida = "HAMBURGUERCARNE     SALADA    01COCA    ";

        Pedido pedido = Conversor.converter(linhaValida);

        assertEquals("HAMBURGUER", pedido.getTipoLanche());
        assertEquals("CARNE     ", pedido.getProteina());
        assertEquals("SALADA    ", pedido.getAcompanhamento());
        assertEquals(1, pedido.getQuantidade());
        assertEquals("COCA    ", pedido.getBebida());
    }

    @Test
    void testConverterLinhaComPaddingDif(){

        //test 2:PASTEL + FRANGO + BACON, 2 unid

        String linhaValida = "PASTEL    FRANGO    BACON     02SUCO    ";

        Pedido pedido = Conversor.converter(linhaValida);

        assertEquals("PASTEL    ", pedido.getTipoLanche());
        assertEquals("FRANGO    ", pedido.getProteina());
        assertEquals("BACON     ", pedido.getAcompanhamento());
        assertEquals(2, pedido.getQuantidade());
        assertEquals("SUCO    ", pedido.getBebida());
    }

    @Test
    void testConverterLinhaTamanhoErrado(){

        String linhaCurta = "STRING CURTA";

        //teste da excecao

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Conversor.converter(linhaCurta);
        });

        //teste da mensagem de excecao

        assertTrue(exception.getMessage().contains("A linha deve ter exatamente 40 caracteres"));
    }

    @Test
    void testConverterQuebraDeLinha(){

        //string com \n
        String linhaQuebrada = "HAMBURGUERCARNE     SALADA    01COCA    \n";

        Pedido pedido = Conversor.converter(linhaQuebrada);

        assertEquals("HAMBURGUER", pedido.getTipoLanche());
        assertEquals("CARNE     ", pedido.getProteina());
        assertEquals("SALADA    ", pedido.getAcompanhamento());
        assertEquals(1, pedido.getQuantidade());
        assertEquals("COCA    ", pedido.getBebida());
    }

    @Test
    void testConverterQuantidadeNaoNumerica(){

        String linhaComErro = "HAMBURGUERCARNE     SALADA    AACOCA    ";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Conversor.converter(linhaComErro);
        });

        assertEquals("O campo 'quantidade' (posições 31-32) deve ser numérico.", exception.getMessage());
    }
}
