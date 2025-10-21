package br.com.anbima.gateway.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "pedidos")
@Data
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoLanche;

    private String proteina;

    private String acompanhamento;

    private Integer quantidade;

    private String bebida;

    private BigDecimal valor;

    private String status;

    private LocalDateTime criadoEm = LocalDateTime.now();



}
