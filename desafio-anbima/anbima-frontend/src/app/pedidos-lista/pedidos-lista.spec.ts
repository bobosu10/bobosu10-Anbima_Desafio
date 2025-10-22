import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PedidosLista } from './pedidos-lista';

describe('PedidosLista', () => {
  let component: PedidosLista;
  let fixture: ComponentFixture<PedidosLista>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PedidosLista]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PedidosLista);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
