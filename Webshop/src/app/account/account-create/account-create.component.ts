import { Component, OnInit } from '@angular/core';
import { Account } from '../account';
import {FormGroup, FormBuilder, Validators, FormControl} from '@angular/forms';
import {AccountService} from "../account.service";
import {Router} from "@angular/router";
import {AuthService} from "../../auth/auth.service";
import {current} from "codelyzer/util/syntaxKind";

@Component({
  selector: 'app-account-create',
  templateUrl: './account-create.component.html',
  styleUrls: ['./account-create.component.css']
})
export class AccountCreateComponent implements OnInit {

  account: Account = new Account();
  currentAccount: Account;
  passwordsMatch: boolean = true;
  buttonEnable: boolean = false;
  editing:boolean = false;
  isCurrentAdmin:boolean = false;


  ngOnInit() {
    this.account =  new Account();
    this.checkCurrentAccount();
    this.test();



    /**
    if(this.accountService.selectedAccount == null){
      console.log('1');
      this.account = new Account();
      this.editing = false;
    }
    else {
      if(this.currentAccount.isAdmin){
        console.log('2');
        this.account = this.accountService.selectedAccount;
        this.account.password2 = this.account.password;
      }
      else{
        console.log('3');
        this.account = this.currentAccount
        this.currentAccount.password2 = this.currentAccount.password;
      }

      this.editing = true;
    }*/

  }

public test() {

    if(this.accountService.selectedAccount == null){
      console.log('new acc');
      this.account = new Account();
      this.editing = false;
    }
    else{
      console.log('edit account');
      this.account = this.accountService.selectedAccount;
      this.account.password2 = this.account.password;
      this.editing = true;
    }


  if(this.editing ==  true){
    this.doPasswordsMatch();
  }

}
  public checkCurrentAccount(){
    this.authService.getAuthenticatedUser()
      .subscribe(
        resultAccount => {
          this.currentAccount = resultAccount;
          this.isCurrentAdmin = resultAccount.isAdmin;
        }
      );
  }

  constructor(private accountService:AccountService, private authService: AuthService){
    this.checkCurrentAccount();

  }

  public createAccount():void{
    this.accountService.createAccount(this.account).subscribe(accountGegevens => {
      this.goAccountOverview();
    }, (err: any) => {
      //do error handling
    })
  }

  public updateAccount():void{
    this.accountService.updateAccount(this.account).subscribe(accountGegevens => {
      this.goAccountOverview();
    }, (err: any) => {
      //do error handling
    });

  }


  public doPasswordsMatch(): void {

      if (this.account.password == this.account.password2) {
        this.passwordsMatch = true;
        this.buttonEnable = true;
      }
      else {
        this.passwordsMatch = false;
        this.buttonEnable = false;

    }
  }

  public goAccountOverview(){
    this.accountService.goAccountOverview();
  }
}



