package br.com.anbima.gateway.Util;

import br.com.anbima.gateway.Dto.PedidoResponseDTO;
import br.com.anbima.gateway.Entity.Pedido;

public class PedidoMapper {

    public static PedidoResponseDTO toDTO(Pedido pedido){
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getTipoLanche(),
                pedido.getProteina(),
                pedido.getAcompanhamento(),
                pedido.getQuantidade(),
                pedido.getBebida(),
                pedido.getValor(),
                pedido.getStatus(),
                pedido.getCriadoEm()
        );
    }

}
