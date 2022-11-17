import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DeviceListComponent} from "./components/device-list/device-list.component";
import {AdminComponent} from "./components/admin/admin.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {RegisterComponent} from "./components/register/register.component";
import {LogInComponent} from "./components/log-in/log-in.component";
import {HomeComponent} from "./components/home/home.component";
import {AuthGuard} from "./services/auth.guard";
import {ModeratorComponent} from "./components/moderator/moderator.component";
import {ModeratorGuard} from "./services/moderator.guard";

const routes: Routes = [
  {path: 'devices', component: DeviceListComponent},
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LogInComponent},
  {path: 'register', component: RegisterComponent},
  {
    path: 'admin', component: AdminComponent, canActivate: [AuthGuard],
    children: [{path: 'profile', component: ProfileComponent}]
  },
  // {path: 'profile', component: ProfileComponent},
  {path: 'moderator', component: ModeratorComponent, canActivate: [ModeratorGuard]},
  {path: '', redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthGuard, ModeratorGuard]
})
export class AppRoutingModule {
}
