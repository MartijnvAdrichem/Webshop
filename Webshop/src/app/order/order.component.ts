import {Component, Input, OnInit} from '@angular/core';
import {Order} from "./order";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  @Input() order:Order;


  constructor() { }

  public getTotalPrice(order):number {
    let totalprice =  0;
    for(var i = 0; i < order.orderRows.length; i++){
      totalprice += order.orderRows[i].product.price * order.orderRows[i].amount;
    }
    return parseFloat(totalprice.toFixed(2));
  }

  public convertDate(inputFormat) {
    function pad(s) { return (s < 10) ? '0' + s : s; }
    var d = new Date(inputFormat);
    return [pad(d.getDate()), pad(d.getMonth()+1), d.getFullYear()].join('/');
  }


  ngOnInit() {
  }

}
