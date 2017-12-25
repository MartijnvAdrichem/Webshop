import {Component, Inject} from '@angular/core';
import {MAT_SNACK_BAR_DATA} from '@angular/material';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
})
export class MessageComponent {
  public isSuccess:Boolean;
  public message:String;

  constructor(@Inject(MAT_SNACK_BAR_DATA) public data: any) {
    this.isSuccess = data['isSuccess'];
    this.message = data['message'];
  }
}
