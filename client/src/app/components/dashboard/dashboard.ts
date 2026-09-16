import { Component } from '@angular/core';
import { Header } from "../header/header";
import { Footer } from "../footer/footer";
import { EmployeeList } from '../employee-list/employee-list';

@Component({
  imports: [Header, Footer, EmployeeList],
  selector: 'app-dashboard',
  styleUrl: './dashboard.css',
  templateUrl: './dashboard.html',
})
export class Dashboard {}
