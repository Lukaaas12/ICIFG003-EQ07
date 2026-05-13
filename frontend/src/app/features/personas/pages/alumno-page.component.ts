import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AlumnoStore } from '../services/alumno.store';

// 1. IMPORTA LOS COMPONENTES QUE FALTAN
import { AlumnoFormComponent } from '../components/alumno-form.component';
import { AlumnoListComponent } from '../components/alumno-list.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-alumno-page',
  standalone: true,
  // 2. AGRÉGALOS AQUÍ (Esto quita los errores NG8001)
  imports: [
    CommonModule, 
    RouterLink, 
    AlumnoFormComponent, 
    AlumnoListComponent
  ],
  templateUrl: './alumno-page.component.html'
})
export class AlumnoPageComponent {
  public store = inject(AlumnoStore);
  private router = inject(Router);

  cerrarSesion() {
    localStorage.removeItem('sesion_activa');
    localStorage.removeItem('username');
    this.router.navigate(['/login']);
  }
}