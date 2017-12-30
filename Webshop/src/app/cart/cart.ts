import {Product} from "../product/product";
import {CartRow} from "./cart-row";

export class Cart {

  constructor() {
    this.producsInCart = new Array();
  }
  producsInCart: CartRow[];


}
