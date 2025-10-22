import { Component , OnInit} from '@angular/core';
import { PedidoService , PedidoResponse } from '../pedido';
import { CommonModule } from '@angular/common';
import { error } from 'console';
import { stat } from 'fs';

@Component({
  selector: 'app-pedidos-lista',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pedidos-lista.html',
  styleUrls: ['./pedidos-lista.css']
})

export class PedidosLista implements OnInit {
    pedidos: PedidoResponse[] = [];
    erro: string | null = null;
    isLoading: boolean = false;
    filtroStatus: 'TODOS' | 'RECEBIDO' | 'ENTREGUE' = 'TODOS';

    constructor(private pedidoService: PedidoService) {}

    ngOnInit(): void {
        this.carregarPedidos();
    }

    carregarPedidos() {
        this.isLoading = true;
        this.erro = null;
        this.pedidoService.listarTodosPedidos().subscribe({
            next: (data) => {
                this.pedidos = data;
                this.isLoading = false;
            },
            error: (error) => {
                console.error('Erro ao carregar pedidos:', error);
                this.erro = error.message || 'Erro desconhecido ao carregar pedidos.';
                this.isLoading = false;
            }
        });
    }

    definirFiltro(status: 'TODOS' | 'RECEBIDO' | 'ENTREGUE'){
        this.filtroStatus = status;
    }

    get pedidosFiltrados(): PedidoResponse[]{
        if (this.filtroStatus == 'TODOS') {
            return this.pedidos;
        }
        return this.pedidos.filter(pedido => pedido.status === this.filtroStatus);
    }
}
