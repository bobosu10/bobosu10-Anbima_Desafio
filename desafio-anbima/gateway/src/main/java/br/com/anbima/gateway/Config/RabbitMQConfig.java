package br.com.anbima.gateway.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {

    public static final String QUEU_PEDIDOS_RECEBIDOS = "pedidos.recebidos";

    @Bean
    public Queue queuePedidosRecebidos(){
        return new Queue(QUEU_PEDIDOS_RECEBIDOS, true);
    }
}
