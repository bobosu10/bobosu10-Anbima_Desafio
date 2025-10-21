package br.com.anbima.gateway.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "pedidos")
@Data
@Entity
public class Pedido {
}
