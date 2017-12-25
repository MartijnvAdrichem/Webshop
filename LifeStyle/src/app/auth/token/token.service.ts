import { Injectable } from '@angular/core';

@Injectable()
export class TokenService {

  public getToken(): string {
    return localStorage.getItem('token');
  }

  public setToken(token): void {
    localStorage.setItem('token', token);
  }

  public deleteToken(): void {
    localStorage.removeItem('token');
  }

  public isAuthenticated(): boolean {
    // get the token
    const token = this.getToken();

    // TODO check whether token is valid
    if(token) {return true}
    else {return false}
  }

  constructor() { }

}
