import { Component, signal } from '@angular/core';
import { email, debounce, form,FormField, required } from '@angular/forms/signals';
import { Header } from '../header/header';


interface employeData {
    email: string;
    password: string;
}

@Component({
  imports: [Header,FormField],
  standalone: true,
  selector: 'app-login',
  styleUrl: './login.css',
  templateUrl: './login.html',
})
export class LoginComponent {

  readonly loginModel = signal<employeData>({
    email: '',
    password: '',
    });

    readonly loginForm = form(this.loginModel,(schemaPath) => {
            debounce(schemaPath.email, 500);
            required(schemaPath.email);
            email(schemaPath.email);
        });

    
    submit($event: SubmitEvent) {
       // Prevent the default form submission behavior
       $event.preventDefault();
       console.log('Form submitted:', this.loginModel());

    }

}

