
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
import {OrderHistoryComponent} from "./order/order-history/order-history.component";
import {HomeComponent} from "./home/home.component";
import {ProductCreateComponent} from "./product/product-create/product-create.component";
import {ProductAdminOverviewComponent} from "./product/product-admin-overview/product-admin-overview.component";
import {AccountAdminOverviewComponent} from "./account/account-admin-overview/account-admin-overview.component";

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: "full"},
  { path: 'home', component: HomeComponent},
  { path: 'login', component: LoginComponent},
  { path: 'products/:type', component: ProductOverviewComponent},
  { path: 'account/register/:edit', component: AccountRegisterComponent},
  { path: 'cart', component: CartOverviewComponent},
  { path: 'cart/payment', component:CartPaymentComponent, canActivate: [AuthGuardService]},
  { path: 'order/history', component:OrderHistoryComponent, canActivate:[AuthGuardService]},
  { path: 'product/create/:prodid', component:ProductCreateComponent, canActivate:[AuthGuardService]},
  { path: 'product/all', component:ProductAdminOverviewComponent, canActivate:[AuthGuardService]},
  { path: 'account/all', component:AccountAdminOverviewComponent, canActivate:[AuthGuardService]},
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ],
  providers: [AuthGuardService, AuthService]
})
export class AppRoutingModule {}
