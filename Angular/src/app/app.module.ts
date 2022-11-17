import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from "@angular/forms";
import {ReactiveFormsModule} from '@angular/forms';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {UserListComponent} from './components/user-list/user-list.component';
import {DeviceListComponent} from './components/device-list/device-list.component';
import {RegisterComponent} from './components/register/register.component';
import {LogInComponent} from './components/log-in/log-in.component';
import {ProfileComponent} from './components/profile/profile.component';
import {AdminComponent} from './components/admin/admin.component';
import {HomeComponent} from './components/home/home.component';
import {ModeratorComponent} from './components/moderator/moderator.component';
import {authInterceptorProviders} from "./auth.interceptor";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTableModule} from "@angular/material/table";
import {MatButtonModule} from "@angular/material/button";
import {MatPaginatorModule} from "@angular/material/paginator";
import { NgxPaginationModule } from 'ngx-pagination';
import { NavigationMenuComponent } from './components/navigation-menu/navigation-menu.component';

@NgModule({
  declarations: [
    AppComponent,
    DeviceListComponent,
    UserListComponent,
    RegisterComponent,
    LogInComponent,
    ProfileComponent,
    AdminComponent,
    HomeComponent,
    ModeratorComponent,
    NavigationMenuComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatButtonModule,
    MatPaginatorModule,
    NgxPaginationModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule {
}
