import { Injectable } from '@angular/core';
import {Order} from "./order";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {CartService} from "../cart/cart.service";
import {Observable} from "rxjs/Observable";

@Injectable()
export class OrderService {

  constructor(private http:HttpClient, private router:Router, private cartService:CartService) { }


  createOrder(order:Order) {
    console.log(order);
    return this.http.post('api/order/create', order).subscribe(data => {
      this.router.navigate(['products/Merchandise'])
      this.cartService.deleteCart();
    });
  }

  getAllOrdersOfAccount() : Observable<Order[]> {
    return this.http.get<Order[]>('api/order/history');

  }

}
