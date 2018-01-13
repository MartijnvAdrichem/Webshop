import { Component, OnInit } from '@angular/core';
import {Product} from "../product";
import {ProductService} from "../product.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-product-admin-overview',
  templateUrl: './product-admin-overview.component.html',
  styleUrls: ['./product-admin-overview.component.css']
})
export class ProductAdminOverviewComponent implements OnInit {

  products:Product[];

  constructor(private productService:ProductService, private router:Router) { }

  ngOnInit() {
    this.productService.getAllProducts().subscribe(data => {
      this.products = data;
    })
  }

  newProduct() {
    this.router.navigate(['product/create/0'])
  }

  removeProduct(product:Product) {
    this.productService.deletePrduct(product.id).subscribe(data => {
      this.products.splice(this.products.indexOf(product), 1);
    });
  }

  editProduct(product:Product) {
    this.router.navigate(['product/create/' + product.id])
  }
}
