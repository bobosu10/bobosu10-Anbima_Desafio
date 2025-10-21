package br.com.anbima.gateway.Controller;

import br.com.anbima.gateway.Dto.PedidoResponseDTO;
import br.com.anbima.gateway.Service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping(value = "/posicional", consumes = "text/plain")
    public ResponseEntity<PedidoResponseDTO> receberPedido(@RequestBody String linha){
        PedidoResponseDTO pedidoResponseDTO = service.processarLinha(linha);
        return ResponseEntity.status(201)
                .body(pedidoResponseDTO);
    }
}
