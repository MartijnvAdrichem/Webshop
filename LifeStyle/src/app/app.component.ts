import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {MessageService} from "./shared/message/message.service";
import {MatSnackBar} from "@angular/material";
import {MessageComponent} from "./shared/message/message.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private router: Router, private messageService:MessageService, private snackBar:MatSnackBar) {
    this.messageService.notificationSuccess.subscribe(message => {
        let data = {isSuccess: true, message:message};
        this.snackBar.openFromComponent(MessageComponent, {data : data,
          duration: 5000,
      });
    });

    this.messageService.notificationFailed.subscribe(message => {
      let data = {isSuccess: false, message:message};
      this.snackBar.openFromComponent(MessageComponent, {data : data,
        duration: 5000,
      });
    });
  }
  title = 'app';
}
