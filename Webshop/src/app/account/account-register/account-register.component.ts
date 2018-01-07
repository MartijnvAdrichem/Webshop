import { Component, OnInit } from '@angular/core';
import { Account } from '../account';
import {FormGroup, FormBuilder, Validators, FormControl} from '@angular/forms';
import {AccountService} from "../account.service";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {AuthService} from "../../auth/auth.service";
import {current} from "codelyzer/util/syntaxKind";

@Component({
  selector: 'app-account-register',
  templateUrl: './account-register.component.html',
  styleUrls: ['./account-register.component.css']
})
export class AccountRegisterComponent implements OnInit {

  account: Account = new Account();
  passwordsMatch: boolean = true;
  buttonEnable: boolean = false;
  editingAccount:boolean;

  constructor(private accountService:AccountService, private authService: AuthService, private router:Router,  private route: ActivatedRoute){
    this.route.params.subscribe(params => {
      this.editingAccount = (params['edit'] == 'true');

      if(this.editingAccount == false){
        this.account = new Account();
      } else {
        this.authService.getAuthenticatedUser().subscribe(
          resultAccount => {
            this.account = resultAccount;
          });
      }
    });

  }
  ngOnInit() {

  }

  public createAccount():void{
    this.accountService.createAccount(this.account).subscribe(accountGegevens => {
      this.router.navigate(['home'])
    }, (err: any) => {

    })
  }

  public updateAccount():void{
    this.accountService.updateAccount(this.account).subscribe(accountGegevens => {
      this.router.navigate(['home'])
    }, (err: any) => {
      //do error handling
    });

  }


  public doPasswordsMatch(): void {

      if (this.account.password == this.account.passwordRepeat) {
        this.passwordsMatch = true;
      }
      else {
        this.passwordsMatch = false;

    }
  }

}



