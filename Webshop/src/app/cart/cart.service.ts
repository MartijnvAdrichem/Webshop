import { Injectable } from '@angular/core';
import {Product} from "../product/product";
import {Cart} from "./cart";
import {CartRow} from "./cart-row";
import {isUndefined} from "util";

@Injectable()
export class CartService {

  amountInCart:number

  constructor() {
    this.calculateAmountinCart();
  }


  addToChart(product:Product, amount:number){
    console.log("Running!")
    let cart:Cart;
    cart = JSON.parse(localStorage.getItem('cart'));
    if(cart == null){
      console.log("null");
      cart = new Cart();
      cart.producsInCart = [];
    }
    var productExists = false;

    for(var i = 0; i < cart.producsInCart.length; i++){
      if(cart.producsInCart[0].product.id == product.id){
        productExists = true;
        cart.producsInCart[0].amount += amount;
      }
    }
    if(!productExists){
      var cartRow = new CartRow();
      cartRow.product = product;
      cartRow.amount = amount;
      cart.producsInCart.push(cartRow)
    }

    localStorage.setItem('cart', JSON.stringify(cart));
    this.calculateAmountinCart();

  }

  calculateAmountinCart() {
    let cart:Cart;
    cart = JSON.parse(localStorage.getItem('cart'));
    if(cart == null){
      this.amountInCart = 0;
    }
    let amount = 0;
    for(var i = 0; i < cart.producsInCart.length; i++){
        amount += cart.producsInCart[i].amount;
      }

    this.amountInCart = amount;

    }



  removeOfChart(){

  }


}
