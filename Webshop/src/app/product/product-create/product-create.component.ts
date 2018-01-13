import { Component, OnInit } from '@angular/core';
import {Product} from "../product";
import {ProductService} from "../product.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {

  product:Product;
  editingProduct:boolean = false;

  productTypes = [
    {value: 'MERCHANDISE', viewValue: 'Merchandise'},
    {value: 'FILM', viewValue: 'Film'},
    {value: 'STRIPBOEK', viewValue: 'Stripboek'},
  ];

  constructor(private productService:ProductService, private router:Router, private authService: AuthService,  private route: ActivatedRoute) {
    this.route.params.subscribe(params => {
      this.editingProduct = (params['prodid'] != '0');

      if(this.editingProduct == false){
        this.product = new Product();
      } else {
        this.productService.getProduct(parseInt(params['prodid'])).subscribe(data =>{
          this.product = data;
        })
      }
    });

  }

  makeProduct() {
    this.productService.makeProduct(this.product).subscribe(data =>{
      this.router.navigate(['product/all'])
    });
  }
  updateProduct() {
    this.productService.updateProduct(this.product).subscribe(data => {
      this.router.navigate(['product/all'])
    })
  }

  ngOnInit() {
  }

}
