package br.com.anbima.gateway.Messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PedidoProducer {

    private final RabbitTemplate rabbitTemplate;

    public PedidoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarPedidoRecebido(Long pedidoId){
        rabbitTemplate.convertAndSend("pedidos.recebidos", Map.of("pedidoId", pedidoId));
    }
}
