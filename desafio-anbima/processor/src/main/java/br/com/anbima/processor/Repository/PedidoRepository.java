package br.com.anbima.processor.Repository;

import br.com.anbima.processor.Entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
