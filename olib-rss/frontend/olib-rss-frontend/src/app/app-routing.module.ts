import { NgModule }             from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { 
  AuthGuard,
  LoginComponent,
  RegisterComponent
} from './shared';

const routes : Routes = [
  { path: '',         loadChildren : './modules/layout/layout.module#LayoutModule'  },
  { path:'login',     component : LoginComponent },
  { path:'register',  component : RegisterComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
