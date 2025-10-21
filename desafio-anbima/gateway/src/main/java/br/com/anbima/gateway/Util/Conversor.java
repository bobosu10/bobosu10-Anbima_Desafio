package br.com.anbima.gateway.Util;

import br.com.anbima.gateway.Entity.Pedido;

public class Conversor {

    public static Pedido converter(String linha){
        if (linha == null || linha.length() != 40){
            throw new IllegalArgumentException("A linha deve ter exatamente 40 caracteres");
        }

        Pedido pedido = new Pedido();
        pedido.setTipoLanche(linha.substring(0, 10).trim());
        pedido.setProteina(linha.substring(10, 20).trim());
        pedido.setAcompanhamento(linha.substring(20, 30).trim());
        pedido.setQuantidade(Integer.parseInt(linha.substring(30, 32)));
        pedido.setBebida(linha.substring(32, 40).trim());
        return pedido;
    }
}
