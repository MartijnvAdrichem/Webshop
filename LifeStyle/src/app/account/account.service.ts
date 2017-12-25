import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Account} from "./account";
import {Observable} from "rxjs/Observable";
import {Router} from "@angular/router";
import {isUndefined} from "util";

@Injectable()
export class AccountService {

  selectedAccount:Account;
  loggedinAccount:Account;

  constructor(private http: HttpClient, private router:Router) { }

  createAccount(account: Account): Observable<any>{
    let accountGegevens = {
      //id: account.id,
      firstname: account.firstname,
      prefix: account.prefix,
      lastname: account.lastname,
      username: account.username,
      password: account.password,
      isAdmin: account.isAdmin,
      isActive: account.isActive
    }

    console.log(accountGegevens);

   return this.http.post('api/account/create', accountGegevens);
  }

  updateAccount(account: Account): Observable<any>{

    let accountGegevens = {
      id: account.id,
      firstname: account.firstname,
      prefix: account.prefix,
      lastname: account.lastname,
      username: account.username,
      password: account.password,
      isAdmin: account.isAdmin,
      isActive: account.isActive
    }

    console.log(accountGegevens);

    return this.http.put('api/account/edit', accountGegevens);
  }

  getAccount(): Observable<Account[]> {
    return this.http.get<Account[]>('api/account/all')
  }

  getAccountForTransfer(): Observable<Account[]> {
    return this.http.get<Account[]>('api/account/transfer/all')
  }
  public goAccountOverview() {
    this.router.navigate(['/account']);
  }


}
