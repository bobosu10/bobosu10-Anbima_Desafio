package br.com.anbima.processor.Service;

import br.com.anbima.processor.Entity.Pedido;
import br.com.anbima.processor.Repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PedidoProcessorService {

    private final PedidoRepository repository;

    public PedidoProcessorService(PedidoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void processarPedidoRecebido(Long pedidoId){
        Pedido pedido = repository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com ID: " + pedidoId));

        //att o status e salva
        pedido.setStatus("ENTREGUE");
        repository.save(pedido);
    }
}
