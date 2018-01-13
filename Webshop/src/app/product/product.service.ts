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
  public getAllProducts():Observable<Product[]> {
    return this.http.get<Product[]>('api/product/all');
  }

  public getProductInformation(ids):Observable<Product[]>  {
     //Make a string of the product ids so we can send it to the API
    // This is because you can't directly send an arralylist in a get request.
      let idString = "";
      for(let i = 0; i < ids.length; i++){
        idString += ids[i] + "-";
      }
    return this.http.get<Product[]>('api/product/'  + idString);
  }

  public makeProduct(product:Product): Observable<any>{
   return this.http.post('api/product/create', product);
  }

  public updateProduct(product:Product):Observable<any>{
    return this.http.put('api/product/update', product);
  }
  public deletePrduct(id:number):Observable<any>{
    return this.http.delete('api/product/delete/' + id);
  }

  public getProduct(id:number):Observable<Product>{
    return this.http.get("api/product/get/" + id);
  }
}
