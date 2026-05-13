import { Component, inject } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { UsuarioService } from '../login/usuario.service'; 

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterLink, FormsModule],
  templateUrl: './register.component.html'
})
export class RegisterComponent {
  usuario = { username: '', password: '', rol: 'USER' };
  
  private usuarioService = inject(UsuarioService);
  private router = inject(Router);

  registrar() {
    this.usuarioService.registrar(this.usuario).subscribe({
      next: () => {
        alert('Usuario creado con éxito');
        this.router.navigate(['/login']);
      },
      error: () => alert('Error al registrar')
    });
  }
}