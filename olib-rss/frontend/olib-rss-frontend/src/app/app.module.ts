import { BrowserModule }    from '@angular/platform-browser';
import { NgModule }         from '@angular/core';
import { FormsModule }      from '@angular/forms';
import { HttpModule }       from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TabsModule }       from 'ngx-bootstrap/tabs';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent }     from './app.component';
import { 
  AuthGuard,
  LoginComponent,
  UserService,
  RegisterComponent,
  AuthenticationService
} from './shared';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    BsDropdownModule.forRoot(),
    TabsModule.forRoot(),
    BrowserAnimationsModule,
    AppRoutingModule
  ],
  declarations: [
    LoginComponent,
    RegisterComponent,
    AppComponent
  ],
  providers: [
    AuthGuard,
    UserService,
    AuthenticationService
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
