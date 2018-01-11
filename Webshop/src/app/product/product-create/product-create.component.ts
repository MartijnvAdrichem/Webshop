import { Component, OnInit } from '@angular/core';
import {Product} from "../product";
import {ProductService} from "../product.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {

  product:Product = new Product();
  editingProduct:boolean = false;

  productTypes = [
    {value: 'MERCHANDISE', viewValue: 'Merchandise'},
    {value: 'FILM', viewValue: 'Film'},
    {value: 'STRIPBOEK', viewValue: 'Stripboek'},
  ];

  constructor(private productService:ProductService, private router:Router) { }


  makeProduct() {
    this.productService.makeProduct(this.product).subscribe(data =>{
      this.router.navigate(['home'])
    });
  }
  ngOnInit() {
  }

}
