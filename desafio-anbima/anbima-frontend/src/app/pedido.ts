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
