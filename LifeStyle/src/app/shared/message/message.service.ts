import { Injectable } from '@angular/core';
import {Subject} from "rxjs/Subject";

@Injectable()
export class MessageService {
  public notificationSuccess: Subject<string> = new Subject();
  public notificationFailed: Subject<string> = new Subject();

  constructor() { }

}
