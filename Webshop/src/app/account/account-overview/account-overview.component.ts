import {Component, OnInit, ViewChild} from '@angular/core';
import { AccountService } from '../account.service';
import {Observable} from "rxjs/Observable";
import {Account} from "../account";
import {MatPaginator} from '@angular/material';
import {DataSource} from '@angular/cdk/collections';
import {Router} from "@angular/router";

@Component({
  selector: 'app-account-inzage',
  templateUrl: './account-overview.component.html',
  styleUrls: ['./account-overview.component.css']
})
export class AccountInzageComponent implements OnInit {


  displayedColumns = ['firstname'];
  dataSource = new AccountDataSource(this.accountService);
  selectedAccount:Account;
  currentFilter = "active";
  constructor(private accountService:AccountService, private router:Router) { }

  ngAfterViewInit() {

  }
  ngOnInit() {

  }
  onSelect(account: Account): void {
    this.selectedAccount = account;
    console.log(account.firstname)
  }

  public goNewAccount() {
    this.router.navigate(['/account/new']);
  }

  public goEditAccount(){
    this.router.navigate(['account/new']);
    console.log("goeditaccount");
  }

  public goEditOwnAccount(){
    this.router.navigate(['account/new']);
    console.log("edit own acc");
  }

  public setFilter(filter){
    console.log("setfilter methode werkt");
    this.currentFilter = filter;
  }


}

export class AccountDataSource extends DataSource<any> {

  constructor(private accountService: AccountService) {
    super();
  }

  connect(): Observable<Account[]> {

    return this.accountService.getAccount();
  }

  disconnect() {
  }


}
