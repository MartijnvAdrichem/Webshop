import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Account} from "./account";
import {Observable} from "rxjs/Observable";
import {Router} from "@angular/router";
import {isUndefined} from "util";

@Injectable()
export class AccountService {

  loggedinAccount:Account;

  constructor(private http: HttpClient, private router:Router) { }

  createAccount(account: Account): Observable<any>{
    let accountGegevens = {
      firstname: account.firstname,
      prefix: account.prefix,
      lastname: account.lastname,
      password: account.password,
      eMail: account.eMail,
      street: account.street,
      houseNumber: account.houseNumber,
      zipCode: account.zipCode,
      town: account.town,
    }

    console.log(accountGegevens);

   return this.http.post('api/account/register', accountGegevens);
  }

  updateAccount(account: Account): Observable<any>{

    let accountGegevens = {
      id: account.id,
      firstname: account.firstname,
      prefix: account.prefix,
      lastname: account.lastname,
      password: account.password,
    }

    console.log(accountGegevens);

    return this.http.put('api/account/edit', accountGegevens);
  }

  getAccount(): Observable<Account[]> {
    return this.http.get<Account[]>('api/account/all')
  }

  public goAccountOverview() {
    this.router.navigate(['/account']);
  }


}
