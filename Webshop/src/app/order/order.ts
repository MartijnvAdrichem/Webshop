import {CartRowProduct} from "../cart/cart-row-product";
import {Account} from "../account/account";
export class Order {

  id:number;
  accountid:number;
  paymentBank: String;
  delivery: String;

  orderRows:CartRowProduct[];
  orderDate:Date;
  orderState:String;

}
