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

  ngOnInit() {
    this.cart = this.cartService.getCart();
      console.log(this.cart);
      console.log(this.cart.producsInCart[0].amount);
    for(var i = 0; i < this.cart.producsInCart.length; i++){

      let cartRowProduct:CartRowProduct = new CartRowProduct();
      cartRowProduct.amount = this.cart.producsInCart[i].amount;

        this.productService.getProductInformation(this.cart.producsInCart[i].prodid).subscribe(data => {
          cartRowProduct.product = data;
          this.productsInCart.push(cartRowProduct);
        });
      }
    }


}
