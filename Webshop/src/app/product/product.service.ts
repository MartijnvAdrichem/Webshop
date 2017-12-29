import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Product} from "./product";

@Injectable()
export class ProductService {

  constructor(private http: HttpClient) { }

  public getProductsbyFilter(filter): Observable<Product[]> {
    return this.http.get<Product[]>('api/product/list/' + filter);
  }

  public getProductInformation(ids):Observable<Product[]>  {
      let idString = "";
      for(let i = 0; i < ids.length; i++){
        idString += ids[i] + "-";
      }
    return this.http.get<Product[]>('api/product/'  + idString);
  }
}
