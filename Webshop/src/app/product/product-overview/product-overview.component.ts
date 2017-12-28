import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-product-overview',
  templateUrl: './product-overview.component.html',
  styleUrls: ['./product-overview.component.css']
})
export class ProductOverviewComponent implements OnInit {

  title:string;

  constructor(private route:ActivatedRoute) {
    this.route.params.subscribe( params => this.title = params['type']);

}

  ngOnInit() {
  }

}
