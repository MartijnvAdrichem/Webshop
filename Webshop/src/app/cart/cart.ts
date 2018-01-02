import {Product} from "../product/product";
import {CartRow} from "./cart-row";
import {CartRowProduct} from "./cart-row-product";

export class Cart {

  constructor() {
    this.productsInCart = new Array();
  }
  productsInCart: CartRowProduct[];

  getTotalPrice():number {
    let totalprice =  0;
    for(var i = 0; i < this.productsInCart.length; i++){
      totalprice += this.productsInCart[i].product.price * this.productsInCart[i].amount;
    }
    return parseFloat(totalprice.toFixed(2));
  }

}
