import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountAdminOverviewComponent } from './account-admin-overview.component';

describe('AccountAdminOverviewComponent', () => {
  let component: AccountAdminOverviewComponent;
  let fixture: ComponentFixture<AccountAdminOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountAdminOverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountAdminOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
