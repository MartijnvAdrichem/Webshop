import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../product.service";
import {Product} from "../product";
import {isUndefined} from "util";

@Component({
  selector: 'app-product-overview',
  templateUrl: './product-overview.component.html',
  styleUrls: ['./product-overview.component.css']
})
export class ProductOverviewComponent implements OnInit {

  title: string;
  products: Product[];

  constructor(private productService: ProductService, private route: ActivatedRoute) {
    this.route.params.subscribe(params => {
      this.title = params['type']
      this.getProducts();
    });

  }

  checkProductsValid() : boolean {
    if (isUndefined(this.products))
      return false;

    if(this.products.length == 0){
      return false;
    }
    return true;
  }

  getProducts() {
    this.productService.getProductsbyFilter(this.title)
      .subscribe(
        resultProducts => {
          this.products = resultProducts;
        }
      );

  }

  ngOnInit() {
  }

}
