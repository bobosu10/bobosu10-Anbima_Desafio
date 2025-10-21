package br.com.anbima.processor.Messaging;

import br.com.anbima.processor.Service.PedidoProcessorService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.logging.Logger;

@Component
public class PedidoListener {

    private static final Logger LOGGER = Logger.getLogger(PedidoListener.class.getName());
    private final PedidoProcessorService pedidoProcessorService;

    public PedidoListener(PedidoProcessorService pedidoProcessorService) {
        this.pedidoProcessorService = pedidoProcessorService;
    }

    //esse metodo vai ouvir a fila pedidos.recibidos
    @RabbitListener(queues = "pedidos.recebidos")
    public void receberMensagemPedido(Map<String, Long> mensagem) {
        Long pedidoId = mensagem.get("pedidoId");
        if (pedidoId != null){
            LOGGER.info("Mensagem recebida para o Pedido ID: " + pedidoId);
            try {
                pedidoProcessorService.processarPedidoRecebido(pedidoId);
                LOGGER.info("Pedido ID: " + pedidoId + " processado e marcado como ENTREGUE.");
            }catch (Exception e) {
                LOGGER.severe("Erro ao processar Pedido ID: " + pedidoId + " - " + e.getMessage());
            }
        }else {
            LOGGER.warning("Mensagem recebida sem 'pedidoId': " + mensagem);
        }
    }
}

