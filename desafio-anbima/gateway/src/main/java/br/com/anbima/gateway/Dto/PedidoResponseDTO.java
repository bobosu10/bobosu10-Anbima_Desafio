package br.com.anbima.gateway.Dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoResponseDTO (
        Long id,
        String tipoLanche,
        String proteina,
        String acompanhamento,
        Integer quantidade,
        String bebida,
        BigDecimal valor,
        String status,
        LocalDateTime criadoEm){
}
