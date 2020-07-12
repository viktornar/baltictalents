import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { PathResolveService } from './not-found/path-resolve.service';
import { AuthenticationGuard } from './auth/authentication.guard';
import { Role } from './auth/role';
import { NotAuthorizedComponent } from './not-authorized/not-authorized.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [AuthenticationGuard],
    data: { roles: [Role.Admin, Role.Editor] }
  },
  { path: 'not-authorized', component: NotAuthorizedComponent },
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  {
    path: '**',
    resolve: {
      path: PathResolveService
    },
    component: NotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
