import {Component, Input, OnInit} from '@angular/core';
import {Account} from "../../account/account";
import {AuthService} from "../../auth/auth.service";
import {Product} from "../../product/product";
import {Cart} from "../cart";
import {CartService} from "../cart.service";
import {Router} from "@angular/router";
import {Order} from "../../order/order";
import {OrderService} from "../../order/order.service";
import {MessageService} from "../../shared/message/message.service";

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
  order:Order = new Order();

  getVerzendKosten() {
    if(this.cart.getTotalPrice() < 20){
      return 2.99;
    } else {
      return 0;
    }
  }

  finishPayment() {
    if(this.order.delivery == null){
      this.messageService.notificationFailed.next("U moet nog een bezorgdienst selecteren...");
      return;
    }
    if(this.order.paymentBank == null){
      this.messageService.notificationFailed.next("U heeft nog geen bank geselectereerd...");
      return;
    }
    this.order.orderRows = this.cart.productsInCart;
    this.orderSerivce.createOrder(this.order);
  }

  public cancelPayment(){
    this.router.navigate(['cart'])
  }

  constructor(private messageService:MessageService, private router:Router,private orderSerivce:OrderService, private authService:AuthService, private cartService:CartService) { }

  ngOnInit() {
    this.cart = this.cartService.getCart();
    this.authService.getAuthenticatedUser().subscribe(data =>{
      this.account = data;
    })
  }

}
