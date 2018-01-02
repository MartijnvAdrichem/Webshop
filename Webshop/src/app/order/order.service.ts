import { Injectable } from '@angular/core';
import {Order} from "./order";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class OrderService {

  constructor(private http:HttpClient) { }


  createOrder(order:Order) {
    console.log(order);
    return this.http.post('api/order/create', order).subscribe(data => {

    });
  }


}
