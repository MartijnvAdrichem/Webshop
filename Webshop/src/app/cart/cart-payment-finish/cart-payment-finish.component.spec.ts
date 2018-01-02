import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CartPaymentFinishComponent } from './cart-payment-finish.component';

describe('CartPaymentFinishComponent', () => {
  let component: CartPaymentFinishComponent;
  let fixture: ComponentFixture<CartPaymentFinishComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CartPaymentFinishComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CartPaymentFinishComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
