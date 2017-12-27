
import {AuthGuardService} from "./auth/guard/auth-guard.service";
import {AuthService} from "./auth/auth.service";
import {AppComponent} from "./app.component";
import {LoginComponent} from "./auth/login/login.component";
import {RouterModule, Routes} from "@angular/router";
import {AccountComponent} from "./account/account.component";
import {NgModule} from "@angular/core";
import {AccountCreateComponent} from "./account/account-create/account-create.component";

const routes: Routes = [
  { path: '', redirectTo: 'login', canActivate: [AuthGuardService], pathMatch: "full"},
  { path: 'login', component: LoginComponent},
  { path: 'account', component: AccountComponent , canActivate: [AuthGuardService]},
  { path: 'account/new', component: AccountCreateComponent, canActivate: [AuthGuardService]},
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ],
  providers: [AuthGuardService, AuthService]
})
export class AppRoutingModule {}
