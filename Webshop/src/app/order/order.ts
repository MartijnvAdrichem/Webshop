import {CartRowProduct} from "../cart/cart-row-product";

export class Order {

  constructor() {

  }

  id:number;
  accountid:number;
  paymentBank: String;
  delivery: String;

  orderRows:CartRowProduct[];
  orderDate:Date;
  orderState:String;

}
