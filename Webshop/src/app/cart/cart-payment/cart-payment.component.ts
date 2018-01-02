import {Component, Input, OnInit} from '@angular/core';
import {Account} from "../../account/account";
import {AuthService} from "../../auth/auth.service";
import {Product} from "../../product/product";
import {Cart} from "../cart";
import {CartService} from "../cart.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-cart-payment',
  templateUrl: './cart-payment.component.html',
  styleUrls: ['./cart-payment.component.css']
})
export class CartPaymentComponent implements OnInit {



  payments = [
    {value: 'ing', viewValue: 'ING'},
    {value: 'abnAmro', viewValue: 'ABN Amro Bank'},
    {value: 'asn', viewValue: 'ASN Bank'},
    {value: 'bunq', viewValue: 'bunq'},
    {value: 'knab', viewValue: 'Knab'},
    {value: 'rabobank', viewValue: 'Rabobank'},
    {value: 'regiobank', viewValue: 'RegioBank'},
    {value: 'snsbank', viewValue: 'SNS Bank'},
    {value: 'triodosbank', viewValue: 'Triodos Bank'},
  ];

  account:Account = new Account();
  cart:Cart = new Cart();

  getVerzendKosten() {
    if(this.cart.getTotalPrice() < 20){
      return 2.99;
    } else {
      return 0;
    }
  }

  public cancelPayment(){
    this.router.navigate(['cart'])
  }

  constructor(private router:Router, private authService:AuthService, private cartService:CartService) { }

  ngOnInit() {
    this.cart = this.cartService.getCart();
    this.authService.getAuthenticatedUser().subscribe(data =>{
      this.account = data;
    })
  }

}
