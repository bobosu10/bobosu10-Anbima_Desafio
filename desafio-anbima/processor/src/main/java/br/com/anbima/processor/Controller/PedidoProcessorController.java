package br.com.anbima.processor.Controller;

import br.com.anbima.processor.DTO.PedidoResponseDTO;
import br.com.anbima.processor.Entity.Pedido;
import br.com.anbima.processor.Repository.PedidoRepository;
import br.com.anbima.processor.Util.PedidoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoProcessorController {

    private final PedidoRepository repository;

    public PedidoProcessorController(PedidoRepository repository) {
        this.repository = repository;
    }

    //endpoint get / pedidos
    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarTodosPedidos(){
        List<Pedido> pedidos = repository.findAll();
        //converter para DTO
        List<PedidoResponseDTO> pedidosDTOs = PedidoMapper.toDTOList(pedidos);
        return ResponseEntity.ok(pedidosDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> pedidoPorId(@PathVariable Long id){
        return repository.findById(id)
                .map(pedido -> {
                    //converter para DTO
                    PedidoResponseDTO pedidoDTO = PedidoMapper.toDTO(pedido);
                    return ResponseEntity.ok(pedidoDTO);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
