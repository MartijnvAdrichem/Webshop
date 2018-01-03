import { Component, OnInit } from '@angular/core';
import {Cart} from "../cart";
import {CartService} from "../cart.service";
import {CartRowProduct} from "../cart-row-product";
import {ProductService} from "../../product/product.service";
import {Product} from "../../product/product";

@Component({
  selector: 'app-cart-overview',
  templateUrl: './cart-overview.component.html',
  styleUrls: ['./cart-overview.component.css']
})
export class CartOverviewComponent implements OnInit {

  cart:Cart;

  constructor(private cartService:CartService, private productService:ProductService) { }


  removeOfCart(prodid) {
    this.cartService.removeOfCart(prodid);
    this.cart = this.cartService.getCart();
  }

  isCartEmpty():boolean {
    if(this.cart == null){
      return true;
    }
    if(this.cart.productsInCart == null){
      return true;
    }
    if(this.cart.productsInCart.length == 0){
      return true;
    }
    return false;
  }

  ngOnInit() {
    this.cart = this.cartService.getCart();
  }

}

