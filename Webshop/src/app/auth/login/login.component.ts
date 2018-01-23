import { Component, OnInit } from '@angular/core';
import {AuthService} from "../auth.service";
import {MatSnackBar} from '@angular/material';
import {Credentials} from "../credentials";
import {HttpErrorResponse} from "@angular/common/http";
import {TokenService} from "../token/token.service";
import {Router, ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {Account} from "../../account/account";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private location: Location, private authService: AuthService, public  snackBar: MatSnackBar, private tokenService: TokenService,  private route:ActivatedRoute, private router:Router) {}
  credentials: Credentials = new Credentials();

  public login(): void {
    const body = {username: this.credentials.username, password: this.credentials.password};

    this.authService.login(body).subscribe(data => {
        let token = data['token'];
        this.tokenService.setToken(token);
      this.authService.getAuthenticatedUser().subscribe(user => {
        let acc:Account = user;
        console.log(acc);
        this.authService.isAdmin = acc.admin;
      });
        if(this.location.path() === '/login') {
          this.location.back();
        }
      }, (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status === 403) {
            this.notifyInvalidCredentials();
          }
        }
      });
  }

  public notifyInvalidCredentials(): void {
    this.snackBar.open('Verkeerde gebruikersnaam en/of wachtwoord', "", {duration: 3000});
  }

  ngOnInit() {
  }

}
