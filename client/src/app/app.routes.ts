import { Routes } from '@angular/router';

export const routes: Routes = [
    {path: '',loadComponent: () => import('./components/login/login').then((m) => m.LoginComponent),},
    {path: 'dashboard',loadComponent: () => import('./components/dashboard/dashboard').then((m) => m.Dashboard),},
    {path: 'employee',loadComponent: () => import('./components/employee-list/employee-list').then((m) => m.EmployeeList),}
];
