import { Injectable } from '@angular/core';
import {Product} from "../product/product";
import {Cart} from "./cart";
import {CartRow} from "./cart-row";
import {isUndefined} from "util";
import {CartRowProduct} from "./cart-row-product";
import {ProductService} from "../product/product.service";

@Injectable()
export class CartService {

  amountInCart:number

  constructor(private productService:ProductService) {
    this.calculateAmountinCart();
  }


  addToCart(product:Product, amount:number){
    let productsInCart:CartRow[];
    productsInCart = JSON.parse(localStorage.getItem('cart'));

    //Check if the user has an cart, if not we initialize a new one
    if(productsInCart == null){
      productsInCart = []
    }

    var productExists = false;
    for(var i = 0; i < productsInCart.length; i++){
      if(productsInCart[i].prodid == product.id){
        productExists = true;
        productsInCart[i].amount += amount;
      }
    }
    //Product does not exists in the list yet, so we add it as a new row
    if(!productExists){
      var cartRow = new CartRow();
      cartRow.prodid = product.id;
      cartRow.amount = amount;
      productsInCart.push(cartRow);
    }

    localStorage.setItem('cart', JSON.stringify(productsInCart));
    this.calculateAmountinCart();

  }

  calculateAmountinCart() {
    let productsInCart:CartRow[];
    productsInCart = JSON.parse(localStorage.getItem('cart'));

    //Check if the user has an cart, if not we initialize a new one
    if(productsInCart == null){
      this.amountInCart = 0;
      return;
    }
    let amount = 0;
    for(var i = 0; i < productsInCart.length; i++){
        amount += productsInCart[i].amount;
      }

    this.amountInCart = amount;

    }

  getCart() : Cart {
   return this.getcartData();
  }

  getcartData() {
    let ids:number[] = new Array();
    let productsInCart:CartRow[];
    let cart:Cart;
    cart = new Cart();
    cart.productsInCart = [];
    productsInCart = JSON.parse(localStorage.getItem('cart'));

    if(productsInCart == null){
      return;
    }

    for(let i = 0; i < productsInCart.length; i++){
      ids.push(productsInCart[i].prodid);
    }

    this.productService.getProductInformation(ids).subscribe(data => {
      console.log(data);
      for(let i = 0; i < data.length; i++){
        let cartRowProduct = new CartRowProduct();
        cartRowProduct.product = data[i];
        cartRowProduct.amount =  productsInCart[i].amount;
        cart.productsInCart.push(cartRowProduct);
      }
    });
    return cart;
  }

  deleteCart() {
    localStorage.removeItem('cart');
    this.calculateAmountinCart();
  }

  removeOfCart(prodid){
    let productsInCart:CartRow[];
    productsInCart = JSON.parse(localStorage.getItem('cart'));

    //Check if the user has an cart, if not we initialize a new one
    if(productsInCart == null){
      productsInCart = []
    }

    for(var i = 0; i < productsInCart.length; i++){

      if(productsInCart[i].prodid == prodid){

          productsInCart.splice(i, 1);
          localStorage.setItem('cart', JSON.stringify(productsInCart));

          this.calculateAmountinCart();

          return;
      }
    }
  }


}
