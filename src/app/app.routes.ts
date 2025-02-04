import { Routes } from '@angular/router';
import { RegisterComponent } from './auth/components/register/register.component';
import { LoginComponent } from './auth/components/login/login.component';
import {AdminComponent} from './modules/admin/admin.component';
import {RoomsComponent} from './modules/customers/components/rooms/rooms.component';
import {CustomersComponent} from './modules/customers/customers.component';

export const routes: Routes = [
  // { path: '', redirectTo: '/login', pathMatch: 'full' }, // Default route to login
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'admin', component: AdminComponent},
  { path: 'customers', component: CustomersComponent},
  { path: 'customers/room', component: RoomsComponent },
];
