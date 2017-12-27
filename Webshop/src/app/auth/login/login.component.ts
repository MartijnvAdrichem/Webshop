import { Component, OnInit } from '@angular/core';
import {AuthService} from "../auth.service";
import {MatSnackBar} from '@angular/material';
import {Credentials} from "../credentials";
import {HttpErrorResponse} from "@angular/common/http";
import {TokenService} from "../token/token.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService, public  snackBar: MatSnackBar, private tokenService: TokenService, private router:Router) {}
  credentials: Credentials = new Credentials();

  public login(): void {
    const body = {username: this.credentials.username, password: this.credentials.password};

    this.authService.login(body).subscribe(data => {
        let token = data['token'];
        this.tokenService.setToken(token);
        this.router.navigate(['account']);
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
