import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpResponse, HttpErrorResponse
} from '@angular/common/http';
import { Router } from "@angular/router";
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import {TokenService} from "./token.service";
import {MessageService} from "../../shared/message/message.service";
import {isUndefined} from "util";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(public tokenService: TokenService, private router:Router, private messageService:MessageService) {}
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${this.tokenService.getToken()}`
      }
    });
    return next.handle(request).do((event: HttpEvent<any>) => {
      if (event instanceof HttpResponse) {
        if(isUndefined(event.body['errors']) == false) {
            this.messageService.notificationSuccess.next(event.body['errors']);
        }
         //do stuff with response if you want
      }
    }, (err: any) => {
      if (err instanceof HttpErrorResponse) {
          console.log(isUndefined(err.error['errors']));
          if(isUndefined(err.error['errors']) == false ) {
            this.messageService.notificationFailed.next(err.error['errors'][0]);
       }
        if (err.status === 401) {
          // redirect to the login route
          this.router.navigate(['login']);
        }
      }
    });
  }
}
