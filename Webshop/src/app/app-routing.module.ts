
import {AuthGuardService} from "./auth/guard/auth-guard.service";
import {AuthService} from "./auth/auth.service";
import {AppComponent} from "./app.component";
import {LoginComponent} from "./auth/login/login.component";
import {RouterModule, Routes} from "@angular/router";
import {AccountComponent} from "./account/account.component";
import {NgModule} from "@angular/core";
import {AccountRegisterComponent} from "./account/account-register/account-register.component";
import {ProductComponent} from "./product/product.component";
import {ProductOverviewComponent} from "./product/product-overview/product-overview.component";
import {CartOverviewComponent} from "./cart/cart-overview/cart-overview.component";
import {CartPaymentComponent} from "./cart/cart-payment/cart-payment.component";

const routes: Routes = [
  { path: '', redirectTo: 'products/Stripboeken', pathMatch: "full"},
  { path: 'login', component: LoginComponent},
  { path: 'products/:type', component: ProductOverviewComponent},
  { path: 'account/register', component: AccountRegisterComponent},
  { path: 'cart', component: CartOverviewComponent},
  { path: 'cart/payment', component:CartPaymentComponent, canActivate: [AuthGuardService]},
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ],
  providers: [AuthGuardService, AuthService]
})
export class AppRoutingModule {}
