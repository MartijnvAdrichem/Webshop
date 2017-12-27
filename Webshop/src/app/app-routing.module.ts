
import {AuthGuardService} from "./auth/guard/auth-guard.service";
import {AuthService} from "./auth/auth.service";
import {AppComponent} from "./app.component";
import {LoginComponent} from "./auth/login/login.component";
import {RouterModule, Routes} from "@angular/router";
import {AccountComponent} from "./account/account.component";
import {NgModule} from "@angular/core";
import {AccountRegisterComponent} from "./account/account-register/account-register.component";

const routes: Routes = [
  { path: '', redirectTo: 'account', pathMatch: "full"},
  { path: 'login', component: LoginComponent},
  { path: 'account/register', component: AccountRegisterComponent},
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ],
  providers: [AuthGuardService, AuthService]
})
export class AppRoutingModule {}
