package br.com.anbima.gateway.Util;

import br.com.anbima.gateway.Entity.Pedido;

public class Conversor {

    public static Pedido converter(String linha){

        // Validação inicial de nulo
        if (linha == null) {
            throw new IllegalArgumentException("A linha não pode ser nula.");
        }

        // Limpeza
        // Remove caracteres de controle (como \n, \r) e espaços "falsos"
        String linhaLimpa = linha.replace("\u00A0", " ")
                .replaceAll("\\p{C}", "");

        // Validação de tamanho
        if (linhaLimpa.length() != 40){
            throw new IllegalArgumentException(
                    "A linha deve ter exatamente 40 caracteres. (Tamanho recebido após limpeza: " + linhaLimpa.length() + ")"
            );
        }

        // Conversão
        try {
            Pedido pedido = new Pedido();
            pedido.setTipoLanche(linhaLimpa.substring(0, 10));
            pedido.setProteina(linhaLimpa.substring(10, 20));
            pedido.setAcompanhamento(linhaLimpa.substring(20, 30));
            pedido.setQuantidade(Integer.parseInt(linhaLimpa.substring(30, 32)));
            pedido.setBebida(linhaLimpa.substring(32, 40));
            return pedido;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O campo 'quantidade' (posições 31-32) deve ser numérico.");
        }
    }
}