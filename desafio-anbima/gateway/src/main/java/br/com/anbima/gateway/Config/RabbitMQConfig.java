package br.com.anbima.gateway.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_PEDIDOS_RECEBIDOS = "pedidos.recebidos";

    @Bean
    public Queue queuePedidosRecebidos(){
        return new Queue(QUEUE_PEDIDOS_RECEBIDOS, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
