import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthGuardService} from "../../auth/guard/auth-guard.service";
import {AuthService} from "../../auth/auth.service";
import {CartService} from "../../cart/cart.service";
import {Account} from "../../account/account";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router:Router, public authService:AuthService, public cartService:CartService) { }

  public logout() {
    this.authService.logout();
  }

  public goAccount(){
    this.router.navigate(['account']);
  }

  public isAuthenticated() {
    return this.authService.isAuthenticated();
  }

  public isAdmin():boolean{
    return this.authService.isAdmin;
  }
  ngOnInit() {
  if(this.authService.isAuthenticated()){
    this.authService.getAuthenticatedUser().subscribe(data => {
      let acc:Account = data;
      this.authService.isAdmin = acc.admin;
    });
  }

  }

}
