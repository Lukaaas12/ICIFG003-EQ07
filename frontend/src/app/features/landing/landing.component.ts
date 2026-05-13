import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [RouterLink], // Asegúrate de que RouterLink esté aquí
  templateUrl: './landing.component.html'
})
export class LandingComponent {
  private router = inject(Router);

  // ESTA ES LA FUNCIÓN QUE FALTA:
  cerrarSesion() {
    localStorage.removeItem('sesion_activa');
    this.router.navigate(['/login']);
  }
}