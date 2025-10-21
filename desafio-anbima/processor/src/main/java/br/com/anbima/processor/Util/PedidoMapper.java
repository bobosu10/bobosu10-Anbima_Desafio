package br.com.anbima.processor.Util;

import br.com.anbima.processor.DTO.PedidoResponseDTO;
import br.com.anbima.processor.Entity.Pedido;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {

    public static PedidoResponseDTO toDTO(Pedido pedido){
        if (pedido == null){
            return null;
        }
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

    public static List<PedidoResponseDTO> toDTOList(List<Pedido> pedidos){
        if (pedidos == null){
            return Collections.emptyList();
        }
        return pedidos.stream()
                .map(PedidoMapper::toDTO)
                .collect(Collectors.toList());
    }


}
