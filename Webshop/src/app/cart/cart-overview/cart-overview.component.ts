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
  productsInCart:CartRowProduct[] = new Array();

  constructor(private cartService:CartService, private productService:ProductService) { }


  removeOfChart(prodid) {
    this.cartService.removeOfChart(prodid);
    for(var i = 0; i < this.productsInCart.length; i++){
      if(this.productsInCart[i].product.id == prodid){
        this.productsInCart.splice(i, 1);
        return;
      }
    }
  }

  getTotalPrice():number {
    let totalprice =  0;
    for(var i = 0; i < this.productsInCart.length; i++){
        totalprice += this.productsInCart[i].product.price * this.productsInCart[i].amount;
      }
    return totalprice;
  }

  getcartData() {
    let ids:number[] = new Array();
    for(let i = 0; i < this.cart.producsInCart.length; i++){
      ids.push(this.cart.producsInCart[i].prodid);
    }

    this.productService.getProductInformation(ids).subscribe(data => {
      console.log(data);
      for(let i = 0; i < data.length; i++){
        let cartRowProduct = new CartRowProduct();
        cartRowProduct.product = data[i];
        cartRowProduct.amount = this.cart.producsInCart[i].amount;
        this.productsInCart.push(cartRowProduct);
      }
    });
  }

  ngOnInit() {
    this.cart = this.cartService.getCart();
    this.getcartData();
  }

}

