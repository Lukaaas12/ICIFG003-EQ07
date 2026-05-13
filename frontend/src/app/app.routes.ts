import { Routes } from '@angular/router';
import { LandingComponent } from './features/landing/landing.component';
import { LoginComponent } from './features/login/login.component';
import { RegisterComponent } from './features/register/register.component'; // <-- 1. Importa esto

export const routes: Routes = [
  { path: '', component: LandingComponent, pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent }, // <-- 2. Agrega esta línea
  {
    path: 'alumnos',
    loadChildren: () => import('./features/personas/alumno.routes').then(m => m.ALUMNO_ROUTES)
  }
];