package br.com.anbima.processor.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoResponseDTO(
        Long id,
        String tipoLanche,
        String proteina,
        String acompanhanmento,
        Integer quantidade,
        String bebida,
        BigDecimal valor,
        String status,
        LocalDateTime criadoEm){
}
