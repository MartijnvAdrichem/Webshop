import { Component, OnInit } from '@angular/core';
import {AccountService} from "../account.service";
import {Account} from "../account";

@Component({
  selector: 'app-account-admin-overview',
  templateUrl: './account-admin-overview.component.html',
  styleUrls: ['./account-admin-overview.component.css']
})
export class AccountAdminOverviewComponent implements OnInit {

  accounts:Account[];
  constructor(private accountService:AccountService) { }

  ngOnInit() {
    this.accountService.getAccount().subscribe(data => {
      this.accounts = data;
    })
  }

  removeAccount(account:Account){
    this.accountService.removeAccount(account).subscribe(data => {
      this.accounts.splice(this.accounts.indexOf(account), 1);
    });
  }
}
