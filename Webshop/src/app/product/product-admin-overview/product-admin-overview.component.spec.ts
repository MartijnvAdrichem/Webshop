import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductAdminOverviewComponent } from './product-admin-overview.component';

describe('AccountAdminOverviewComponent', () => {
  let component: ProductAdminOverviewComponent;
  let fixture: ComponentFixture<ProductAdminOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductAdminOverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductAdminOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
