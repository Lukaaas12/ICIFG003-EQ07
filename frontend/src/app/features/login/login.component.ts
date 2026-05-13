import { Component, inject } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { FormsModule } from '@angular/forms'; 
import { UsuarioService } from './usuario.service'; 

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterLink, FormsModule],
  templateUrl: './login.component.html'
})
export class LoginComponent {
  credenciales = { username: '', password: '' };
  
  private usuarioService = inject(UsuarioService);
  private router = inject(Router);

  iniciarSesion() {
    this.usuarioService.login(this.credenciales).subscribe({
      next: (res) => {
        this.router.navigate(['/alumnos']);
      },
      error: () => {
        alert('Usuario o contraseña incorrectos');
      }
    });
  }
}