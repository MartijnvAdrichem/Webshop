import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountInzageComponent } from './account-overview.component';

describe('AccountInzageComponent', () => {
  let component: AccountInzageComponent;
  let fixture: ComponentFixture<AccountInzageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountInzageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountInzageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
