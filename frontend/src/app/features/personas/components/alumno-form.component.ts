import { Component, inject, effect } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common'; // Agregado para estabilidad
import { AlumnoStore } from '../services/alumno.store';

@Component({
  selector: 'app-alumno-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './alumno-form.component.html'
})
export class AlumnoFormComponent {
  private fb = inject(FormBuilder);
  private _store = inject(AlumnoStore);

  get store() { return this._store; }

  form = this.fb.group({
    id: [0],
    nombre: ['', Validators.required],
    curso: ['', Validators.required],
    protocolo_id: ['', Validators.required] 
  });

  constructor() {
    effect(() => {
      const alumno = this.store.selected();
      if (alumno) {
        this.form.patchValue(alumno);
      }
    });
  }

  guardar() {
    if (this.form.invalid) return;
    const alumno = this.form.value;

    if (alumno.id) {
      this.store.update(alumno as any);
    } else {
      this.store.add(alumno as any);
    }
    this.form.reset({ id: 0, nombre: '', curso: '', protocolo_id: '' });
  }

  cancelar() {
    this.store.clearSelection();
    this.form.reset({ id: 0, nombre: '', curso: '', protocolo_id: '' });
  }
}