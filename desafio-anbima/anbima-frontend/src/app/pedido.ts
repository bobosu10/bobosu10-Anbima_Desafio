import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

//interface para tipar resposta com base nos DTOs
export interface PedidoResponse {
    id: number;
    tipoLanche: string;
    proteina: string;
    acompanhamento: string;
    quantidade: number;
    bebida: string;
    valor: number;
    status: string;
    criadoEm: string;
}

@Injectable({
    providedIn: 'root'
})
export class PedidoService {

    private apiUrlGateway = 'http://localhost:8080/pedidos'; //url do gateway A

    private apiUrlProcessor = 'http://localhost:8081/pedidos'; // URL do Processor B

    constructor(private http: HttpClient) { }

    enviarPedidoPosicional(linhaPosicional: string): Observable<PedidoResponse> {
        const headers = new HttpHeaders({ 'Content-Type': 'text/plain' });
        return this.http.post<PedidoResponse>(`${this.apiUrlGateway}/posicional`, linhaPosicional, { headers: headers});
    }

    listarTodosPedidos(): Observable<PedidoResponse[]> {
        return this.http.get<PedidoResponse[]>(this.apiUrlProcessor);
    }

    buscarPedidoPorId(id:number): Observable<PedidoResponse> {
        return this.http.get<PedidoResponse>(`${this.apiUrlProcessor}/${id}`);
    }
}
