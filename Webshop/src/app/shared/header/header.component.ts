import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthGuardService} from "../../auth/guard/auth-guard.service";
import {AuthService} from "../../auth/auth.service";
import {CartService} from "../../cart/cart.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router:Router, private authService:AuthService, private cartService:CartService) { }

  public logout() {
    this.authService.logout();
  }

  public goAccount(){
    this.router.navigate(['account']);
  }

  private isAuthenticated() {
    return this.authService.isAuthenticated();
  }

  ngOnInit() {
  }

}
