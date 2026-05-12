import { Component, inject } from '@angular/core';
// Importamos los nuevos nombres de los componentes
import { AlumnoListComponent } from '../components/alumno-list.component'; 
import { AlumnoFormComponent } from '../components/alumno-form.component';
import { AlumnoStore } from '../services/alumno.store';

@Component({
  selector: 'app-alumno-page',
  standalone: true,
  // Usamos las clases AlumnoListComponent y AlumnoFormComponent
  imports: [AlumnoListComponent, AlumnoFormComponent],
  templateUrl: './alumno-page.component.html'
})
export class AlumnoPageComponent {
  // Inyectamos AlumnoStore en lugar de PersonaStore
  store = inject(AlumnoStore);
}
