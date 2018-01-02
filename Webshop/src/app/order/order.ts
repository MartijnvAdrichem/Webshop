import {CartRowProduct} from "../cart/cart-row-product";

export class Order {

  account:Account;
  paymentBank: String;
  delivery: String;

  orderRows:CartRowProduct[];
  orderDate:Date;
  orderState:String;

}
