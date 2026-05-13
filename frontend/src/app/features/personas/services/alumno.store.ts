import { Injectable, signal, inject } from '@angular/core';
import { AlumnoService } from './alumno.service';
import { Alumno } from '../models/alumno.model';

@Injectable({
  providedIn: 'root'
})
export class AlumnoStore {
  private service = inject(AlumnoService);

  alumnos = signal<Alumno[]>([]);
  loading = signal(false);
  error = signal<string | null>(null);
  selected = signal<Alumno | null>(null);
  success = signal<string | null>(null);

  // 🔑 LA PIEZA FALTANTE:
  // Intenta sacar el nombre del localStorage; si no hay nada, pone 'Usuario' por defecto.
  usuarioActual = signal<string | null>(localStorage.getItem('username') || 'Usuario');

  load() {
    this.loading.set(true);
    this.service.getAll().subscribe({
      next: (data: Alumno[]) => {
        this.alumnos.set(data);
        this.loading.set(false);
      },
      error: (err: any) => {
        this.error.set('Error cargando alumnos');
        this.loading.set(false);
      }
    });
  }

  // ... (el resto de tus métodos select, add, update, delete se mantienen igual)
  
  select(alumno: Alumno) {
    this.selected.set(alumno);
  }

  clearSelection() {
    this.selected.set(null);
  }

  add(alumno: Alumno) {
    this.service.create(alumno).subscribe({
      next: (p: Alumno) => {
        this.alumnos.update((list: Alumno[]) => [...list, p]);
        this.success.set('Alumno guardado correctamente');
        setTimeout(() => this.success.set(null), 3000);
      },
      error: (err) => {
        console.error('ERROR AL GUARDAR:', err);
        this.error.set('No se pudo guardar el alumno. Revisa la conexión con el backend.');
        setTimeout(() => this.error.set(null), 5000);
      }
    });
  }
  
  update(alumno: Alumno) {
    this.service.update(alumno).subscribe({
      next: (updated: Alumno) => {
        this.alumnos.update((list: Alumno[]) =>
          list.map((p: Alumno) => p.id === updated.id ? updated : p)
        );
        this.clearSelection();
        this.success.set('Alumno actualizado correctamente');
        setTimeout(() => this.success.set(null), 3000);
      }
    });
  }

  delete(id: number) {
    this.service.delete(id).subscribe({
      next: () => {
        this.alumnos.update((list: Alumno[]) => list.filter((p: Alumno) => p.id !== id));
      }
    });
  }
}