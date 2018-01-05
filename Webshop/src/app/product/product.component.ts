import {Component, Input, OnInit} from '@angular/core';
import {Product} from "./product";
import {CartService} from "../cart/cart.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  @Input() product:Product;

  amount = 1;

  constructor(private cartService:CartService) { }


  public addProductToCart(){
    this.cartService.addToCart(this.product, this.amount);
  }
  ngOnInit() {
  }


}
