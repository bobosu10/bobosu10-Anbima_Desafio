import { Routes } from '@angular/router';

import { NovoPedido } from './novo-pedido/novo-pedido';
import { PedidosLista } from './pedidos-lista/pedidos-lista';

export const routes: Routes = [
    { path: 'novo', component: NovoPedido },
    { path: 'pedidos', component: PedidosLista },
    { path: '', redirectTo:'/novo', pathMatch: 'full' },
];