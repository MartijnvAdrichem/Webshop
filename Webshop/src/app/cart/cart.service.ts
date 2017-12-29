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
    let cart:Cart;
    cart = this.getCart();

    //Check if the user has an cart, if not we initialize a new one
    if(cart == null){
      cart = new Cart();
      cart.producsInCart = [];
    }
    var productExists = false;

    for(var i = 0; i < cart.producsInCart.length; i++){
      if(cart.producsInCart[i].prodid == product.id){
        productExists = true;
        cart.producsInCart[i].amount += amount;
      }
    }
    //Product does not exists in the list yet, so we add it as a new row
    if(!productExists){
      var cartRow = new CartRow();
      //We only save the prod id, because thats all we need.
      cartRow.prodid = product.id;
      cartRow.amount = amount;
      cart.producsInCart.push(cartRow)
    }

    localStorage.setItem('cart', JSON.stringify(cart));
    this.calculateAmountinCart();

  }

  calculateAmountinCart() {
    let cart:Cart;
    cart = this.getCart();
    if(cart == null){
      this.amountInCart = 0;
      return;
    }
    let amount = 0;
    for(var i = 0; i < cart.producsInCart.length; i++){
        amount += cart.producsInCart[i].amount;
      }

    this.amountInCart = amount;

    }


  getCart() : Cart {
    return JSON.parse(localStorage.getItem('cart'));
  }


  removeOfChart(){

  }


}
