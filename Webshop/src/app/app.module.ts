

import {
  MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule,
  MatCheckboxModule, MatChipsModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatFormFieldModule,
  MatGridListModule,
  MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule,
  MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule,
  MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule,
  MatStepperModule, MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule
} from "@angular/material";
import {NgModule} from "@angular/core";
import {AppComponent} from "./app.component";
import {HeaderComponent} from "./shared/header/header.component";
import {LoginComponent} from "./auth/login/login.component";
import {AccountRegisterComponent} from "./account/account-register/account-register.component";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {AppRoutingModule} from "./app-routing.module";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {HttpModule} from "@angular/http";
import {AuthService} from "./auth/auth.service";
import {TokenService} from "./auth/token/token.service";
import {AccountService} from "./account/account.service";
import {TokenInterceptor} from "./auth/token/token.interceptor";
import {MessageService} from "./shared/message/message.service";
import {MessageComponent} from "./shared/message/message.component";
import {AccountComponent} from "./account/account.component";
import { ProductComponent } from './product/product.component';
import { ProductOverviewComponent } from './product/product-overview/product-overview.component';
import {ProductService} from "./product/product.service";
import { CartComponent } from './cart/cart.component';
import {CartService} from "./cart/cart.service";
import { CartOverviewComponent } from './cart/cart-overview/cart-overview.component';
import { OrderComponent } from './order/order.component';
import { CartPaymentComponent } from './cart/cart-payment/cart-payment.component';
import {OrderService} from "./order/order.service";
import { OrderHistoryComponent } from './order/order-history/order-history.component';
import { HomeComponent } from './home/home.component';
import { NumberOnlyDirective} from './number.directive';

@NgModule({
  exports: [],
  declarations: [
    AppComponent,
    LoginComponent,
    AccountRegisterComponent,
    MessageComponent,
    AccountComponent,
    HeaderComponent,
    ProductComponent,
    ProductOverviewComponent,
    CartComponent,
    CartOverviewComponent,
    OrderComponent,
    CartPaymentComponent,
    OrderHistoryComponent,
    HomeComponent,
    NumberOnlyDirective,
  ],
  imports: [
    MatInputModule,
    MatFormFieldModule,
    MatSidenavModule,
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    MatRadioModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatDatepickerModule,
    MatDialogModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatStepperModule,
    HttpClientModule,
    MatSnackBarModule,
    MatAutocompleteModule
  ],
  providers: [AuthService, TokenService, AccountService, MessageService, ProductService, CartService, OrderService, {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent, MessageComponent]
})
export class AppModule { }
