package br.com.anbima.gateway.Service;

import br.com.anbima.gateway.Dto.PedidoResponseDTO;
import br.com.anbima.gateway.Entity.Pedido;
import br.com.anbima.gateway.Messaging.PedidoProducer;
import br.com.anbima.gateway.Repository.PedidoRepository;
import br.com.anbima.gateway.Util.Conversor;
import br.com.anbima.gateway.Util.PedidoMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    private final PedidoProducer producer;

    public PedidoService(PedidoProducer producer, PedidoRepository repository) {
        this.producer = producer;
        this.repository = repository;
    }

    public PedidoResponseDTO processarLinha(String linha){
        Pedido pedido = Conversor.converter(linha);

        if (pedido.getQuantidade() < 1 || pedido.getQuantidade() > 99){
            throw new IllegalArgumentException("A quantidade deve ser um valor entre 01 e 99.");
        }

        //regras para preco
        BigDecimal base = switch (pedido.getTipoLanche().trim().toUpperCase()){
            case "HAMBURGUER" -> BigDecimal.valueOf(20);
            case "PASTEL" -> BigDecimal.valueOf(15);
            default -> BigDecimal.valueOf(12);
        };

        BigDecimal total = base.multiply(BigDecimal.valueOf(pedido.getQuantidade()));

        //caso do 10% de desconto
        if (pedido.getTipoLanche().trim().equalsIgnoreCase("HAMBURGUER")
            && pedido.getProteina().trim().equalsIgnoreCase("CARNE")
                && pedido.getAcompanhamento().trim().equalsIgnoreCase("SALADA")){
            total = total.multiply(BigDecimal.valueOf(0.9));
        }

        pedido.setValor(total);
        pedido.setStatus("RECEBIDO");

        Pedido salvo = repository.save(pedido);

        producer.enviarPedidoRecebido(salvo.getId());

        return PedidoMapper.toDTO(salvo);
    }
}
