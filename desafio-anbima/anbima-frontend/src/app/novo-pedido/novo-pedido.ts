import { Component } from '@angular/core';
import { PedidoService, PedidoResponse } from '../pedido'; 
import { FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common'; 
import { response } from 'express';
import { error } from 'console';

@Component({
  selector: 'app-novo-pedido',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './novo-pedido.html',
  styleUrls: ['./novo-pedido.css']
})
export class NovoPedido {
    linhaPosicional: string = '';
    resultado: PedidoResponse | null = null;
    erro: string | null = null;
    isLoading: boolean = false;

    constructor(private pedidoService: PedidoService) {}

    enviarPedido() {
        if (this.linhaPosicional.length !== 40) {
            this.erro = 'A linha deve ter exatamente 40 caracteres.';
            this.resultado = null;
            return;
        }
        this.isLoading = true;
        this.erro = null;
        this.resultado = null;

        this.pedidoService.enviarPedidoPosicional(this.linhaPosicional).subscribe({
            next: (response) => {
                this.resultado = response;
                this.isLoading = false;
                this.linhaPosicional = '';
                console.log('Pedido criado:', response);
            },
            error: (error) => {
                console.error('Erro ao enviar pedido:', error);
                this.erro = error.error?.message || error.message || 'Erro desconhecido ao enviar o pedido.';
                this.isLoading = false;
            }
        });
    }
}
